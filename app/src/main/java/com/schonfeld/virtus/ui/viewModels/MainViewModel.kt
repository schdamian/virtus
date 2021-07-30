package com.schonfeld.virtus.ui.viewModels

import androidx.lifecycle.*
import com.schonfeld.virtus.database.HitTable
import com.schonfeld.virtus.models.*
import com.schonfeld.virtus.repository.DataProvider
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val repo: DataProvider) : ViewModel() {

    private val _state = MutableLiveData<DataState>()
    val state: LiveData<DataState> = _state

    val hits = MediatorLiveData<List<HitTable>>()

    fun getHits() {
        viewModelScope.launch {
            _state.value = DataState(isLoading = true)
            try {
                hits.addSource(repo.requestHits()) {
                    hits.value = it
                }
                _state.value = DataState(isSuccess = true)
            } catch (ex: Exception) {
                _state.value = DataState(isError = true)
            }
        }
    }

    fun deleteHit(hit: HitTable) {
        viewModelScope.launch {
            repo.deleteHit(hit)
        }
    }
}