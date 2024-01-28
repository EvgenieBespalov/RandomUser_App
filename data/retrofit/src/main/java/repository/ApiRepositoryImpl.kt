package repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import data_source.UserApi
import kotlinx.coroutines.flow.Flow
import paging_source.UserPagingSource
import ru.cft.shift2023winter.data.model.ApiResponse
import ru.cft.shift2023winter.data.model.User
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : ApiRepository {
    override suspend fun getListUser(
        //numberOfUsers: String,
        page: String,
        seed: String?,
        selectedGender: String?
    ): Flow<PagingData<User>> =
        Pager(PagingConfig(10)){
            UserPagingSource(
                userApi = userApi,
                seed = seed,
                selectedGender = selectedGender
            )
        }.flow
}