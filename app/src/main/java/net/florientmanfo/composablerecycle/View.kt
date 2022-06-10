package net.florientmanfo.composablerecycle

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.florientmanfo.composablerecycle.viewmodel.BaseItemViewModel
import net.florientmanfo.composablerecycle.viewmodel.SubItemViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainComposable(baseItemViewModels:List<BaseItemViewModel>){
    LazyColumn(modifier = Modifier.fillMaxWidth()){
        items(baseItemViewModels, null){ baseItemViewModel ->
            BaseItemRow(baseItem = baseItemViewModel)
            Divider()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BaseItemRow(baseItem: BaseItemViewModel){

    val subItemIsVisible = remember{ mutableStateOf(false)}


    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center) {

        Row(modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
            ){

            Row(){
                Box(
                    Modifier.size(50.dp, 50.dp)
                        .background(color = Color.Black, shape = RoundedCornerShape(20)),
                    contentAlignment = Center){
                    Text(
                        text = baseItem.description.substring(0, 1),
                        modifier = Modifier
                            .drawBehind {
                                drawRoundRect(
                                    color = Color.Black, cornerRadius = CornerRadius(20f, 20f),
                                )
                            },
                        style = MaterialTheme.typography.body1 ,
                        color = Color.White)
                }
                Spacer(modifier = Modifier.width(3.dp))
                Text(text = baseItem.description,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.body1,
                    color = Color.Black)
            }
            IconButton(
                onClick = {
                    subItemIsVisible.value = dropSubItems(subItemIsVisible.value)
                },
                modifier = Modifier
                    .size(26.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Image(
                    painter = painterResource(
                        id = if (subItemIsVisible.value) {
                            R.drawable.ic_baseline_keyboard_arrow_down_24
                        } else {
                            R.drawable.ic_baseline_keyboard_arrow_right_24
                        }
                    ),
                   contentDescription = ""
                )
            }
        }

        if(subItemIsVisible.value){
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 20.dp, 0.dp, 0.dp)) {
                if(baseItem.subItems?.isNotEmpty() == true){
                    val context = LocalContext.current
                    Card(modifier = Modifier
                        .padding(7.dp)
                        .background(Color.Transparent),
                        shape = RoundedCornerShape(corner = CornerSize(16.dp))
                    ){
                        Column(modifier = Modifier.fillMaxWidth()){
                            if(baseItem.subItems!=null){
                                for(subItem in baseItem.subItems!!){

                                    SubItemRow(subItem){ subItemSelected(context, subItem)}
                                    Divider()
                                }
                            }
                        }
                    }
                }else{
                    Text(
                        text = "No requirements",
                        modifier = Modifier
                            .fillMaxWidth()
                            .drawBehind {
                                drawRoundRect(
                                    color = Color.Gray, cornerRadius = CornerRadius(10f, 10f)
                                )
                            },
                        style = TextStyle(color = Color.Black, fontSize = 25.sp, textAlign = TextAlign.Center),
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SubItemRow(subItem: SubItemViewModel, onSubItemClick: (SubItemViewModel)->Unit){
    Row(modifier = Modifier
        .clickable(onClick = { onSubItemClick(subItem) })
        .fillMaxWidth()
        .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween){

        val imageModifier = Modifier
            .size(26.dp)
            .clip(shape = CircleShape)

        Image(painterResource(id = R.drawable.ic_baseline_warning_24),
            contentDescription = "",
            modifier = imageModifier,
            contentScale = ContentScale.Crop)
        Spacer(modifier = Modifier.width(3.dp))
        Text(text = "${subItem.description} ${subItem.dateTime} ",
        style = MaterialTheme.typography.body2)
    }
}

@Composable
fun TopBar(){
    TopAppBar(
        title = { Text(text = "IBM",
        style = TextStyle(
            color = Color.Black,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
        )
        ) },
        backgroundColor=Color.White,
        navigationIcon = {
            Image(
                painterResource(R.drawable.ic_baseline_keyboard_arrow_left_24),
                contentDescription = ""
            )
        },
    )
}