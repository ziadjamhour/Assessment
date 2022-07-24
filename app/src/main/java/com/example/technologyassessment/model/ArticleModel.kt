package com.example.technologyassessment.model

import android.os.Parcelable
import com.google.gson.JsonObject
import java.io.Serializable

data class ArticleModel (
    var title: String? = "",
    var abstract: String? = "",
    var publishedDate: String? = "",
    var source: String? = ""
) : Serializable{
    constructor(article: JsonObject) : this() {
        this.title = article.get("title").asString
        this.abstract = article.get("abstract").asString
        this.source = article.get("source").asString
        if (article.get("published_date") != null){
            this.publishedDate =article.get("published_date").asString
        }
    }

}
