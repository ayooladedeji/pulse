package com.pulsetask.api
import android.util.Log
import com.pulsetask.datamodel.Article
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
/**
 * Created by ayoola on 29/09/2017.
 */
class ArticleRepositoryImpl : ArticleRepository {

    private val dataService: DataService = ServiceGenerator.dataService

    override fun getArticle(id: String): Single<Article> =
            dataService
                    .getArticle(id)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .retry(20)
                    .map { toArticle(it) }
                    .onErrorReturn { t ->  Log.e(ArticleRepositoryImpl::class.simpleName, t.message); errorArticleResponse()}

    override fun getArticleList(): Single<List<Article>> =
            dataService
                    .getArticleList()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .retry(20)
                    .map { toArticleList(it) }
                    .onErrorReturn { t ->  Log.e(ArticleRepositoryImpl::class.simpleName, t.message); errorArticleListResponse()}

}