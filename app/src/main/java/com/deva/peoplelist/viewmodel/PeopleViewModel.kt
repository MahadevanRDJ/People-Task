package com.deva.peoplelist.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.deva.peoplelist.data.model.Peoples
import com.deva.peoplelist.data.model.Results
import com.deva.peoplelist.data.model.dummyjson.User
import com.deva.peoplelist.data.model.dummyjson.Users
import com.deva.peoplelist.data.repository.PeopleRepository
import com.deva.peoplelist.data.utils.BaseResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PeopleViewModel(application: Application) : AndroidViewModel(application) {
    private val peopleRepository = PeopleRepository.getInstance()

    private val _peopleList = MutableStateFlow<BaseResponse<Peoples>>(BaseResponse.Initial())
    var peopleList: StateFlow<BaseResponse<Peoples>> = _peopleList.asStateFlow()
    private val _userList = MutableStateFlow<BaseResponse<Users>>(BaseResponse.Initial())
    var userList: StateFlow<BaseResponse<Users>> = _userList.asStateFlow()

    val total = mutableIntStateOf(0)


    fun loadPeopleData(count: Int) {
        viewModelScope.launch {
            _peopleList.value = BaseResponse.Loading()
            val response = peopleRepository.getPeopleList(count)
            _peopleList.value = response
        }
    }

    fun loadUserData() {
        viewModelScope.launch {
            _userList.value = BaseResponse.Loading()
            val response = peopleRepository.getUserList()
            when (response) {
                is BaseResponse.Success -> {
                    total.value = response.data?.total!!
                }

                else -> {

                }
            }
            _userList.value = response
        }
    }

    fun loadUserDataByPage(skip: Int, limit: Int) {
        viewModelScope.launch {
            _userList.value = BaseResponse.Loading()
            val response = peopleRepository.getUserList(skip = skip, limit = limit)
            _userList.value = response
        }
    }


    fun getPeopleByIndex(index: Int): Results {
        return when (val it = _peopleList.value) {
            is BaseResponse.Success -> {
                it.data!!.results[index]
            }

            else -> {
                Results()
            }
        }
    }

    fun filterPeopleList(query: String): List<Results> {
        return when (val peoples = peopleList.value) {
            is BaseResponse.Success -> {
                val tempList = peoples.data!!.results.toMutableList()
                tempList.filter {
                    ((it.name!!.first + it.name!!.last).contains(query, ignoreCase = true))
                }
            }

            else -> {
                emptyList()
            }
        }
    }

    fun filterUserList(query: String): List<User> {
        return when (val users = _userList.value) {
            is BaseResponse.Success -> {
                val tempList = users.data!!.users.toMutableList()
                tempList.filter {
                    (it.firstName + it.lastName).contains(query, ignoreCase = true)
                }
            }

            else -> {
                emptyList()
            }
        }
    }

    fun getUserByUserID(userId: Int): User {
        return when (val it = _userList.value) {
            is BaseResponse.Success -> {
                it.data!!.users.find {
                    (it.id == userId)
                }!!
            }

            else -> {
                User()
            }
        }
    }
}