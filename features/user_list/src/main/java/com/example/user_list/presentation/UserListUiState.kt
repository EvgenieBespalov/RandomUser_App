package com.example.user_list.presentation

import androidx.paging.PagingData
import com.example.user_list.domain.entities.User
import kotlinx.coroutines.flow.Flow

sealed interface UserListUiState{
    object Initial : UserListUiState

    object Loading : UserListUiState

    data class Content(val userList: Flow<PagingData<User>>) : UserListUiState

    data class Error(val message: String?) : UserListUiState
}