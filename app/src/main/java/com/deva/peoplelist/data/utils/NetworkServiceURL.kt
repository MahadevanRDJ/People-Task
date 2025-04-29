package com.deva.peoplelist.data.utils

sealed class NetworkServiceURL(val path : String) {
    data object RANDOMUSER_API : NetworkServiceURL(AppConstants.BASE_URL_RANDOMUSER + "/api")
    data object DUMMYJSON_USERS : NetworkServiceURL(AppConstants.BASE_URL_DUMMYJSON + "/users")
}