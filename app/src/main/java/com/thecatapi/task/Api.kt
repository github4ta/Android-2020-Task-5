package com.thecatapi.task

import com.thecatapi.task.data.ApiData
import com.thecatapi.task.data.Cat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheCatApi {
    @GET("/v1/images/search?limit=9&order=Desc&api_key=91589490-b97b-4392-b68a-2afd458df1d1")
    suspend fun getListOfCats(
        @Query("page") page: String?
    ): Response<List<ApiData>>
}

object TheCatApiImpl {
    var page = 10
    var listOfCats: MutableList<Cat>? = mutableListOf()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://api.thecatapi.com")
        .build()

    private val service = retrofit.create(TheCatApi::class.java)

    suspend fun getListOfCats(): List<Cat>? {
        val tmpList = withContext(Dispatchers.Main){
            service.getListOfCats(page.toString())
                .body()
                ?.map {
                    Cat(
                        it.catId,
                        it.catImageUrl
                    )
                }
        }

        return tmpList?.toMutableList().also { listOfCats = it }
    }
}