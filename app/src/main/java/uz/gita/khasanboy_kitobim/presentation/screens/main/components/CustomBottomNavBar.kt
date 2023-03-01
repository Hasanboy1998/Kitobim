package uz.gita.khasanboy_kitobim.presentation.screens.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import uz.gita.khasanboy_kitobim.R
import uz.gita.khasanboy_kitobim.ui.theme.KitobimTheme
import uz.gita.khasanboy_kitobim.ui.theme.MainColor
import uz.gita.khasanboy_kitobim.ui.theme.UnselectedColor

@Composable
fun CustomBottomNavigation(/*navController: NavController*/screen: (Screen) -> Unit) {

    
    
    
    /*val bottomNavList by remember { mutableStateOf(prepareBottomMenu()) }
    NavigationBar(
        contentColor = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        bottomNavList.forEach { item ->
            NavigationBarItem(
                icon = { Icon(painter = painterResource(id = item.icon), contentDescription = item.label) },
                label = { Text(text = item.label, fontSize = 12.sp, fontWeight = FontWeight.W500) },
                colors = NavigationBarItemDefaults.colors(selectedIconColor = MainColor, unselectedIconColor = UnselectedColor, selectedTextColor = MainColor, unselectedTextColor = UnselectedColor),
                alwaysShowLabel = currentRoute == item.label,
                selected = currentRoute == item.label,
                onClick = {
                    navController.navigate(item.label) {

                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }*/
}

private fun prepareBottomMenu(): List<BottomMenuItem> {
    val bottomMenuItemsList = arrayListOf<BottomMenuItem>()

    bottomMenuItemsList.add(BottomMenuItem(label = "Asosiy", icon = R.drawable.home_48px))
    bottomMenuItemsList.add(BottomMenuItem(label = "Yuklanganlar", icon = R.drawable.download_48px))
//    bottomMenuItemsList.add(BottomMenuItem(label = "Dastur haqida", icon = R.drawable.about))

    return bottomMenuItemsList
}

data class BottomMenuItem(val label: String, val icon: Int)