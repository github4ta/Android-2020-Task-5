package com.thecatapi.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thecatapi.task.data.Cat
import kotlinx.coroutines.launch

class CatViewModel : ViewModel() {

    private val _items = MutableLiveData<List<Cat>>()
    val items: LiveData<List<Cat>> get() = _items

    init {
        viewModelScope.launch {
            _items.value = TheCatApiImpl.getListOfCats()
        }
    }

    fun upLoadItems() {
        TheCatApiImpl.page += 1
        viewModelScope.launch {
            _items.value = TheCatApiImpl.getListOfCats()
        }
    }
}