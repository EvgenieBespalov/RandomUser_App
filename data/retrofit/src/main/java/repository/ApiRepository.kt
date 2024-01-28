package repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query
import ru.cft.shift2023winter.data.model.ApiResponse
import ru.cft.shift2023winter.data.model.User

interface ApiRepository {
    suspend fun getListUser(
        //numberOfUsers: String,
        page: String,
        seed: String?,
        selectedGender: String?
    ) : Flow<PagingData<User>>
}