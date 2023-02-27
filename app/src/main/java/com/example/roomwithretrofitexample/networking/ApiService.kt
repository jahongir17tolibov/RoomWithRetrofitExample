package com.example.roomwithretrofitexample.networking

import com.example.roomwithretrofitexample.models.CryptoIncomingModel
import com.example.roomwithretrofitexample.models.CryptoModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    //Crypto api
    @GET("tickers/")
    suspend fun getCryptoApi(): Response<CryptoIncomingModel>

}