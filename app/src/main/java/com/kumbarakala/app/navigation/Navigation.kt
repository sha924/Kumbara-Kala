package com.kumbarakala.app.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kumbarakala.app.ui.components.BottomNav
import com.kumbarakala.app.ui.screens.HomeScreen
import com.kumbarakala.app.ui.screens.LoginScreen
import com.kumbarakala.app.ui.screens.ProductDetailScreen
import com.kumbarakala.app.ui.screens.ProductScreen
import com.kumbarakala.app.ui.screens.ProfileScreen
import com.kumbarakala.app.ui.screens.SplashScreen
import com.kumbarakala.app.ui.screens.StoryCardScreen
import com.kumbarakala.app.ui.screens.UploadProductScreen
import com.kumbarakala.app.viewmodel.AuthViewModel
import com.kumbarakala.app.viewmodel.ProductViewModel
import com.kumbarakala.app.viewmodel.StoryCardViewModel

sealed class Route(val path: String) {
    data object Splash : Route("splash")
    data object Login : Route("login")
    data object Home : Route("home")
    data object Products : Route("products")
    data object Detail : Route("product/{productId}") {
        fun create(productId: String) = "product/$productId"
    }
    data object Story : Route("story")
    data object StoryForProduct : Route("story/{productId}") {
        fun create(productId: String) = "story/$productId"
    }
    data object Profile : Route("profile")
    data object Upload : Route("upload")
}






@Composable
fun KumbaraNavigation() {
    val navController = rememberNavController()
    val productViewModel: ProductViewModel = viewModel()
    val authViewModel: AuthViewModel = viewModel()
    val storyCardViewModel: StoryCardViewModel = viewModel()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val showBottomBar = currentRoute in listOf(Route.Home.path, Route.Products.path, Route.Story.path, Route.Profile.path)

    Scaffold(
        bottomBar = {
            if (showBottomBar) BottomNav(navController, backStackEntry?.destination)
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Route.Splash.path,
            modifier = Modifier.padding(padding)
        ) {
            composable(Route.Splash.path) {
                SplashScreen(onFinished = {
                    navController.navigate(Route.Login.path) {
                        popUpTo(Route.Splash.path) { inclusive = true }
                    }
                })
            }
            composable(Route.Login.path) {
                LoginScreen(
                    viewModel = authViewModel,
                    onAuthenticated = {
                        navController.navigate(Route.Home.path) {
                            popUpTo(Route.Login.path) { inclusive = true }
                        }
                    }
                )
            }
            composable(Route.Home.path) {
                val state by productViewModel.uiState.collectAsStateWithLifecycle()
                HomeScreen(
                    state = state,
                    onSearch = productViewModel::search,
                    onCategory = productViewModel::selectCategory,
                    onProductClick = { navController.navigate(Route.Detail.create(it.id)) },
                    onFavorite = productViewModel::toggleFavorite,
                    onUploadClick = { navController.navigate(Route.Upload.path) }
                )
            }
            composable(Route.Products.path) {
                val state by productViewModel.uiState.collectAsStateWithLifecycle()
                ProductScreen(
                    state = state,
                    onSearch = productViewModel::search,
                    onCategory = productViewModel::selectCategory,
                    onProductClick = { navController.navigate(Route.Detail.create(it.id)) },
                    onFavorite = productViewModel::toggleFavorite,
                    onUploadClick = { navController.navigate(Route.Upload.path) }
                )
            }
            composable(
                route = Route.Detail.path,
                arguments = listOf(navArgument("productId") { type = NavType.StringType })
            ) { entry ->
                val id = entry.arguments?.getString("productId").orEmpty()
                productViewModel.selectProduct(id)
                val state by productViewModel.uiState.collectAsStateWithLifecycle()
                ProductDetailScreen(
                    product = state.selectedProduct,
                    healthTip = state.healthTip,
                    onBack = { navController.popBackStack() },
                    onFavorite = { productViewModel.toggleFavorite(it) },
                    onGenerateStory = { navController.navigate(Route.StoryForProduct.create(it.id)) }
                )
            }
            composable(Route.Story.path) {
                val state by productViewModel.uiState.collectAsStateWithLifecycle()
                StoryCardScreen(
                    product = state.products.firstOrNull(),
                    viewModel = storyCardViewModel
                )
            }
            composable(
                route = Route.StoryForProduct.path,
                arguments = listOf(navArgument("productId") { type = NavType.StringType })
            ) { entry ->
                val id = entry.arguments?.getString("productId").orEmpty()
                productViewModel.selectProduct(id)
                val state by productViewModel.uiState.collectAsStateWithLifecycle()
                StoryCardScreen(product = state.selectedProduct, viewModel = storyCardViewModel)
            }
            composable(Route.Profile.path) {
                val state by productViewModel.uiState.collectAsStateWithLifecycle()
                ProfileScreen(products = state.products)
            }
            composable(Route.Upload.path) {
                UploadProductScreen(
                    onBack = { navController.popBackStack() },
                    onSave = { product, uri ->
                        productViewModel.addProduct(product, uri)
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
