package com.deva.peoplelist.ui.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.deva.peoplelist.data.model.Results
import com.deva.peoplelist.data.model.dummyjson.User
import com.deva.peoplelist.ui.theme.Primary
import com.deva.peoplelist.ui.theme.White

@Composable
fun PeopleGrid(people: Results, index: Int = 0, onPeopleClick : () -> Unit = {}) {
    val context = LocalContext.current
    var imageRatio by remember { mutableFloatStateOf(1f) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight().clickable {
                onPeopleClick()
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(people.picture!!.large)
                    .listener(
                        onSuccess = { _, result ->
                            val drawable = result.drawable
                            val width = drawable.intrinsicWidth.toFloat()
                            val height = drawable.intrinsicHeight.toFloat()
                            /*Log.d("TAG", "PeopleCard: $imageRatio $width $height")*/
                            imageRatio = width / height
                        }
                    )
                    .build(),
                contentDescription = "User profile picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(if ((index + 1) % 3 == 0) imageRatio else 0.8f)
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            )

            Column(modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .background(Color.Black.copy(alpha = 0.3f),)
                .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = "${people.name!!.first} ${people.name!!.last}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = White
                )
                Text(
                    text = "${people.location!!.city}, ${people.location!!.country}",
                    style = MaterialTheme.typography.bodySmall,
                    color = White.copy(alpha = .8f)
                )
            }
        }
    }
}

@Composable
fun UserGrid(user: User, index: Int = 0, onUserClick : () -> Unit = {}) {
    val context = LocalContext.current
    var imageRatio by remember { mutableFloatStateOf(1f) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight().clickable {
                onUserClick()
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.background(
                color = Primary.copy(alpha = .03f)
            )
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(user.image!!.replace("128", "512"))
                    .listener(
                        onSuccess = { _, result ->
                            val drawable = result.drawable
                            val width = drawable.intrinsicWidth.toFloat()
                            val height = drawable.intrinsicHeight.toFloat()
                            /*Log.d("TAG", "PeopleCard: $imageRatio $width $height")*/
                            imageRatio = width / height
                        }
                    )
                    .build(),
                contentDescription = "User profile picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .aspectRatio(if ((index + 1) % 3 == 0) imageRatio else 0.8f)
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            )
            HorizontalDivider(
                color = Primary.copy(alpha = 0.8f),
                thickness = 2.5.dp
            )

            Column(modifier = Modifier
                .fillMaxWidth().background(color = White.copy(alpha = .7f)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = "${user.firstName} ${user.lastName}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "${user.address!!.city}, ${user.address!!.country}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black.copy(alpha = .7f)
                )
            }
        }
    }
}
