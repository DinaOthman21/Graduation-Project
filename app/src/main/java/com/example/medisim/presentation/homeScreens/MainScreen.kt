package com.example.medisim.presentation.homeScreens

import android.os.Build
import android.os.Handler
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.medisim.R
import com.example.medisim.presentation.components.TextWithHiatusFont
import com.example.medisim.presentation.homeScreens.bottomNavigationScreens.home.HomeViewModel
import com.example.medisim.presentation.homeScreens.bottomNavigationScreens.medicine.MedicineScreenViewModel
import com.example.medisim.presentation.homeScreens.bottomNavigationScreens.predictiion.disease.PredictionViewModel
import com.example.medisim.presentation.homeScreens.bottomNavigationScreens.predictiion.skinDisease.SkinDiseaseScreenViewModel
import com.example.medisim.presentation.homeScreens.topNavigationScreens.profile.NavigationDrawerBody
import com.example.medisim.presentation.homeScreens.topNavigationScreens.profile.NavigationDrawerHeader
import com.example.medisim.presentation.homeScreens.topNavigationScreens.profile.ProfileViewModel
import com.example.medisim.presentation.navigation.BottomNavigation
import com.example.medisim.presentation.navigation.NavigationScreen
import com.example.medisim.presentation.navigation.Screens
import com.example.medisim.ui.theme.CommonComponent2
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun MainScreen(
    appNavController: NavHostController,
    homeViewModel:HomeViewModel,
    profileViewModel: ProfileViewModel,
    predictionViewModel: PredictionViewModel,
    medicineViewModel: MedicineScreenViewModel,
    skinDiseaseViewModel: SkinDiseaseScreenViewModel,

) {

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

    val screensName = listOf(
        stringResource(R.string.home),
        stringResource(R.string.prediction),
        stringResource(R.string.drug),
        stringResource(R.string.calculator)
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
                            //AppNameWithHiatusFont(showAiString = false, colorText = CommonComponent2, fontSize = 50)
                            TextWithHiatusFont("MediSim")
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(
                                imageVector = Icons.Default.Person,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clickable {
                                        scope.launch {
                                            scaffoldState.drawerState.open()
                                        }
                                    },
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.primary
                            )

                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.Chat,
                                modifier = Modifier
                                    .padding(8.dp, end = 16.dp)
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
                NavigationDrawerHeader(profileViewModel)
                NavigationDrawerBody(appNavController,profileViewModel)
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
                screens.forEachIndexed { idx, screen->

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
                                    text = screensName[idx],
                                    fontSize = 16.sp,
                                    modifier = Modifier,
                                    color = if(screen.route == currentRoute) CommonComponent2
                                    else Color.White,
                                    softWrap = false,
                                )

                            }
                        }

                    )


                }

            }



        },


    ){
        Box(modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(it)){
            BottomNavigation(
                bottomNavController = navController,
                appNavController = appNavController,
                homeViewModel = homeViewModel,
                medicineViewModel = medicineViewModel,
                predictionViewModel = predictionViewModel,
                skinDiseaseViewModel = skinDiseaseViewModel,
            )
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