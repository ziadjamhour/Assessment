package com.example.technologyassessment.connectivity
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.technologyassessment.model.ArticleModel
import com.example.technologyassessment.utils.Loading
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    companion object {
        var articlesList: ArrayList<ArticleModel> = ArrayList()
        fun getPopularArticles(
            section: String,
            periode: String,
            key: String
        ): MutableLiveData<ArrayList<ArticleModel>> {
            val customersListLiveData: MutableLiveData<ArrayList<ArticleModel>> =
                MutableLiveData<ArrayList<ArticleModel>>()
            val call: Call<JsonObject> =
                ApiClient.getClient.getPopularArticles(section, periode, key)
            call.enqueue(object : Callback<JsonObject> {

                override fun onResponse(
                    call: Call<JsonObject>,
                    response: Response<JsonObject>
                ) {
                    if (response.body()!!.has("status") && response.body()!!
                            .get("status").asString.equals("OK")
                    ) {
                        if (response.body()!!.has("results")) {
                            val articles = response.body()!!.getAsJsonArray("results")
                            for (article in articles) {
                                articlesList.add(ArticleModel(article = article as JsonObject))
                            }
                            customersListLiveData.postValue(articlesList)
                        }
                    }
                }

                override fun onFailure(call: Call<JsonObject>?, t: Throwable?) {
                    Log.d("error", t.toString())
                    Loading.loadingInstance?.hideLoading()
                }
            })
            return customersListLiveData
        }

        fun checkParamsValidity(section: String, periode: String, key: String):Boolean{
            var retVal : Boolean = true
            if (section.isNullOrEmpty() || periode.isNullOrEmpty() || key.isNullOrEmpty()){
                retVal = false
            }
            return retVal
        }

    }


}