package com.example.dessertclicker

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert

class DessertViewModel : ViewModel() {

    // List of all desserts
    private val desserts = Datasource.dessertList

    // Current dessert shown — observable by Compose
    var currentDessert by mutableStateOf(desserts.first())
        private set

    // Revenue counter — observable
    var revenue by mutableStateOf(0)
        private set

    // Desserts sold counter — observable
    var dessertsSold by mutableStateOf(0)
        private set

    // Called when user taps the dessert image
    fun onDessertClicked() {
        dessertsSold++
        revenue += currentDessert.price

        // Check dessert seterusnya (choose most expensive dessert that should be shown)
        val nextDessert = desserts.lastOrNull { dessertsSold >= it.startProductionAmount }
        if (nextDessert != null && nextDessert != currentDessert) {
            currentDessert = nextDessert
        }
    }
}