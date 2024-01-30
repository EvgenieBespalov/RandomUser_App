package com.example.user_list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.user_list.domain.usecase.GetUserListUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class UserListScreenViewModel(
    private val getUserListUseCase: GetUserListUseCase
) : ViewModel() {

    private val _state: MutableLiveData<UserListUiState> =
        MutableLiveData(UserListUiState.Initial)
    val state: LiveData<UserListUiState> = _state

    fun initial() {
        viewModelScope.launch {
            _state.value = UserListUiState.Initial
        }
    }

    fun getUserList(
        seed: String?,
        selectedGender: String?
    ) {
        viewModelScope.launch {
            _state.value = UserListUiState.Loading

            try {
                val listImages = getUserListUseCase(
                    seed = seed,
                    selectedGender = selectedGender
                )
                _state.value = UserListUiState.Content(listImages)
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                _state.value = UserListUiState.Error(ex.message)
            }

        }
    }
}