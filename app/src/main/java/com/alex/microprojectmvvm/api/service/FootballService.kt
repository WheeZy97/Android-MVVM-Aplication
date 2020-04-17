package com.alex.microprojectmvvm.api.service

import com.alex.microprojectmvvm.model.realm.FootballMatch
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface FootballService {

    @Headers("Content-Type: application/json")
    @GET("video-api/v1/")
    fun getFootballMatches() : Observable<List<FootballMatch>>
}