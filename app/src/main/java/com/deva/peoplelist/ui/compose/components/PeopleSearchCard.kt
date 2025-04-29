package com.deva.peoplelist.ui.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

import com.deva.peoplelist.data.model.dummyjson.User
import com.deva.peoplelist.ui.theme.Primary

@Composable
fun PeopleSearchCard(
    modifier: Modifier = Modifier,
    people: User,
    onUserSelect: () -> Unit = {},
) {

    val context = LocalContext.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp)
            .background(Primary.copy(alpha = 0.1f), shape = RoundedCornerShape(10.dp))
            .height(100.dp)
            .clickable {
                onUserSelect()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(people.image!!.replace("128", "256"))
                .build(),
            contentDescription = "User profile picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = "${people.firstName} ${people.lastName}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "${people.address!!.city}, ${people.address!!.country}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black.copy(alpha = .8f)
            )
        }
    }
}