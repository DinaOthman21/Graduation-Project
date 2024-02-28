package com.example.medisim.presentation.authScreens.signUp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.medisim.R
import com.example.medisim.presentation.components.BackIcon
import com.example.medisim.presentation.components.ButtonClickOn
import com.example.medisim.presentation.components.TextLabel
import com.example.medisim.presentation.navigation.Screens
import com.example.medisim.ui.theme.brush


val chronics = listOf(
    "chronic disease 1",
    "chronic disease 2",
    "chronic disease 3",
    "chronic disease 4",
    "chronic disease 5",
    "chronic disease 6"
)
@Composable
fun SignUpUserChronicScreen(navController: NavHostController) {
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
            items(chronics){
                ChronicDiseaseCard(
                    chronicDiseaseName = it,
                    chronicDiseaseState = true,
                    onSelectChronic = {}
                )
            }
        }



        Spacer(modifier = Modifier.weight(1f))
        ButtonClickOn(
            buttonText = stringResource(R.string.create_account),
            paddingValue = 0) {
            navController.navigate(Screens.RegistrationSuccessfully.route)
        }



    }
}


@Composable
fun ChronicDiseaseCard(
    chronicDiseaseName:String,
    chronicDiseaseState:Boolean,
    onSelectChronic:()->Unit
) {
    Row (
        modifier = Modifier.padding(vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        TextLabel(text = chronicDiseaseName, textFont = 18)
        Spacer(modifier = Modifier.width(26.dp))
        SelectedIconWithName(
            name = stringResource(R.string.yse),
            selectedState = chronicDiseaseState,
            onSelect = { onSelectChronic() }

        )
        Spacer(modifier = Modifier.width(22.dp))
        SelectedIconWithName(
            name = stringResource(R.string.no),
            selectedState = chronicDiseaseState.not(),
            onSelect = { onSelectChronic() }

        )
    }
}


@Composable
fun SelectedIconWithName(
    name:String,
    selectedState:Boolean,
    onSelect:()->Unit
) {
    Icon(
        imageVector = if (selectedState)Icons.Default.CheckCircle else Icons.Outlined.Circle,
        modifier = Modifier.clickable {
          onSelect()
        },
        contentDescription = "select icon",
        tint = MaterialTheme.colorScheme.primary
    )
    Spacer(modifier = Modifier.width(5.dp))
    TextLabel(text = name, textFont = 18)

}