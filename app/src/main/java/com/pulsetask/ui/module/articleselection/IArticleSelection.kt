package com.pulsetask.ui.module.articleselection

import com.pulsetask.datamodel.Article
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import java.lang.ref.WeakReference

/**
 * Created by ayoola on 29/09/2017.
 */
interface IArticleSelection {

    interface ViewModel{

        val articleListValue: BehaviorSubject<List<Article>>

        var viewReference: WeakReference<View>

        fun loadArticleList()

        fun getArticle(id: String): Single<Article>

    }

    interface View {

        fun showArticleDialog(articleId: String)

        fun updateRecyclerView(items: List<Article>)

        fun showError()

        fun isLoading(show: Boolean)
    }

}