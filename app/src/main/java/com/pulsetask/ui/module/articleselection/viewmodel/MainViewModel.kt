package com.pulsetask.ui.module.articleselection.viewmodel

import android.arch.lifecycle.ViewModel
import com.pulsetask.ui.module.articleselection.IArticleSelection
import com.pulsetask.api.ArticleRepository
import com.pulsetask.api.ArticleRepositoryImpl
import com.pulsetask.api.errorArticleListResponse
import com.pulsetask.datamodel.Article
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import java.lang.ref.WeakReference

/**
 * Created by ayoola on 29/09/2017.
 */

class MainViewModel : ViewModel(), IArticleSelection.ViewModel {

    private var articleRepository: ArticleRepository = ArticleRepositoryImpl()
    override val articleListValue: BehaviorSubject<List<Article>> = BehaviorSubject.create()
    override lateinit var viewReference: WeakReference<IArticleSelection.View>

    override fun getArticle(id: String): Single<Article> {
        val view: IArticleSelection.View? = viewReference.get()
        view?.isLoading(true)
        return articleRepository
                .getArticle(id)
                .doAfterSuccess { view?.isLoading(false) }
    }

    override fun loadArticleList() {
        val view: IArticleSelection.View? = viewReference.get()
        articleRepository
                .getArticleList()
                .subscribe { articleList ->
                    when(articleList){
                        errorArticleListResponse() -> {
                            view?.isLoading(false)
                            view?.showError()
                        } else -> articleListValue.onNext(articleList)
                    }
                }
    }
}