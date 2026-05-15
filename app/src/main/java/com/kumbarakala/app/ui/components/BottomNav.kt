package com.kumbarakala.app.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.kumbarakala.app.navigation.Route

private data class BottomItem(val route: String, val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)

@Composable
fun BottomNav(navController: NavHostController, currentDestination: NavDestination?) {
    val items = listOf(
        BottomItem(Route.Home.path, "Home", Icons.Filled.Home),
        BottomItem(Route.Products.path, "Products", Icons.Filled.ShoppingCart),
        BottomItem(Route.Story.path, "Story", Icons.Filled.AddBox),
        BottomItem(Route.Profile.path, "Profile", Icons.Filled.Person)
    )
    NavigationBar {
        items.forEach { item ->
            val selected = currentDestination?.route == item.route
            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) }
            )
        }
    }
}
