package com.example.user_list.binding.user_list.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.example.user_list.binding.user_list.converter.UserListConverter
import com.example.user_list.domain.entities.User
import com.example.user_list.domain.repositories.UserListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import repository.ApiRepository

class UserAdapterUserListModule(
    private val converter: UserListConverter,
    private val apiRepository: ApiRepository
) : UserListRepository{
    override suspend fun getListUser(
        seed: String?,
        selectedGender: String?
    ): Flow<PagingData<User>> =
        apiRepository.getListUser(
            seed = null,
            selectedGender = null
        ).map {pagingData ->
            pagingData.map {
                converter.convertUser(it)
            }
        }

    override suspend fun getUser(): User {
        TODO("Not yet implemented")
    }
}