package com.yom.ahati.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

@Composable
fun SocialButtons() {
    var isFavoriteClicked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(end = 16.dp, bottom = 16.dp) ,
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        IconButton(
            Icons.Filled.Favorite,
            onClick = { isFavoriteClicked = !isFavoriteClicked },
            isClicked = isFavoriteClicked
        )
        Spacer(modifier = Modifier.height(16.dp))
        IconButton(Icons.Filled.Add,    onClick = { isFavoriteClicked = !isFavoriteClicked },
            isClicked = isFavoriteClicked
        )
        Spacer(modifier = Modifier.height(16.dp))
        IconButton(Icons.Filled.Share, onClick = { isFavoriteClicked = !isFavoriteClicked },
            isClicked = isFavoriteClicked
        )
    }
}


@Composable
fun IconButton(icon: ImageVector, onClick: () -> Unit, isClicked: Boolean
) {
    val buttonColor = if (isClicked && icon == Icons.Filled.Favorite) Color.Red else Color.White

    Icon(
        icon,
        contentDescription = null,
        tint = buttonColor,
        modifier = Modifier.size(36.dp).clickable(onClick = onClick)
    )
}