package com.example.jettriviademo.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jettriviademo.data.DataOrException
import com.example.jettriviademo.model.QuestionItem
import com.example.jettriviademo.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val repository: QuestionRepository
) : ViewModel() {

    val _data = mutableStateOf(
        DataOrException<ArrayList<QuestionItem>, Boolean, Exception>(null, true, Exception(""))
    )
    //val data : State<DataOrException<ArrayList<QuestionItem>, Boolean, Exception>>> = _data

    init {
        getAllQuestions()
    }


    private fun getAllQuestions() {
        viewModelScope.launch {
            _data.value.loading = true
            _data.value = repository.getAllQuestions()

            if(_data.value.data.toString().isNotEmpty()) {
                _data.value.loading = false
            }
        }
    }

}