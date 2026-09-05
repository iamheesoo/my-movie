package com.heesoo.my_movie.presentaion.home.composable

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.heesoo.my_movie.domain.model.Movie
import com.heesoo.my_movie.presentaion.ui.composable.image.LoadingAsyncImage
import kotlin.math.absoluteValue

@Composable
fun MovieHorizontalPager(
    pagerState: PagerState,
    movieList: List<Movie>,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Crossfade(
            targetState = movieList.getOrNull(pagerState.currentPage)?.backdropUrl,
            animationSpec = tween(durationMillis = 400),
            modifier = Modifier.fillMaxSize()
        ) { backdropUrl ->
            if (backdropUrl != null) {
                LoadingAsyncImage(
                    data = backdropUrl,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 64.dp),
            pageSpacing = 8.dp,
            beyondViewportPageCount = 1
        ) { currentPage ->
            val movie = movieList.getOrNull(currentPage)
            val pageOffset = pagerState.getOffsetDistanceInPages(currentPage)
            val focusFraction = 1f - pageOffset.absoluteValue.coerceIn(0f, 1f)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f / 3f)
                    .graphicsLayer {
                        val scale = lerp(0.85f, 1f, focusFraction)
                        scaleX = scale
                        scaleY = scale
                        alpha = lerp(0.6f, 1f, focusFraction)
                    }
                    .shadow(
                        elevation = lerp(0f, 12f, focusFraction).dp,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clip(RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                if (movie != null) {
                    LoadingAsyncImage(
                        data = movie.posterUrl,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Icon(
                        imageVector = Icons.Rounded.Warning,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondary
                    )
                }
            }
        }
    }
}

@Composable
@Preview
private fun MovieHorizontalPagerPreview() {
    MovieHorizontalPager(
        pagerState = rememberPagerState() { 1 },
        movieList = listOf()
    )
}