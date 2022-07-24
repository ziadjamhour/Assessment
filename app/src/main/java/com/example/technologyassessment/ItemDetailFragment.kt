package com.example.technologyassessment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.technologyassessment.model.ArticleModel

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [ItemDetailActivity]
 * on handsets.
 */
class ItemDetailFragment : Fragment() {

    private var article: ArticleModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ARTICLE)) {
                article = it.getSerializable(ARG_ARTICLE) as ArticleModel?
                activity?.findViewById<TextView>(R.id.txtSource)?.text = article?.source ?: ""
            }
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        article?.let {
            rootView.findViewById<TextView>(R.id.txtDetailTitle).text = it.title
            rootView.findViewById<TextView>(R.id.txtDetailAbstract).text = it.abstract
            rootView.findViewById<TextView>(R.id.txtDetailDate).text = it.publishedDate
        }

        return rootView
    }

    companion object {
        const val ARG_ARTICLE = "obj_article"
    }
}