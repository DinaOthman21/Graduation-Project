package com.example.medisim.presentation.homeScreens.topNavigationScreens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medisim.R
import com.example.medisim.presentation.components.CircleIconBackground
import com.example.medisim.presentation.components.TextLabel
import com.example.medisim.presentation.components.ViewImage


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun NavigationDrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(190.dp)
            .clip(
                shape = RoundedCornerShape(
                    bottomStart = 150.dp,
                )
            )
            .background(MaterialTheme.colorScheme.tertiary),

    ){
        Row(
            modifier = Modifier.padding(16.dp,top = 50.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            ViewImage(
                image = "https://media.istockphoto.com/id/1138364436/vector/user-icon-green-vector-icon.jpg?s=2048x2048&w=is&k=20&c=nZQ0mPUQadDvjw0CVrFmkNVM5oh0LmoG1e_pTFKpIaM=",
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
            )

            Column (
                modifier = Modifier.padding(start = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally

                ){
                TextLabel(
                    text = "Mahmoud Adel",
                    textFont = 18,
                    textFontWight = FontWeight.Bold
                )
                TextLabel(
                    text = "Mahmoudadel5556@gmail.com",
                    modifier = Modifier.padding(top = 10.dp),
                    textFont = 13,
                    textFontWight = FontWeight.Bold
                )
            }

        }
    }
}


@Preview
@Composable
fun NavigationDrawerBody() {
    val items = listOf(
        DrawerItem.Language,
        DrawerItem.Mode,
        DrawerItem.TermsAndConditions,
        DrawerItem.License,
        DrawerItem.Rate,
        DrawerItem.Share,
        DrawerItem.Logout,
    )
    LazyColumn(Modifier.fillMaxSize()){
        item {
            TextLabel(
                text = stringResource(R.string.general_settings),
                modifier = Modifier.padding(start = 3.dp, bottom = 10.dp),
                textFontWight = FontWeight.Bold,
                textFont = 18,
                textColor = MaterialTheme.colorScheme.secondary
            )
        }
        itemsIndexed(items){ index,item ->
            BodyItem(
                item = item,
                iconColor = item.color,
                onRowClick = {}
            ) { }
            if (index== 1 || index==5){
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp, bottom = 5.dp, start =  18.dp,end = 18.dp),
                    color = MaterialTheme.colorScheme.primary
                )
                if (index==1){
                    TextLabel(
                        text = stringResource(R.string.about),
                        modifier = Modifier.padding(start = 3.dp,bottom = 10.dp),
                        textFontWight = FontWeight.Bold,
                        textFont = 18,
                        textColor = MaterialTheme.colorScheme.secondary
                    )
                }else{
                    TextLabel(
                        text = stringResource(R.string.account_settings),
                        modifier = Modifier.padding(start = 3.dp,bottom = 10.dp),
                        textFontWight = FontWeight.Bold,
                        textFont = 18,
                        textColor = MaterialTheme.colorScheme.secondary
                    )

                }
            }

        }
    }

}


@Composable
fun BodyItem(
    item: DrawerItem,
    iconColor:Long,
    onRowClick:()->Unit,
    onModeSwitch:()->Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 10.dp)
            .clickable {
                onRowClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ){
        CircleIconBackground(
            imageVector = item.imageVector,
            modifier = Modifier.background(Color(iconColor), CircleShape),
            iconColor = Color.White,
            iconSize = 40
        )
        TextLabel(
            text = item.title,
            modifier = Modifier.padding(start = 5.dp),
            textFont = 18,
            textFontWight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(1f))
        if (item.title != DrawerItem.Logout.title){
            if (item.title != DrawerItem.Mode.title){
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.secondary
                )
            }else{
                Switch(
                    checked = false,
                    onCheckedChange = { onModeSwitch() }
                )
            }
        }

    }

}