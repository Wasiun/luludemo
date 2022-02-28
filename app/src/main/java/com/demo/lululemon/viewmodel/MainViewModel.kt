package com.demo.lululemon.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.demo.lululemon.roomdb.GarmentEntity
import com.demo.lululemon.roomdb.RoomData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _list = MutableLiveData<List<String>>()
    val list: LiveData<List<String>>
        get() = _list
    var selection = Sort.ALPHABETS

    fun addToList(name: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                RoomData.getInstance(getApplication()).getMyEntityDao()
                    .insertGarment(GarmentEntity(0, name))
            }
        }
    }

    fun getList(sortType: Sort) {
        selection = sortType
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (sortType == Sort.ALPHABETS) {
                    _list.postValue(
                        RoomData.getInstance(getApplication()).getMyEntityDao()
                            .getGarmentsByName().sortedBy { it.lowercase() }
                    )
                } else {
                    _list.postValue(
                        RoomData.getInstance(getApplication()).getMyEntityDao()
                            .getGarmentsByTime()
                    )
                }
            }
        }
    }

    companion object {
        enum class Sort {
            ALPHABETS,
            ENTRY
        }
    }
}