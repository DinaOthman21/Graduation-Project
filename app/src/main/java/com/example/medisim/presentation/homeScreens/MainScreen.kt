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
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
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
import com.example.medisim.presentation.navigation.BottomNavigation
import com.example.medisim.presentation.navigation.NavigationScreen
import com.example.medisim.ui.theme.CommonComponent2
import com.example.medisim.ui.theme.animatedShimmerColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MainScreen(appNavController: NavHostController) {

    val systemUiController = rememberSystemUiController()
    val statusBarColor = MaterialTheme.colorScheme.tertiary // Set desired status bar color

    SideEffect {
        // Update the status bar color when the screen is first composed
        systemUiController.setStatusBarColor(statusBarColor)
    }



    var doubleBackToExitPressedOnce = false
    val activity = LocalOnBackPressedDispatcherOwner.current as ComponentActivity
    val context = LocalContext.current


    val navController = rememberNavController()
    val screens = listOf(
        NavigationScreen.Home,
        NavigationScreen.Prediction,
        NavigationScreen.Medicine,
        NavigationScreen.Calculator
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    Scaffold (
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
                                },
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.Chat,
                                modifier = Modifier.padding(end = 15.dp).clickable {

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
        floatingActionButton = {
            Button (
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent,),
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
//            FloatingActionButton(
//                shape = CircleShape,
//                containerColor = Color.Transparent,
//                modifier = Modifier
//                    .size(60.dp)
//                    .clip(CircleShape)
//                    .background(
//                    animatedShimmerColor(
//                        shimmerColors = listOf(
//                            CommonComponent2.copy(0.8f),
//                            CommonComponent2.copy(0.6f),
//                            CommonComponent2.copy(0.8f)
//                        ),
//                        durationMillis = 3000
//                    )
//                ),
//                onClick = {
//                    // navigate to
//                    navController.navigate(NavigationScreen.MedicalTest.route) {
//                        popUpTo(navController.graph.findStartDestination().id)
//                        launchSingleTop = true
//                    }
//                }) {
//                Icon(
//                    painter = painterResource(id = NavigationScreen.MedicalTest.icon),
//                    tint = MaterialTheme.colorScheme.background,
//                    contentDescription = "Add",
//                )
//            }
        },
        bottomBar = {
            BottomAppBar (
                containerColor = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                    .height(60.dp)
            ){
                screens.forEachIndexed { index,screen->
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
            if (doubleBackToExitPressedOnce) {
                finishAffinity(activity)
            } else {
                doubleBackToExitPressedOnce = true
                Toast.makeText(context,
                    context.getString(R.string.press_again_to_exit), Toast.LENGTH_SHORT).show()
                Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
            }
        })

    }
}