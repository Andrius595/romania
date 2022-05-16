package edu.ktu.romania.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import edu.ktu.romania.data.NavItem
import edu.ktu.romania.ui.theme.mainBrightWhite

@Composable
fun BottomNavigationBar(
    menu: List<NavItem>,
    navController: NavController,
    modifier: Modifier= Modifier,
    onClick: (NavItem) -> Unit
    )
{
    val backStackEntry = navController.currentBackStackEntryAsState()

    BottomNavigation(
        modifier = Modifier,
        backgroundColor = Color.DarkGray,
        elevation = 5.dp
    ) {
        menu.forEach{ item->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onClick(item) },
                selectedContentColor = mainBrightWhite,
                unselectedContentColor = Color.Gray,
                icon = {
                    Column(horizontalAlignment = CenterHorizontally){
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.name
                        )
                        if(selected){
                            Text(
                                item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp)
                        }
                    }
                }
            )

        }


    }

}