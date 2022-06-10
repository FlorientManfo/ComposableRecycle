package net.florientmanfo.composablerecycle

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.florientmanfo.composablerecycle.ui.theme.ComposableRecycleTheme
import net.florientmanfo.composablerecycle.viewmodel.BaseItemViewModel
import net.florientmanfo.composablerecycle.viewmodel.SubItemViewModel
import java.time.LocalDateTime

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(

               topBar = { TopBar() },
               content = {
                   Greeting("Android")
               }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Greeting(name: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Franck Tchuisseu",
            modifier = Modifier.padding(16.dp),
            style = TextStyle(fontSize = 20.sp,
            fontWeight = FontWeight.Bold
            )
        )
        var subItemViewModels = listOf<SubItemViewModel>(
            SubItemViewModel("Sub1 test", LocalDateTime.now().toString()),
            SubItemViewModel("Sub2 test", LocalDateTime.now().toString()),
            SubItemViewModel("Sub3 test", LocalDateTime.now().toString()),
            SubItemViewModel("Sub4 test", LocalDateTime.now().toString()),
        )
        var baseItemViewModels = listOf<BaseItemViewModel>(
            BaseItemViewModel("Test", null),
            BaseItemViewModel("Test", subItemViewModels),
            BaseItemViewModel("Test", subItemViewModels),
            BaseItemViewModel("Test", null),
            BaseItemViewModel("Test", subItemViewModels),
        )

        MainComposable(baseItemViewModels)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposableRecycleTheme {
        Greeting("Android")
    }
}