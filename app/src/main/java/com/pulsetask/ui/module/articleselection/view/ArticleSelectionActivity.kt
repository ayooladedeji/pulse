package com.pulsetask.ui.module.articleselection.view

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.pulsetask.*
import com.pulsetask.ui.module.articleselection.IArticleSelection
import com.pulsetask.datamodel.Article
import com.pulsetask.ui.module.articleselection.viewmodel.MainViewModel
import com.pulsetask.ui.module.articleselection.view.adapters.ArticleListAdapter
import com.pulsetask.ui.dialogs.SimpleDialog
import kotlinx.android.synthetic.main.activity_article_selection.*
import android.view.View
import com.pulsetask.api.errorArticleResponse
import java.lang.ref.WeakReference

class ArticleSelectionActivity : AppCompatActivity(), IArticleSelection.View, ArticleListAdapter.IOnItemClickListener {

    private lateinit var viewModel: IArticleSelection.ViewModel
    private lateinit var adapter: ArticleListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_selection)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.viewReference = WeakReference(this)
        viewModel.loadArticleList()
        subscribeViews()

    }

    override fun showArticleDialog(articleId: String) {
        viewModel
                .getArticle(articleId)
                .subscribe { article ->
                    if (article != errorArticleResponse()) {
                        ArticleDialogFragment()
                                .newInstance(article)
                                .show(supportFragmentManager, ArticleDialogFragment::class.simpleName)
                    } else {
                        isLoading(false)
                        showError()
                    }
                }
    }

    override fun updateRecyclerView(items: List<Article>) {
        try {
            adapter.update(items)
        } catch (e: UninitializedPropertyAccessException) {
            adapter = ArticleListAdapter(items, this)
            recycler_view.layoutManager = LinearLayoutManager(this)
            recycler_view.adapter = adapter
        }
    }

    private fun subscribeViews() {
        swipe_refresh.setOnRefreshListener { swipe_refresh.isRefreshing = false; viewModel.loadArticleList() }
        viewModel
                .articleListValue
                .subscribe { updateRecyclerView(it) }
    }

    override fun showError() = SimpleDialog.show(this, getString(R.string.error_text), getString(R.string.error_message), true)

    override fun onItemClick(position: Int) {
        showArticleDialog(adapter.getItemAt(position).id)
    }

    override fun isLoading(show: Boolean) {
        progress_bar.visibility = if (show) View.VISIBLE else View.GONE
    }

}
