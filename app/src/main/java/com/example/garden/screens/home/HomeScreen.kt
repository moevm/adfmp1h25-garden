package com.example.garden.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.garden.screens.DBViewModel
import com.example.garden.screens.navigation.BottomNavGraph
import com.example.garden.screens.navigation.bottomNav
import com.example.garden.ui.theme.DarkGreen
import com.example.garden.ui.theme.LightGreen
import com.example.garden.ui.theme.White


@Composable
fun HomeScreen(navController: NavHostController, bedViewModel: DBViewModel) {
    val navControllerBottom = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = LightGreen,
                contentColor = LightGreen,
                modifier = Modifier.clip(RoundedCornerShape(size = 10.dp))
            ) {
                val navBackStackEntry by navControllerBottom.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                bottomNav.forEach { item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(item.icon),
                                contentDescription = "",
                            )
                        },
                        selected = currentDestination?.route == item.destination.route,
                        onClick = {
                            navControllerBottom.navigate(item.destination.route)
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = DarkGreen,
                            unselectedIconColor = White,
                            indicatorColor = LightGreen
                        )
                    )
                }
            }
        },
        ) { innerPadding ->
        BottomNavGraph(
            navControllerBottom,
            navController,
            Modifier.padding(innerPadding),
            bedViewModel
        )
    }
}