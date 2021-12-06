package com.example.ecotask_.repositories

import com.example.ecotask_.api.RetrofitInstance
import com.example.ecotask_.database.TarefasDao
import com.example.ecotask_.model.Tarefa
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response

class Repository(private val tarefasDao: TarefasDao) {

    //Retrofit
    suspend fun getAllTasks(): Response<List<Tarefa>> {
        return RetrofitInstance.api.getAllTasks()
    }

    suspend fun addTask(tarefa: Tarefa): Response<Tarefa> {
        return RetrofitInstance.api.addTask(tarefa)
    }

    suspend fun updateTarefa(tarefa: Tarefa): Response<Tarefa> {
        return RetrofitInstance.api.updateTarefa(tarefa)
    }

    suspend fun deleteTarefa(valor: Int): Response<Tarefa> {
        return RetrofitInstance.api.deleteTarefa(valor)
    }

    //Room
    fun queryAllTasks(): Flow<List<Tarefa>> {
        return tarefasDao.queryAllTask()
    }
    fun queryById(id: Int): Flow<Tarefa?> {
        return tarefasDao.queryById(id)
    }
    suspend fun insertTask(tarefa: Tarefa){
        tarefasDao.addTask(tarefa)
    }
    suspend fun updateRoom(tarefa: Tarefa){
        return tarefasDao.updateRoom(tarefa)
    }
    suspend fun deleteTaskRoom(tarefa: Tarefa){
        return tarefasDao.deleteTaskRoom(tarefa)
    }


}