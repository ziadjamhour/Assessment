package com.example.technologyassessment.connectivity

import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("{section}/{periode}.json")
    fun getPopularArticles(
        @Path("section") section: String,
        @Path("periode") periode: String?,
        @Query("api-key") key: String?
    ): Call<JsonObject>
}