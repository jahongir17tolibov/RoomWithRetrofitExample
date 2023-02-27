package com.example.roomwithretrofitexample.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomwithretrofitexample.models.CryptoIncomingModel
import com.example.roomwithretrofitexample.models.Result
import com.example.roomwithretrofitexample.repository.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepo: MainRepo) : ViewModel() {

    private val _cryptoList = MutableLiveData<Result<CryptoIncomingModel>>()
    val cryptoList = _cryptoList

    fun fetchCryptos() {
        viewModelScope.launch {
            mainRepo.fetchCryptos().collect {
                _cryptoList.value = it
            }
        }
    }

}