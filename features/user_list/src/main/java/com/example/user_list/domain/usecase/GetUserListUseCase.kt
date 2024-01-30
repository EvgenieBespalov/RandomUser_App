package com.example.user_list.domain.usecase

import androidx.paging.PagingData
import com.example.user_list.domain.entities.User
import com.example.user_list.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUserListUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(
        seed: String?,
        selectedGender: String?
    ) : Flow<PagingData<User>> = repository.getListUser(seed, selectedGender)
}