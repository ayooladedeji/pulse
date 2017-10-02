package com.pulsetask.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by ayoola on 29/09/2017.
 */

interface DataService {

    @GET("contentList.json")
    fun getArticleList(): Single<ArticleListResponse>

    @GET("content/{id}.json")
    fun getArticle(@Path("id") id: String): Single<ArticleResponse>
}