package com.example.mtgcards.components.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeScreenViewModel : ViewModel() {
    private val _viewState = MutableLiveData<HomeViewState?>()
    val viewState: LiveData<HomeViewState?>
        get() = _viewState

    fun onNormalSearch(cardName: String){
        _viewState.value = HomeViewState.NormalSearch(cardName)
    }
    fun onAdvancedSearch(){
        _viewState.value = HomeViewState.AdvancedSearch
    }

    fun clearState(){
        _viewState.value = null
    }
}

sealed interface HomeViewState{
    data object AdvancedSearch: HomeViewState
    data class NormalSearch(val cardName: String): HomeViewState
}