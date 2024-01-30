package com.example.user_list.navigation

sealed class ModulesRoutes(val route: String) {
    object UserListModule : ModulesRoutes("UserListModuleRoute")
    object UserInfoModule : ModulesRoutes("UserInfoModuleRoute")
}