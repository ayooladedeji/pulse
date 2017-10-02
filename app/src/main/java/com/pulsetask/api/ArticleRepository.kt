package com.pulsetask.api

import com.pulsetask.datamodel.Article
import io.reactivex.Single

/**
 * Created by ayoola on 29/09/2017.
 */
interface ArticleRepository{

    fun getArticle(id: String): Single<Article>

    fun getArticleList(): Single<List<Article>>
}