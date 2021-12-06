package com.example.ecotask_.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecotask_.model.Tarefa
import com.example.ecotask_.repositories.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    /*
    private var _myGetResponse = MutableLiveData<Response<List<Tarefa>>>()
    val myresponse = _myGetResponse

    private val _myDeleteResponse = MutableLiveData<Response<Tarefa>>()
    val myDeleteResponse: LiveData<Response<Tarefa>> = _myDeleteResponse
*/

    val selectedDateLiveData: MutableLiveData<String> = MutableLiveData()
    var tarefaSelecionada: Tarefa? = null

    lateinit var myQueryResponse: Flow<List<Tarefa>>

    init {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date = formatter.format(Date())
        selectedDateLiveData.postValue(date.toString())

        viewModelScope.launch {
            myQueryResponse = repository.queryAllTasks()
        }
    }

    fun listTarefas() {
        viewModelScope.launch {
            try {
                val response = repository.getAllTasks()
                //_myGetResponse.value = response
                if (response.isSuccessful) {
                    val listTasks = response.body()!!
                    for (task in listTasks) {
                        val findTasks = repository.queryById(task.id)
                        if (findTasks.first() != null) {
                            repository
                        } else {
                            repository.insertTask(task)
                        }
                    }
                } else {
                    Log.d("Developer", "Error: ${response.errorBody().toString()}")
                }
            } catch (e: Exception) {
                Log.d("Developer", e.message.toString())
            }
        }
    }


    fun addTask(tarefa: Tarefa) {
        viewModelScope.launch {
            try {
                val response = repository.addTask(tarefa)
                if (response.isSuccessful) {

                    repository.insertTask(response.body()!!)

                    repository.insertTask(tarefa)

                } else {
                    repository.insertTask(tarefa)
                }
            } catch (e: Exception) {
                repository.insertTask(tarefa)
            }
        }
    }

    fun updateTarefa(tarefa: Tarefa) {
        viewModelScope.launch {
            try {
                repository.updateTarefa(tarefa)
                repository.updateRoom(tarefa)
            }catch (e:Exception){
                repository.updateRoom(tarefa)
            }
        }
    }

    fun deleteTarefa(tarefa: Tarefa) {
        viewModelScope.launch {
            try {
                repository.deleteTarefa(tarefa.id)
                repository.deleteTaskRoom(tarefa)
            }catch (e:Exception){
                repository.deleteTaskRoom(tarefa)
            }
            //_myDeleteResponse.value = response
        }
    }
}

