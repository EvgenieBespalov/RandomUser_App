package com.example.user_list.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.user_info.UserInfoScreen
import com.example.user_list.screen.UserListScreen

@Composable
fun ModulesNavHostContainer(
    padding: PaddingValues
){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ModulesRoutes.UserListModule.route,
        modifier = Modifier.padding(paddingValues = padding),
        builder = {
            composable(ModulesRoutes.UserListModule.route) {
                UserListScreen(navController = navController)
            }

            composable(ModulesRoutes.UserInfoModule.route) {
                UserInfoScreen()
            }
        }
    )
}