package com.deva.peoplelist.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.deva.peoplelist.R
import com.deva.peoplelist.data.model.Results
import com.deva.peoplelist.ui.compose.components.ContactInfoItem
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeopleDetailUi(
    navController: NavController,
    people: Results,
) {
    val context = LocalContext.current

    val instant = Instant.parse(people.dob!!.date.toString())
    val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    val formattedDate = localDateTime.format(DateTimeFormatter.ofPattern("MMMM d, yyyy")).toString()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(),
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    Icon(
                        Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        },
                    )
                },
                actions = {
                    Row {

                    }
                }
            )
        },
        content = { innerPadding ->

            innerPadding

            Column(
                modifier = Modifier.verticalScroll(state = rememberScrollState())
            ) {
                ConstraintLayout {
                    val (headerSection, profileSection, detailSection) = createRefs()

                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(people.picture!!.large)
                            .build(),
                        contentDescription = "User profile picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .constrainAs(headerSection) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                            .height(300.dp)
                            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                    )


                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .offset(y = (-40).dp)
                            .constrainAs(profileSection) {
                                top.linkTo(headerSection.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }

                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(people.picture!!.medium)
                                .build(),
                            contentDescription = "User profile picture",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(80.dp)
                                .clip(shape = CircleShape)
                        )

                    }

                    Column(
                        modifier = Modifier.constrainAs(detailSection) {
                            top.linkTo(profileSection.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            end.linkTo(parent.end)
                        },
                        verticalArrangement = Arrangement.SpaceBetween

                    ) {
                        Text(
                            text = formattedDate,
                            color = Color.Gray,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .align(Alignment.CenterHorizontally)
                        )

                        Text(
                            text = people.name!!.first + " " + people.name!!.last,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            letterSpacing = 1.sp,
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .align(Alignment.CenterHorizontally)
                        )

                        Row(
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .align(Alignment.CenterHorizontally),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = people.gender!!.capitalize(locale = Locale.current),
                                color = Color.Gray,
                                fontSize = 14.sp
                            )
                            Text(
                                text = "•",
                                color = Color.Gray,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(horizontal = 4.dp)
                            )
                            Text(
                                text = "${people.dob!!.age} year old",
                                color = Color.Gray,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        ContactInfoItem(
                            icon = Icons.Rounded.AccountCircle,
                            iconTint = Color(0xFF008811),
                            label = stringResource(R.string.account),
                            value = people.login!!.username!!
                        )

                        ContactInfoItem(
                            icon = Icons.Rounded.Lock,
                            iconTint = Color(0xFFFF0011),
                            label = stringResource(R.string.password),
                            value = people.login!!.password!!,
                            isPassword = true
                        )


                        ContactInfoItem(
                            icon = Icons.Rounded.LocationOn,
                            iconTint = Color(0xFF4080FF),
                            label = stringResource(R.string.location),
                            value = people.location!!.city + ", " +people.location!!.state + ", " +people.location!!.country
                        )

                        ContactInfoItem(
                            icon = Icons.Filled.Info,
                            iconTint = Color(0xFFFFC107),
                            label = stringResource(R.string.unique_id),
                            value = people.id!!.value.toString()
                        )

                        ContactInfoItem(
                            icon = Icons.Filled.MailOutline,
                            iconTint = Color(0xFFFF80AB),
                            label = stringResource(R.string.contacts),
                            value = people.email.toString()
                        )

                        ContactInfoItem(
                            icon = Icons.Filled.Call,
                            iconTint = Color(0xFF4CAF50),
                            label = stringResource(R.string.emergency_number),
                            value = people.phone + " | " + people.cell
                        )

                    }
                }
            }


        }
    )

}

@Preview(showBackground = true)
@Composable
fun PreviewUserProfile() {
//    PeopleDetailUi(people = Results())
}