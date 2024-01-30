package com.example.user_list.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.user_list.domain.entities.User
import com.example.user_list.presentation.UserListScreenViewModel
import com.example.user_list.presentation.UserListUiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserListScreen(
    navController: NavHostController,
    viewModel: UserListScreenViewModel = koinViewModel()
) {
    val state by viewModel.state.observeAsState(UserListUiState.Initial)

    when(state){
        UserListUiState.Initial    -> viewModel.getUserList(null, null)
        UserListUiState.Loading    -> LoadScreen()
        is UserListUiState.Content -> {
            UserListColumn(
                navController = navController,
                users = (state as UserListUiState.Content).userList.collectAsLazyPagingItems()
            )
        }
        is UserListUiState.Error   -> ErrorScreen(errorText = (state as UserListUiState.Error).message.orEmpty())
    }
}

@Composable
fun UserListColumn(
    navController: NavHostController,
    users: LazyPagingItems<User>
){
    LazyColumn(){
        items(
            count = users.itemCount
        ){
            users[it]?.let { index ->
                UserBox(
                    navController = navController,
                    users = index
                )
            }
        }
    }
}

@Composable
fun UserBox(
    navController: NavHostController,
    users: User
){
    Box(
        modifier = Modifier
            //.fillMaxSize()
            .padding(10.dp)
    ){
        Row(
            //modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(100.dp)
                    .clickable {
                        navController.navigate("UserInfoModuleRoute")
                    },
                model = users.picture.thumbnail,
                contentScale = ContentScale.Crop,
                contentDescription = "Icon user"
            )
            Column(
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Text(text = users.name.title + ". " + users.name.first + " " + users.name.last)
                Text(text = users.location.country
                        + ", " + users.location.state
                        + ", " + users.location.city
                        + ", " + users.location.street.name
                        + ", " + users.location.street.number)
                Text(text = users.phone)
            }
        }
    }
}

@Composable
internal fun ErrorScreen(errorText: String){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ){
        Text(text = errorText)
    }
}

@Composable
internal fun LoadScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ){
        CircularProgressIndicator()
    }
}