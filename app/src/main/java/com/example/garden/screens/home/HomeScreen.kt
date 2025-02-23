package com.example.garden.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.garden.screens.navigation.BottomNavGraph
import com.example.garden.screens.navigation.bottomNav


@Composable
fun HomeScreen(navController: NavHostController, navControllerBottom: NavHostController) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                bottomNav.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(imageVector = item.icon, contentDescription = "") },
                        selected = currentDestination?.hierarchy?.any { it.route == item.destination.route } == true,
                        onClick = {
                            navControllerBottom.navigate(item.destination.route)
                        }
                    )
                }
            }
        }
    ) { innerPadding->
        BottomNavGraph(navControllerBottom, navController, Modifier.padding(innerPadding))
    }

}