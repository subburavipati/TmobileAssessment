package com.tmobile.subbu.viewmodel

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmobile.subbu.model.data.Card
import com.tmobile.subbu.model.repository.Repository
import kotlinx.coroutines.launch

/**
 * ViewModel which takes of retrieving the data from the repository and passes the same to the view layer
 */
class CardsViewModel @ViewModelInject constructor(
    private val cardsRepository: Repository) : ViewModel() {

    /**
     * Displays the loader while the network /local read is active
     */
    val loaderVisibility = MutableLiveData<Int>()

    /**
     * Holder to pass the data to the view layer upon successful read
     */
    val cards = MutableLiveData<List<Card>>()

    /**
     * flag to control the display of the error
     */
    val shouldDisplayError = MutableLiveData<Boolean>()

    init {
        shouldDisplayError.postValue(false)
        getCards()
    }

    private fun getCards() {
        viewModelScope.launch {
            try {
                loaderVisibility.postValue(View.VISIBLE)
                cards.postValue(cardsRepository.getCards())
                shouldDisplayError.postValue(false)
            } catch (e: Exception) {
                shouldDisplayError.postValue(true)
            } finally {
                loaderVisibility.postValue(View.GONE)
            }
        }
    }
}