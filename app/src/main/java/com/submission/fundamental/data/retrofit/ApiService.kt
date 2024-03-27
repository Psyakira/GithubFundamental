package com.submission.fundamental.data.retrofit

import com.submission.fundamental.data.response.GithubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    fun searchUsers(@Query("q") query: String): Call<GithubResponse>
}