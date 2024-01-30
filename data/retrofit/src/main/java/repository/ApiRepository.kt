package repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.cft.shift2023winter.data.model.UserModel

interface ApiRepository {
    suspend fun getListUser(
        seed: String?,
        selectedGender: String?
    ) : Flow<PagingData<UserModel>>
}