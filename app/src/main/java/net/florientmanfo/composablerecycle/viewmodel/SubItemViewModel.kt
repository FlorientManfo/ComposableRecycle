package net.florientmanfo.composablerecycle.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class SubItemViewModel: ViewModel {

    var description: String = ""
    var dateTime: LocalDateTime = LocalDateTime.now()

    constructor()

    constructor(description: String, dateTime: String = ""){
        this.description = description
        this.dateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME)
    }
}