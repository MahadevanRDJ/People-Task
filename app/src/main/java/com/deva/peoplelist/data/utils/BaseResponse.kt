package com.deva.peoplelist.data.utils

sealed class BaseResponse<out T> {
    data class Success<out T>(val data : T ?= null) : BaseResponse<T>()
    data class Initial(val data : Nothing ?= null) : BaseResponse<Nothing>()
    data class Loading(val data : Nothing ?= null) : BaseResponse<Nothing>()
    data class Error(val errorCode : Int = -1, val errorDescription : String = "Unknown") : BaseResponse<Nothing>()
}