package paging_source

import android.service.controls.ControlsProviderService
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import data_source.UserApi
import retrofit2.HttpException
import ru.cft.shift2023winter.data.model.ApiResponse
import ru.cft.shift2023winter.data.model.User

class UserPagingSource(
    private val userApi: UserApi,
    private val seed: String?,
    private val selectedGender: String?,
): PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        /*if (categoryId.isBlank()) {
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }*/
        try {
            val pageNumber = params.key ?: 1
            val response = userApi.getListUser(
                //numberOfUsers = "10",
                page = pageNumber.toString(),
                seed = seed,
                selectedGender = selectedGender
            )

            if (response.isSuccessful) {
                val listUsers = response.body()!!.listUsers
                val nextPageNumber = if (listUsers.isEmpty()) null else pageNumber + 1
                val prevPageNumber = if (pageNumber > 1) pageNumber - 1 else null

                return LoadResult.Page(listUsers, prevPageNumber, nextPageNumber)
            } else {
                Log.i(ControlsProviderService.TAG, "PagingSource Error: " + HttpException(response))
                return LoadResult.Error(HttpException(response))
            }
        } catch (e: HttpException) {
            Log.i(ControlsProviderService.TAG, "PagingSource HttpException: " + e)
            return LoadResult.Error(e)
        } catch (e: Exception) {
            Log.i(ControlsProviderService.TAG, "PagingSource Exception:" + e)
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }
}