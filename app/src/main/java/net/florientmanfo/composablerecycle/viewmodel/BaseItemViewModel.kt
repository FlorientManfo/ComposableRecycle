package net.florientmanfo.composablerecycle.viewmodel

import androidx.lifecycle.ViewModel

class BaseItemViewModel: ViewModel {
    var description: String = ""
    var subItems: List<SubItemViewModel>? = mutableListOf()

    constructor()
    constructor(description: String, subItems: List<SubItemViewModel>?){
        this.description = description
        this.subItems = subItems
    }
}