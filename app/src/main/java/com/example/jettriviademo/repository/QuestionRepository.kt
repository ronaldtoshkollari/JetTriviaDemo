package com.example.jettriviademo.repository

import android.util.Log
import com.example.jettriviademo.data.DataOrException
import com.example.jettriviademo.model.QuestionItem
import com.example.jettriviademo.network.QuestionApi
import javax.inject.Inject
import kotlin.Exception

class QuestionRepository @Inject constructor(
    private val questionApi: QuestionApi
) {

    private val dataOrException = DataOrException<ArrayList<QuestionItem>, Boolean, Exception>()

    suspend fun getAllQuestions(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception> {
        try {
            dataOrException.loading = true
            dataOrException.data = questionApi.getAllQuestions()

            if(dataOrException.data.toString().isNotEmpty()) dataOrException.loading = false
        } catch (exception: Exception) {
            dataOrException.e = exception
            Log.d("EXC", "getAllQuestions: ${dataOrException.e!!.localizedMessage}")
        }

        return dataOrException
    }

}