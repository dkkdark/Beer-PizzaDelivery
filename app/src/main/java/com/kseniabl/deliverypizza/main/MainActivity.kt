package com.kseniabl.deliverypizza.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kseniabl.deliverypizza.R
import com.kseniabl.deliverypizza.main.navigation.NavGraph
import com.kseniabl.themes.ContainerRed
import com.kseniabl.themes.DeliveryPizzaTheme
import com.kseniabl.themes.Grey20
import com.kseniabl.themes.Grey80
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeliveryPizzaTheme {
                val navController = rememberNavController()
                val viewModel = hiltViewModel<MainViewModel>()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    // bottom bar
                    BaseScreen(navController, viewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScreen(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    val listState = rememberLazyListState()
    val isCollapsed: Boolean by remember {
        derivedStateOf { listState.firstVisibleItemIndex == 0 }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { CollapsedTopBar(isCollapsed, viewModel) },
        bottomBar = { BottomNavBar() },
        containerColor = Color.White
    ) { paddingValues ->
        Box(Modifier.padding(paddingValues)) {
            NavGraph(navController = navController, listState)
        }
    }
}

@Composable
fun BottomNavBar() {
    val items = listOf(
        BottomNavItem(icon = R.drawable.menu_item, label = "Меню"),
        BottomNavItem(icon = R.drawable.profile, label = "Профиль"),
        BottomNavItem(icon = R.drawable.basket, label = "Корзина"),
    )

    var selectedItemIndex by remember { mutableStateOf(0) }

    BottomAppBar(
        containerColor = Grey80,
        modifier = Modifier.height(56.dp)
    ) {
        items.forEachIndexed { index, item ->
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        selectedItemIndex = index
                        // Navigation integration here
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val isSelected = selectedItemIndex == index

                Icon(
                    painter = painterResource(id = item.icon),
                    contentDescription = item.label,
                    tint = if (isSelected) ContainerRed else Grey20,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = item.label,
                    fontSize = 12.sp,
                    color = if (isSelected) ContainerRed else Grey20,
                )
            }
        }
    }
}

data class BottomNavItem(val icon: Int, val label: String)
