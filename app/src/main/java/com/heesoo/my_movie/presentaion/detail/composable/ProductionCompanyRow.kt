package com.heesoo.my_movie.presentaion.detail.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.heesoo.my_movie.domain.model.ProductionCompany
import com.heesoo.my_movie.presentaion.ui.composable.image.LoadingAsyncImage

@Composable
fun ProductionCompanyRow(
    productionCompanyList: List<ProductionCompany>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(productionCompanyList) { company ->
            if (company.logoUrl.isNotEmpty()) {
                LoadingAsyncImage(
                    data = company.logoUrl,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .height(32.dp)
                        .width(64.dp)
                )
            }
        }
    }
}
