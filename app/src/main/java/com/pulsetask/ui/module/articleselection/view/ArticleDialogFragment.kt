package com.pulsetask.ui.module.articleselection.view

import android.os.Bundle
import android.app.Dialog
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import com.pulsetask.R
import com.pulsetask.datamodel.Article


/**
 * Created by ayoola on 01/10/2017.
 */
class ArticleDialogFragment : DialogFragment() {

    fun newInstance(article: Article): ArticleDialogFragment{
        val fragment = ArticleDialogFragment()
        val args = Bundle()
        args.putParcelable(Article::class.simpleName, article)
        fragment.arguments = args
        return fragment
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val article: Article = arguments.getParcelable(Article::class.simpleName)
        return AlertDialog
                .Builder(activity)
                .setTitle(article.subtitle)
                .setMessage(article.body + "\n\n" + article.date)
                .setNegativeButton(getString(R.string.dismiss_text), { dialog, _ -> dialog.dismiss() })
                .create()
    }
}