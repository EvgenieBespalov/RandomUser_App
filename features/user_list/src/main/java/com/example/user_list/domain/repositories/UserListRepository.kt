package com.example.user_list.domain.repositories

import androidx.paging.PagingData
import com.example.user_list.domain.entities.User
import kotlinx.coroutines.flow.Flow

interface UserListRepository {
    suspend fun getListUser(
        seed: String?,
        selectedGender: String?
    ): Flow<PagingData<User>>

    suspend fun getUser(): User
}