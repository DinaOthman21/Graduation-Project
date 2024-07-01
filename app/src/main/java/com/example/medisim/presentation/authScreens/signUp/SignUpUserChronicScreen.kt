package com.example.medisim.presentation.authScreens.signUp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.medisim.R
import com.example.medisim.data.remote.dto.main.ChronicDisease
import com.example.medisim.presentation.components.BackIcon
import com.example.medisim.presentation.components.ButtonClickOn
import com.example.medisim.presentation.components.TextLabel
import com.example.medisim.presentation.components.TextTitle
import kotlinx.coroutines.flow.collectLatest


@Composable
fun SignUpUserChronicScreen(navController: NavHostController,signUpScreenViewModel: SignUpScreenViewModel) {
    val chronicDiseases = signUpScreenViewModel.chronicDiseasesList.collectAsState().value
    val state = signUpScreenViewModel.state.value

    Column (
        modifier = Modifier.padding(12.dp),

        ){
        Row(
            modifier = Modifier.padding(bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackIcon{ navController.popBackStack()}
        }

        TextLabel(
            text = stringResource(R.string.create_new_account),
            modifier = Modifier.padding(top = 10.dp),
            textFont = 28,
            textFontWight = FontWeight.Bold
        )
        TextLabel(
            text = stringResource(R.string.select_your_chronic_diseases),
            modifier = Modifier.padding(top = 10.dp),
            textFont = 18,
            textColor = MaterialTheme.colorScheme.secondary
        )
        LazyColumn(modifier = Modifier.padding(top = 20.dp)){
            items(chronicDiseases){
                ChronicDiseaseCard(
                    chronicDisease = it,
                    onSelectChronic = {cd->
                        signUpScreenViewModel.onSelectChronic(cd)
                    },
                )
            }
            item{
                Spacer(modifier = Modifier.weight(1f))
                ButtonClickOn(
                    buttonText = stringResource(R.string.create_account),
                    paddingValue = 0) {
                    signUpScreenViewModel.onSignUpClick(navController)

                }
                Row {
                    Text(
                        state.errorMessage, style = MaterialTheme.typography.bodyMedium, modifier = Modifier
                            .padding(top = 3.dp, start = 25.dp), color = Color.Red
                    )
                    Spacer(modifier = Modifier.weight(1f))

                }
            }
        }








    }
}


@Composable
fun ChronicDiseaseCard(
    chronicDisease:ChronicDisease,
    onSelectChronic:(ChronicDisease)->Unit,
) {
    val context = LocalContext.current
    // Now can access resources using the context
    val resources = context.resources
    val isArabicLang = resources.configuration.locales[0].language == "ar"


    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
//        TextLabel(text = if (isArabicLang) chronicDisease.arName else chronicDisease.enName,
//            textFont = 18)
        TextTitle(
            text =  if (isArabicLang) chronicDisease.arName else chronicDisease.enName,
            modifier = Modifier.weight(0.9f),
            textFontWight = FontWeight.Bold,
            maxLines = 2,
            textColor = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(26.dp))
//        SelectedIconWithName(
//            name = stringResource(R.string.yse),
//            selectedState = chronicDiseaseState,
//            onSelect = { onSelectChronic() }
//
//        )
//        Spacer(modifier = Modifier.weight(1f))
        SelectedIconWithName(
            name = stringResource(R.string.empty_string),
            chronicDisease = chronicDisease,
            onSelect = { cd-> onSelectChronic(cd) }

        )
    }
}


@Composable
fun SelectedIconWithName(
    name:String,
    chronicDisease: ChronicDisease,
    onSelect:(ChronicDisease)->Unit
) {
    Icon(
        imageVector = if (chronicDisease.isSelected)Icons.Default.CheckCircle else Icons.Outlined.Circle,
        modifier = Modifier.clickable {
          onSelect(chronicDisease)
        },
        contentDescription = "select icon",
        tint = MaterialTheme.colorScheme.primary
    )
    Spacer(modifier = Modifier.width(5.dp))
    TextLabel(text = name, textFont = 18)

}