package com.post.presentation.navigation

sealed class NavigationItem(val route: String) {
    data object PostWebLink : NavigationItem(Screen.POST_WEB_LINK.name)
    data object PostList : NavigationItem(Screen.POST_LIST.name)
}

enum class Screen {
    POST_LIST,
    POST_WEB_LINK,
}