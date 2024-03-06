package com.example.medisim.presentation.homeScreens

import android.os.Handler
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.medisim.R
import com.example.medisim.presentation.homeScreens.topNavigationScreens.profile.NavigationDrawerBody
import com.example.medisim.presentation.homeScreens.topNavigationScreens.profile.NavigationDrawerHeader
import com.example.medisim.presentation.homeScreens.topNavigationScreens.profile.ProfileViewModel
import com.example.medisim.presentation.navigation.BottomNavigation
import com.example.medisim.presentation.navigation.NavigationScreen
import com.example.medisim.presentation.navigation.Screens
import com.example.medisim.ui.theme.CommonComponent2
import com.example.medisim.ui.theme.animatedShimmerColor
import kotlinx.coroutines.launch

@Composable
fun MainScreen(appNavController: NavHostController, profileViewModel: ProfileViewModel) {

    // use it when user need to exit app to show toast to click again to exit.
    var doubleBackToExitPressedOnce = false

    // use it to close activity if user click he want to close app.
    val activity = LocalOnBackPressedDispatcherOwner.current as ComponentActivity
    val context = LocalContext.current


    // navController for bottom navigation
    val navController = rememberNavController()

    // list of Bottom navigation and take in your mind the middle
    // navigation item is floating action button, so (it not in list of screens).
    val screens = listOf(
        NavigationScreen.Home,
        NavigationScreen.Prediction,
        NavigationScreen.Drug,
        NavigationScreen.Calculator
    )

    // back stack & currentRoute to handel route of bottom navigation for user
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()


    Scaffold (
        scaffoldState = scaffoldState,
        // top app bar it contain user Profile and Chat Ai icons.
        topBar = {
            AnimatedVisibility(visible = currentRoute == NavigationScreen.Home.route) {
                TopAppBar(
                    modifier = Modifier
                        .height(40.dp)
                        .background(MaterialTheme.colorScheme.background)
                        ,
                    title = {
                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                        ){
                            Icon(
                                imageVector = Icons.Outlined.Person,
                                modifier = Modifier.clickable {
                                    scope.launch {
                                        scaffoldState.drawerState.open()
                                    }
                                },
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.Chat,
                                modifier = Modifier
                                    .padding(end = 15.dp)
                                    .clickable {
                                        appNavController.navigate(Screens.ChatAI.route)
                                    },
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }

                    },
                    backgroundColor = MaterialTheme.colorScheme.tertiary
                )
            }

        },


        // make Navigation drawer not open using horizontal swap,
        // in any screen not home
        drawerGesturesEnabled = currentRoute == NavigationScreen.Home.route,

        // Navigation drawer content
        drawerContent = {
            Column (Modifier.background(MaterialTheme.colorScheme.background)){
                NavigationDrawerHeader()
                NavigationDrawerBody(appNavController,profileViewModel)
            }
        },
        // floating action button it for the item of bottom navigation
        // we use it as center button in bottom navigation
        floatingActionButton = {
            Button (
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                enabled = true,
                onClick = {
                    // navigate to
                    navController.navigate(NavigationScreen.MedicalTest.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                },
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(
                        animatedShimmerColor(
                            shimmerColors = listOf(
                                CommonComponent2.copy(0.9f),
                                CommonComponent2.copy(0.6f),
                                CommonComponent2.copy(0.9f)
                            ),
                            durationMillis = 2000
                        )
                    ),
                contentPadding = PaddingValues(all = 17.dp)


                ){
                Box(modifier = Modifier.fillMaxSize()){
                    Icon(
                        painter = painterResource(id = NavigationScreen.MedicalTest.icon),
                        modifier = Modifier.size(120.dp),
                        tint = MaterialTheme.colorScheme.background,
                        contentDescription = "Add",
                    )
                }

            }
        },
        // bottom navigation contain screens in list and empty item in middle
        // to make space for fab.
        bottomBar = {
            BottomAppBar (
                containerColor = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                    .height(60.dp)
            ){
                screens.forEachIndexed { index,screen->
                    // if this item is middle item then make empty item
                    // to make fab free space
                    if (index == 2){
                        NavigationBarItem(
                            selected = false,
                            onClick = {},
                            icon = {},
                            enabled = false
                        )
                    }
                    NavigationBarItem(
                        selected = false,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                        },
                        icon = {
                            Column (
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxWidth()
                            ){
                                Icon(
                                    painter = painterResource(id = screen.icon) ,
                                    contentDescription = "bottom nav icon",
                                    tint = if(screen.route == currentRoute) CommonComponent2
                                    else Color.White

                                )
                                Spacer(modifier = Modifier.width(2.dp))
                                Text(
                                    text = screen.title,
                                    color = if(screen.route == currentRoute) CommonComponent2
                                    else Color.White,
                                    softWrap = false
                                )

                            }
                        }

                    )


                }

            }



        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center


    ){
        Box(modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(it)){
            BottomNavigation(bottomNavController = navController, appNavController = appNavController)
        }


        //Back Handler
        BackHandler(onBack = {
            // show toast to user to click again if need to exit and make
            // make time 2 seconds then reassign doubleBackToExitPressedOnce to false
            if (doubleBackToExitPressedOnce) {
                finishAffinity(activity)
            }
            else {
                doubleBackToExitPressedOnce = true
                Toast.makeText(context,
                    context.getString(R.string.press_again_to_exit), Toast.LENGTH_SHORT).show()
                Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
            }
        })

    }
}