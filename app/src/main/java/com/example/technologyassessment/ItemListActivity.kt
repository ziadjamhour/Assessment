package com.example.technologyassessment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.example.technologyassessment.model.ArticleModel
import com.example.technologyassessment.utils.Loading.Companion.loadingInstance
import com.example.technologyassessment.viewmodel.ArticleViewModel
import kotlin.collections.ArrayList
import kotlin.collections.List

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ItemListActivity : AppCompatActivity() {

    private var twoPane: Boolean = false
    private var articlesList: ArrayList<ArticleModel> = ArrayList()
    private val articleViewModel:ArticleViewModel =ArticleViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title

        if (findViewById<NestedScrollView>(R.id.item_detail_container) != null) {
            twoPane = true
        }

        loadingInstance?.showLoading(this, getString(R.string.loading_articles), cancelable = false)
        articleViewModel.getCustomersRepository()!!.observe(this) { articleResponse ->
            articlesList = articleResponse
            setupRecyclerView(findViewById(R.id.item_list))
            loadingInstance?.hideLoading()
        }

    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = ArticleRecyclerViewAdapter(this, articlesList, twoPane)
    }

    class ArticleRecyclerViewAdapter(
        private val parentActivity: ItemListActivity,
        private val values: List<ArticleModel>,
        private val twoPane: Boolean
    ) :
        RecyclerView.Adapter<ArticleRecyclerViewAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->
                val item = v.tag as ArticleModel
                if (twoPane) {
                    val fragment = ItemDetailFragment().apply {
                        arguments = Bundle().apply {
                            putString(ItemDetailFragment.ARG_ARTICLE, item.title)
                        }
                    }
                    parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit()
                } else {
                    val intent = Intent(v.context, ItemDetailActivity::class.java).apply {
                        putExtra(ItemDetailFragment.ARG_ARTICLE, item)
                    }
                    v.context.startActivity(intent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val article = values[position]
            holder.txtTitle.text = article.title
            holder.txtAbstract.text = article.abstract
            holder.txtPublisheddate.text = article.publishedDate

            with(holder.itemView) {
                tag = article
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val txtTitle: TextView = view.findViewById(R.id.txtTitle)
            val txtAbstract: TextView = view.findViewById(R.id.txtAbstract)
            val txtPublisheddate: TextView = view.findViewById(R.id.txtPublisheddate)
        }
    }
}