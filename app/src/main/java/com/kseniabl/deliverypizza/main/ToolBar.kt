package com.kseniabl.deliverypizza.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kseniabl.main.R
import com.kseniabl.themes.ContainerRed
import com.kseniabl.themes.Grey40
import com.kseniabl.themes.SelectedTextRed

@Composable
fun CollapsedTopBar(
    isCollapsed: Boolean,
    viewModel: MainViewModel
) {
    viewModel.getBanners()
    val banners by viewModel.banners.collectAsState()
    val categories = listOf("Пиво", "Комбо", "Десерты", "Напитки")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Москва",
                fontSize = 16.sp,
                fontWeight = FontWeight.Black
            )
            Spacer(Modifier.width(14.dp))
            Icon(
                painter = painterResource(R.drawable.keyboard_arrow_down),
                contentDescription = "Выбрать город",
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
            )
            Spacer(Modifier.weight(1f))
            Icon(
                painter = painterResource(R.drawable.qr_code),
                contentDescription = "Сканировать qr-код",
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
            )
        }
        BannerSection(isCollapsed, banners ?: listOf())
        CategoriesSection(categories)
    }
}

@Composable
fun BannerSection(
    isCollapsed: Boolean,
    banners: List<com.kseniabl.deliverypizza.domain.BannerImageModel>
) {
    AnimatedVisibility(visible = isCollapsed) {
        LazyRow(modifier = Modifier
            .fillMaxWidth()) {
            items(banners) {
                BannerItem(it)
            }
        }
        Spacer(Modifier.height(24.dp))
    }
}

@Composable
fun CategoriesSection(
    categories: List<String>
) {
    var selectedItemIndex by remember { mutableStateOf(0) }
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(categories) {
            val idx = categories.indexOf(it)
            Card(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = 16.dp, top = 16.dp, bottom = 10.dp)
                    .clickable {
                        selectedItemIndex = idx
                    },
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                colors =
                if (selectedItemIndex != idx)
                    CardDefaults.cardColors(containerColor = Color.White)
                else
                    CardDefaults.cardColors(containerColor = SelectedTextRed),
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
                    text = it,
                    color = if (selectedItemIndex != idx) Grey40 else ContainerRed
                )
            }
        }
    }
}

@Composable
fun BannerItem(
    banner: com.kseniabl.deliverypizza.domain.BannerImageModel
) {
    Card(
        modifier = Modifier
            .width(300.dp)
            .height(150.dp)
            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(6.dp),
    ) {
        Box(
            modifier = Modifier
        ) {
            Image(
                painter = painterResource(id = banner.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}