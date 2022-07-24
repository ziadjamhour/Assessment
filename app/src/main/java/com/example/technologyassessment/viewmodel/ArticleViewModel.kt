package com.example.technologyassessment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technologyassessment.connectivity.Repository
import com.example.technologyassessment.model.ArticleModel
import com.example.technologyassessment.utils.Constants
import java.util.ArrayList

class ArticleViewModel : ViewModel() {

    private var articleListLiveData = MutableLiveData<ArrayList<ArticleModel>>()
    fun getCustomersRepository(): LiveData<ArrayList<ArticleModel>>? {
        return articleListLiveData
    }

    init {
        articleListLiveData =
            Repository.getPopularArticles(Constants.SECTION, Constants.PERIODE, Constants.KEY)
    }
}
