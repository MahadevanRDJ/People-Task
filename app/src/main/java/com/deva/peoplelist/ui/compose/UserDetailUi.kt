package com.deva.peoplelist.ui.compose



import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.rounded.Star
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.deva.peoplelist.R
import com.deva.peoplelist.data.model.dummyjson.User
import com.deva.peoplelist.ui.compose.components.ContactInfoItem
import com.deva.peoplelist.ui.theme.Primary
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailUi(
    navController: NavController,
    user: User,
) {
    val context = LocalContext.current
    val originalDate = LocalDate.parse(user.birthDate, DateTimeFormatter.ofPattern("yyyy-M-d"))
    val formattedDate = originalDate.format(DateTimeFormatter.ofPattern("MMMM d, yyyy"))


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
                            .data(user.image!!.replace("128", "512"))
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
                            .height(250.dp)
                            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                    )


                    Box(
                        modifier = Modifier
                            .offset(y = (-80).dp)
                            .padding(8.dp)
                            .background(color = Color.White)
                            .border(width = 4.dp, color = Primary.copy(alpha = 0.5f), shape = CircleShape)
                            .constrainAs(profileSection) {
                                top.linkTo(headerSection.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }

                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(user.image)
                                .build(),
                            contentDescription = "User profile picture",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(140.dp)
                                .padding(6.dp)
                                .clip(shape = CircleShape)

                        )

                    }

                    Column(
                        modifier = Modifier.constrainAs(detailSection) {
                            top.linkTo(profileSection.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            end.linkTo(parent.end)
                        }.offset(y = (-80).dp),
                        verticalArrangement = Arrangement.SpaceBetween

                    ) {


                        Text(
                            text = user.firstName + " " + user.lastName,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            letterSpacing = 1.sp,
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                        Text(
                            text = formattedDate.toString(),
                            color = Color.Gray,
                            fontSize = 14.sp,
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
                                text = user.gender!!.capitalize(locale = Locale.current),
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
                                text = "${user.age!!} year old",
                                color = Color.Gray,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-80).dp)
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
                            value = user.username!!
                        )


                        ContactInfoItem(
                            icon = Icons.Rounded.Star,
                            iconTint = Color(0xFFFF0011),
                            label = stringResource(R.string.college),
                            value = user.university!!,
                        )


                        ContactInfoItem(
                            icon = Icons.Rounded.LocationOn,
                            iconTint = Color(0xFF4080FF),
                            label = stringResource(R.string.location),
                            value = user.address!!.city + ", " +user.address!!.state + ", " +user.address!!.country
                        )

                        ContactInfoItem(
                            icon = Icons.Filled.Info,
                            iconTint = Color(0xFFFFC107),
                            label = stringResource(R.string.unique_id),
                            value = user.id!!.toString()
                        )

                        ContactInfoItem(
                            icon = Icons.Filled.MailOutline,
                            iconTint = Color(0xFFFF80AB),
                            label = stringResource(R.string.contacts),
                            value = user.email.toString()
                        )

                        ContactInfoItem(
                            icon = Icons.Filled.Call,
                            iconTint = Color(0xFF4CAF50),
                            label = stringResource(R.string.emergency_number),
                            value = user.phone.toString()
                        )

                    }
                }
            }


        }
    )

}
