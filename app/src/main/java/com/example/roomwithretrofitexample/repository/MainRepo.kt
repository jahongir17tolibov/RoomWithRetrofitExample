package com.example.roomwithretrofitexample.repository

import com.example.roomwithretrofitexample.data.local.AppDao
import com.example.roomwithretrofitexample.data.remote.RemoteDataSource
import com.example.roomwithretrofitexample.models.CryptoIncomingModel
import kotlinx.coroutines.flow.Flow
import com.example.roomwithretrofitexample.models.Result
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepo @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val appDao: AppDao
) {

    suspend fun fetchCryptos(): Flow<Result<CryptoIncomingModel>?> {
        return flow {
            emit(fetchDataCached())
            emit(Result.loading())
            val response = remoteDataSource.fetchCryptoData()

            if (response.status == Result.Status.SUCCESS) {
                response.data?.data?.let {
                    appDao.run {
                        deleteData(it)
                        insertData(it)
                    }
                }
            }
            emit(response)
        }.flowOn(IO)
    }

    private fun fetchDataCached(): Result<CryptoIncomingModel> =
        appDao.getAllData().let {
            Result.success(CryptoIncomingModel(it))
        }

}