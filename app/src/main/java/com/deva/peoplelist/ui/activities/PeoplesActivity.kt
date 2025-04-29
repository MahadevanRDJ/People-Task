package com.deva.peoplelist.ui.activities

import NavigationUtils
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import com.deva.peoplelist.R
import com.deva.peoplelist.data.model.Peoples
import com.deva.peoplelist.data.model.dummyjson.User
import com.deva.peoplelist.data.model.dummyjson.Users
import com.deva.peoplelist.data.utils.BaseResponse
import com.deva.peoplelist.ui.compose.PeoplesUi
import com.deva.peoplelist.ui.theme.Primary
import com.deva.peoplelist.viewmodel.PeopleViewModel

class PeoplesActivity : ComponentActivity() {
    private val peopleViewModel by viewModels<PeopleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.light(
                scrim = this.getColor(R.color.black),
                darkScrim = this.getColor(R.color.black)
            ),
            statusBarStyle = SystemBarStyle.light(
                scrim = this.getColor(R.color.white),
                darkScrim = this.getColor(R.color.white)
            )
        )
        WindowInsetsControllerCompat(
            window,
            window.decorView
        ).isAppearanceLightStatusBars = true

        peopleViewModel.loadUserData()
        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {
                NavigationUtils(peopleViewModel)
            }
        }

    }

}

@Composable
fun PeopleList(
    peopleViewModel: PeopleViewModel,
    navController: NavController,
) {
    val peopleState by peopleViewModel.peopleList.collectAsState()
    val userState by peopleViewModel.userList.collectAsState()
    val showErrorDialog = remember { mutableStateOf(false) }
    val showProgressLoading = remember { mutableStateOf(true) }
    val errorDescription = remember { mutableStateOf("") }
    val peopleList = remember { mutableStateOf(Peoples()) }
    val userList = remember { mutableStateOf(Users()) }
    val paginatedList = remember { mutableStateOf(emptyList<User>()) }
    val filteredList = remember { mutableStateOf(emptyList<User>()) }
    val searchQuery = remember { mutableStateOf("") }
    val limit = 25



    fun onPerformSearch(query: String) {
        filteredList.value = if (query.isNotEmpty()) {
            peopleViewModel.filterUserList(query)
        } else {
            userList.value.users
        }
    }

    fun onQueryChange(newQuery: String) {
        searchQuery.value = newQuery
        if (newQuery.length >= 2) {
            onPerformSearch(newQuery)
        } else if (newQuery.isEmpty()) {
            filteredList.value = emptyList()
        }
    }

    fun onPaginate(page: Int) {
        peopleViewModel.loadUserDataByPage(skip = limit * (page - 1), limit = limit)
    }



    when (val it = peopleState) {
        is BaseResponse.Success -> {
            peopleList.value = it.data!!
            showProgressLoading.value = false
        }

        is BaseResponse.Error -> {
            errorDescription.value = it.errorDescription
            showErrorDialog.value = true
            showProgressLoading.value = false
        }

        is BaseResponse.Loading -> {
            showProgressLoading.value = true
        }

        else -> {
            showErrorDialog.value = false
        }
    }
    when (val it = userState) {
        is BaseResponse.Success -> {
            userList.value = it.data!!
            paginatedList.value = userList.value.users.subList(0,25)
            showProgressLoading.value = false
        }

        is BaseResponse.Error -> {
            errorDescription.value = getErrorMessage(it.errorCode, it.errorDescription)
            showErrorDialog.value = true

            showProgressLoading.value = false
        }

        is BaseResponse.Loading -> {
            showProgressLoading.value = true
        }

        else -> {
            showErrorDialog.value = false
        }
    }


    PeoplesUi(
        navController = navController,
        peopleList = peopleList.value.results,
        userList = paginatedList.value,
        onQueryChange = { newQuery: String ->
            onQueryChange(newQuery)
        },
        totalPages = peopleViewModel.total.intValue,
        onPageSelect = { page: Int ->
            onPaginate(page)
        },
        filteredPeople = filteredList.value,
    )

    if (showProgressLoading.value) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                color = Primary
            )
        }
    }

    if (showErrorDialog.value) {
        AlertDialog(
            onDismissRequest = { showErrorDialog.value = false },
            title = { Text(text = "PeopleList") },
            text = { Text(errorDescription.value) },
            confirmButton = {
                TextButton(onClick = { showErrorDialog.value = false }) {
                    Text("Confirm")
                }
            },
        )
    }

}

fun getErrorMessage(statusCode : Int, errorDescription : String): String {
    return when(statusCode) {
        in (500..600) -> {
            "Server side error"
        }
        in (400..500) -> {
            "Client side error"
        } else -> errorDescription
    }
}
