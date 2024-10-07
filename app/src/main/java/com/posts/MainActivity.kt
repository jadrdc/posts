package com.posts

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.post.presentation.navigation.NavigationItem
import com.post.presentation.screen.PostListScreen
import com.post.presentation.screen.PostWebScreen
import com.posts.ui.theme.PostsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PostsTheme {
                val navController = rememberNavController()
                NavHost(
                    navController,
                    startDestination = NavigationItem.PostList.route,
                    Modifier.padding()
                ) {
                    composable(NavigationItem.PostList.route) {
                        PostListScreen(onNavigate = {
                            val encodedUrl = Uri.encode(it.url)
                            navController.navigate(NavigationItem.PostWebLink.route + "/${encodedUrl}")
                        })
                    }
                    composable(
                        route = NavigationItem.PostWebLink.route + "/{url}",
                    ) { backStackEntry ->
                        val url = backStackEntry.arguments?.getString("url") ?: ""
                        PostWebScreen(url) { navController.popBackStack() }
                    }
                }

            }
        }
    }
}
