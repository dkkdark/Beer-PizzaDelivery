package com.kseniabl.data.main.source

import com.kseniabl.data.entity.BeerRequest
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitRequests {
    @GET("beers")
    suspend fun getBeer(): Response<List<BeerRequest>>
}