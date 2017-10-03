package com.pulsetask.api

import com.pulsetask.datamodel.Article

/**
 * Created by ayoola on 29/09/2017.
 */

data class DataResponse(
        val id: String,
        val title: String,
        val subtitle: String,
        val body: String?,
        val date: String
)

data class ArticleListResponse(val items: List<DataResponse>)

data class ArticleResponse(val item: DataResponse)

fun toArticle(articleResponse: ArticleResponse): Article =
        Article(articleResponse.item.id, articleResponse.item.title, articleResponse.item.subtitle, articleResponse.item.body, articleResponse.item.date)

fun toArticleList(articleListResponse: ArticleListResponse): List<Article> =
        articleListResponse.items.mapTo(mutableListOf()) { Article(it.id, it.title, it.subtitle, it.body, it.date) }

fun errorArticleResponse(): Article = toArticle(ArticleResponse(DataResponse("","","","","")))

fun errorArticleListResponse(): List<Article> = toArticleList(ArticleListResponse(emptyList()))

