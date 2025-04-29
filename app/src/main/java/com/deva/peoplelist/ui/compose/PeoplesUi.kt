package com.deva.peoplelist.ui.compose

import Screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.deva.peoplelist.R
import com.deva.peoplelist.data.model.Results
import com.deva.peoplelist.data.model.dummyjson.User
import com.deva.peoplelist.ui.compose.components.PaginationBar
import com.deva.peoplelist.ui.compose.components.PeopleSearchCard
import com.deva.peoplelist.ui.compose.components.UserGrid
import com.deva.peoplelist.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeoplesUi(
    modifier: Modifier = Modifier,
    navController: NavController,
    peopleList: MutableList<Results>,
    userList: List<User>,
    onQueryChange: (String) -> Unit = {},
    onPageSelect: (Int) -> Unit = {},
    filteredPeople: List<User> = emptyList(),
    totalPages: Int
) {

    val showSearch = remember { mutableStateOf(false) }
    val searchString = remember { mutableStateOf("") }
    val currentPage = remember { mutableIntStateOf(1) }
    val localFocusManager = LocalFocusManager.current



    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            }
            ,
        topBar = {
            if (showSearch.value)
                TopAppBar(
                    modifier = Modifier.padding(
                        all = 8.dp
                    ),
                    title = {
                        OutlinedTextField(
                            onValueChange = { value ->
                                searchString.value = value
                                onQueryChange(value)
                            },
                            value = searchString.value,
                            placeholder = { Text(stringResource(R.string.search_people)) },
                            singleLine = true,
                            shape = MaterialTheme.shapes.medium,
                        )
                    },
                    actions = {
                        Row(
                        ) {
                            Text(
                                text = stringResource(R.string.cancel),
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    color = Primary
                                ),
                                modifier = Modifier.clickable {
                                    showSearch.value = false
                                }
                            )
                        }
                    }

                )
            else TopAppBar(
                modifier = Modifier.padding(vertical = 8.dp),
                title = {
                    Text(
                        text = stringResource(R.string.peoples)
                    )
                },
                actions = {
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(.2f),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Icon(
                            Icons.Rounded.Search,
                            modifier = Modifier
                                .clickable {
                                    showSearch.value = true
                                }
                                .size(32.dp),
                            contentDescription = "Search"
                        )
                        Image(
                            painter = painterResource(R.drawable.ic_weather),
                            modifier = Modifier.size(32.dp),
                            contentScale = ContentScale.Fit,
                            contentDescription = "Weather"
                        )
                    }
                }
            )
        },
        content = { innerPadding ->

            if (!showSearch.value) {

                Box(
                    modifier.background(
                        Color.Black.copy(alpha = .03f)
                    )
                ) {
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Adaptive(150.dp),
                        contentPadding = innerPadding.also { PaddingValues(12.dp) },
                        verticalItemSpacing = 10.dp,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        state = rememberLazyStaggeredGridState(

                        ),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        /* items(peopleList.size) { index ->
                             PeopleGrid(
                                 people = peopleList[index],
                                 index = index,
                                 onPeopleClick = {
                                     navController.navigate(Screen.PeopleDetails.route + "?peopleId=$index")
                                 }
                             )
                         }*/
                        items(userList.size) { index ->
                            UserGrid(
                                user = userList[index],
                                index = index,
                                onUserClick = {
                                    navigateToUserDetail(navController, userList, index)
                                }
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .height(80.dp)
                            .background(Color.White)

                    ) {
                        PaginationBar(modifier = modifier.wrapContentSize(align = Alignment.BottomCenter), onPageSelected = {
                            currentPage.intValue = it
                            onPageSelect(it)
                        }, totalPages = totalPages.div(25), currentPage = currentPage.intValue)
                    }
                }
            } else {
                if (filteredPeople.isEmpty() && searchString.value.length > 1) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.no_search_results_found),
                            color = Primary
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(
                                color = Primary.copy(alpha = .05f)
                            )
                    ) {
                        LazyColumn {
                            items(filteredPeople.size) { index ->
                                PeopleSearchCard(
                                    people = filteredPeople[index],
                                    onUserSelect = {
                                        navigateToUserDetail(navController, filteredPeople, index)
                                    }
                                )
                            }
                        }
                    }

                }
            }
        }
    )
}


private fun navigateToUserDetail(navController: NavController, users: List<User>, index: Int) {
    navController.navigate(Screen.UserDetails.route + "?userId=${users[index].id}")
}