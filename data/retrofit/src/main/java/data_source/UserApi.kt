package data_source

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.cft.shift2023winter.data.model.ApiResponse

interface UserApi {
    @GET("/")
    suspend fun getListUser(
        //@Query("results") numberOfUsers: String,
        @Query("page") page: String,
        @Query("seed") seed: String?,
        @Query("gender") selectedGender: String?
    ): Response<ApiResponse>
}