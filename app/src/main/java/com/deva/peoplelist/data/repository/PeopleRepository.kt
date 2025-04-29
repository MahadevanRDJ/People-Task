package com.deva.peoplelist.data.repository

import com.deva.peoplelist.data.model.Peoples
import com.deva.peoplelist.data.model.dummyjson.Users
import com.deva.peoplelist.data.services.KtorClient
import com.deva.peoplelist.data.utils.BaseResponse
import com.deva.peoplelist.data.utils.NetworkServiceURL
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class PeopleRepository private constructor() {

    private val ktorClient = KtorClient.getInstance()

    companion object {
        @Volatile
        private var instance: PeopleRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: PeopleRepository().also { instance = it }
            }
    }

    suspend fun getPeopleList(count: Int): BaseResponse<Peoples> {

        return try {
            val response = ktorClient.httpClient.get(NetworkServiceURL.RANDOMUSER_API.path) {
                parameter("results", count)
            }
            if (response.status.value == 200) {
                BaseResponse.Success(response.body())
            } else {
                BaseResponse.Error(
                    response.status.value, response.status.description
                )
            }
        } catch (e: Exception) {
            BaseResponse.Error(errorDescription = "exception")
        }
    }

    suspend fun getUserList(skip : Int = 0, limit : Int = 0): BaseResponse<Users> {

        return try {
            val response = ktorClient.httpClient.get(NetworkServiceURL.DUMMYJSON_USERS.path) {
                parameter("skip", skip)
                parameter("limit", limit)
            }
            if (response.status.value == 200) {
                BaseResponse.Success(response.body())
            } else {
                BaseResponse.Error(
                    response.status.value, response.status.description
                )
            }
        } catch (e: Exception) {
            BaseResponse.Error(errorDescription = "exception")
        }
    }
}