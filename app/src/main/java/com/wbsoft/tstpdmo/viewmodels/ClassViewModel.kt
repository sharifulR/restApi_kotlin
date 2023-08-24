package com.wbsoft.tstpdmo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wbsoft.tstpdmo.viewmodels.repositorys.ClassRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClassViewModel@Inject constructor(private val classRepository: ClassRepository) :ViewModel() {

    val classLiveData get()=classRepository.classLiveData

    fun getAllClass(){
        viewModelScope.launch {
            classRepository.getAllClass()
        }
    }
}