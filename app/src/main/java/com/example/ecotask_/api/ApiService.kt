package com.example.ecotask_.api

import com.example.ecotask_.model.Tarefa
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("api/todo")
    suspend fun getAllTasks(): Response<List<Tarefa>>


    @POST("api/todo")
    suspend fun addTask(
        @Body tarefa: Tarefa
    ): Response<Tarefa>


    @PUT("api/todo")
    suspend fun updateTarefa(
        @Body tarefas: Tarefa
    ): Response<Tarefa>

    @DELETE("api/todo/{tarefa}")
    suspend fun deleteTarefa(
        @Path("tarefa")valor:Int

    ):Response<Tarefa>

}