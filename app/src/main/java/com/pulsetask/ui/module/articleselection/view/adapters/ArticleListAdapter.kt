package com.pulsetask.ui.module.articleselection.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.pulsetask.R

import com.pulsetask.datamodel.Article

import java.util.ArrayList

/**
 * Created by ayoola on 30/09/2017.
 */

class ArticleListAdapter(items: List<Article>, private val onItemClickListener: IOnItemClickListener) : RecyclerView.Adapter<ArticleListAdapter.ViewHolder>() {

    private val items = ArrayList<Article>()

    init {
        this.items.addAll(items)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = getItemAt(position)
        holder.articleTitle.text = article.title

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(v)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var articleTitle: TextView = itemView.findViewById(R.id.article_title)

        init {
            itemView.setOnClickListener(this)

        }

        override fun onClick(view: View) {
            onItemClickListener.onItemClick(this.layoutPosition)
        }
    }

    fun getItemAt(position: Int): Article = items[position]

    override fun getItemCount(): Int = items.size

    interface IOnItemClickListener {
        fun onItemClick(position: Int)
    }


}
