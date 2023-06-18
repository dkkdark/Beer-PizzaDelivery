package com.kseniabl.main.menu.presentation

import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.kseniabl.main.R
import com.kseniabl.main.menu.entity.BeerModel
import com.kseniabl.themes.ContainerRed
import com.kseniabl.themes.GreyText
import kotlinx.coroutines.launch

@Composable
fun MenuScreen(
    listState: LazyListState,
    menuViewModel: MenuViewModel
) {
    menuViewModel.getBeer()
    val beer by menuViewModel.beerList.collectAsState()
    val error by menuViewModel.error.collectAsState(null)

    // error handling here

    FoodList(listState, beer)
}

@Composable
fun FoodList(listState: LazyListState, beer: List<BeerModel>?) {
    LazyColumn(modifier = Modifier.fillMaxSize(), state = listState) {
        items(beer ?: listOf()) {
            FoodItem(it)
        }
    }
}

@Composable
fun FoodItem(item: BeerModel) {
    var loadError by remember { mutableStateOf(false) }

    val syncPainter = painterResource(id = R.drawable.no_image)
    val asyncPainter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = item.imageUrl)
                .apply(block = fun ImageRequest.Builder.() {
                    listener(
                        onError = { _, _ ->
                            loadError = true
                        },
                        onCancel = {
                            loadError = true
                        }
                    )
                }).build()
        )

    Row(modifier = Modifier
        .wrapContentHeight()
        .fillMaxWidth()
        .padding(15.dp)) {
        Image(
            painter = if (loadError) syncPainter else asyncPainter,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(135.dp)
                .width(135.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(Modifier.fillMaxSize()) {
            Text(text = item.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = item.description,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp,
                color = GreyText
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedButton(
                modifier = Modifier.align(Alignment.End),
                onClick = { /*TODO*/ },
                border = BorderStroke(1.dp, ContainerRed),
                shape = RoundedCornerShape(20)
            ) {
                Text( text = "от 350 р",
                color = ContainerRed)
            }
        }
    }
}