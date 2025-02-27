package com.example.garden.screens.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.garden.screens.BedViewModel
import com.example.garden.screens.navigation.BottomNavGraph
import com.example.garden.screens.navigation.bottomNav


@Composable
fun HomeScreen(navController: NavHostController, bedViewModel: BedViewModel) {
    val navControllerBottom = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = colorScheme.primary,
                contentColor = colorScheme.primary,
                modifier = Modifier.clip(RoundedCornerShape(size = 10.dp))
            ) {
                val navBackStackEntry by navControllerBottom.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                bottomNav.forEach { item ->
                    NavigationBarItem(
                        icon = {
                            Icon(imageVector = ImageVector.vectorResource(item.icon),
                                contentDescription = "",
                                )
                               },
                        selected = currentDestination?.route == item.destination.route,
                        onClick = {
                            navControllerBottom.navigate(item.destination.route)
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = colorScheme.secondary,
                            unselectedIconColor = colorScheme.background,
                            indicatorColor = colorScheme.primary
                        )
                    )
                }
            }
        },

    ) { innerPadding->
        BottomNavGraph(navControllerBottom, navController, Modifier.padding(innerPadding),bedViewModel )
    }

}