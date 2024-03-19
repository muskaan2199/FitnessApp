package com.example.elixir

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.elixir.ui.theme.ElixirTheme


class MainActivity : ComponentActivity() {
    companion object {
        val items = listOf(
            Item(
                title = "Monday",
                image = R.drawable.monday
            ),
            Item(
                title = "Tuesday",
                image = R.drawable.tuesday
            ),
            Item(
                title = "Wednesday",
                image = R.drawable.wednesday
            ),
            Item(
                title = "Thursday",
                image = R.drawable.thursday
            ),
            Item(
                title = "Friday",
                image = R.drawable.friday
            ),
            Item(
                title = "Saturday",
                image = R.drawable.saturday
            )

        )
    }
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ElixirTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navHostController = rememberNavController()
                    NavHost(
                        navController = navHostController,
                        startDestination = "workout_main") {
                        composable("workout_main") {
                            WorkoutMainScreen(navHostController = navHostController)
                        }
                        composable("workout_daily") {
                            WorkoutDailyScreen()
                        }

                    }
                }
            }
        }
    }
}






@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar() {
    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false
        ),
                BottomNavigationItem(
                title = "Run",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        hasNews = false
    ),
        BottomNavigationItem(
            title = "Workout",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false
        ),
        BottomNavigationItem(
            title = "Statistics",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false
        )
    )
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "My Workout") },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
                )
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                                  selectedItemIndex = index
                                //navController.navigate(item.title)
                                  },
                        label = {
                            Text(text = item.title)
                        },
                        icon = {
                            BadgedBox(
                                badge = {
                                    if(item.badgeCount != null) {
                                        Badge {
                                            Text(text = item.badgeCount.toString())
                                        }

                                    }
                                    else if(item.hasNews) {
                                        Badge ()
                                    }

                                }) {
                                    Icon(
                                        imageVector = if(index == selectedItemIndex) {
                                                                                     item.selectedIcon
                                                                                     }
                                        else {
                                             item.unselectedIcon
                                             }, contentDescription = item.title)
                            }
                        })
                }

            }
        }
    ) { values ->
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(values)) {
            items(7) {
                Text(
                    text = "Day$it",
                    modifier = Modifier.padding(16.dp)
                )

            }
        }

    }
}