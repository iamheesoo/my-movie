package com.heesoo.my_movie.home.presentation.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    rightContent: (@Composable () -> Unit)? = null,
    onClickBack: (() -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 16.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        if (onClickBack != null) {
            Box(
                modifier = Modifier.clickable(
                    indication = null,
                    interactionSource = null,
                    onClick = onClickBack
                )
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        if (title != null) {
            Text(
                text = title,
                modifier = Modifier.align(Alignment.Center),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
        if (rightContent != null) {
            Box(modifier = Modifier.align(Alignment.CenterEnd)) {
                rightContent()
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun TopAppBarPreview() {
    TopAppBar(
        title = "홈",
        onClickBack = {}
    )
}