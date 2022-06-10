package net.florientmanfo.composablerecycle

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import net.florientmanfo.composablerecycle.viewmodel.SubItemViewModel

fun dropSubItems(currentState: Boolean): Boolean{
    return !currentState
}

@RequiresApi(Build.VERSION_CODES.O)
fun subItemSelected(context: Context, subItem: SubItemViewModel){
    Toast.makeText(context, "${subItem.description}", Toast.LENGTH_SHORT).show()
}