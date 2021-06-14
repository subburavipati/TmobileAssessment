package com.tmobile.subbu.model.datasource.remote.retrofit

import com.tmobile.subbu.model.data.CardsResponse
import retrofit2.http.GET

/**
 * Retrofit API class which helps us in getting the data from the server
 */
interface CardsApi {
    @GET("test/home")
    suspend fun getCards(): CardsResponse
}