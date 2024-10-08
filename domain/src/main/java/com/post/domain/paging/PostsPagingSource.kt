package com.post.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.post.core.util.OperationResult
import com.post.domain.interfaces.PostRemoteDataSource
import com.post.domain.models.Post

class PostsPagingSource(private val repository: PostRemoteDataSource) : PagingSource<Int, Post>() {
    override fun getRefreshKey(state: PagingState<Int, Post>): Int {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        val key = params.key ?: 1
        val page = if (key == 0) 1 else key

        return when (val result = repository.getPosts(page)) {
            is OperationResult.Success -> {
                return LoadResult.Page(
                    data = result.data,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (result.data.isEmpty()) null else page + 1
                )
            }

            is OperationResult.Error -> LoadResult.Error(result.exception ?: Exception(""))
        }
    }

}