package com.example.paranikontrolet.ui.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paranikontrolet.domain.ui_model.BudgetUiModel
import com.example.paranikontrolet.domain.usecase.UseCases
import com.example.paranikontrolet.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _result = MutableLiveData<Resource<List<BudgetUiModel>>>()
    val result: LiveData<Resource<List<BudgetUiModel>>> = _result

    fun getBudgetFromFirestore() {
        viewModelScope.launch {
            _result.value = useCases.getBudgetFromFirestore()
        }
    }

}