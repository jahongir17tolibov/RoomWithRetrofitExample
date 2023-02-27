package com.example.roomwithretrofitexample.data.remote

import com.example.roomwithretrofitexample.models.CryptoIncomingModel
import com.example.roomwithretrofitexample.networking.ApiService
import com.example.roomwithretrofitexample.models.Result
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException
import javax.inject.Inject

/**
 * fetches data from remote source
 */
class RemoteDataSource @Inject constructor(private val retrofit: Retrofit) {

    companion object {
        fun parseError(response: Response<*>, retrofit: Retrofit): Error? {
            val converter =
                retrofit.responseBodyConverter<Error>(Error::class.java, arrayOfNulls(0))
            return try {
                converter.convert(response.errorBody()!!)
            } catch (e: IOException) {
                Error()
            }
        }
    }

    suspend fun fetchCryptoData(): Result<CryptoIncomingModel> {
        val cryptoService = retrofit.create(ApiService::class.java)
        return getResponse(
            request = { cryptoService.getCryptoApi() },
            "Error fetching Movie list"
        )
    }

    private suspend fun <T> getResponse(
        request: suspend () -> Response<T>,
        defaultErrorMessage: String
    ): Result<T> {
        return try {
            val response = request.invoke()
            if (response.isSuccessful) {
                return Result.success(response.body())
            } else {
                val errorResponse = parseError(response, retrofit)
                Result.error(errorResponse?.localizedMessage ?: defaultErrorMessage, errorResponse)
            }
        } catch (e: Throwable) {
            Result.error("Unknown error", null)
        }
    }

}