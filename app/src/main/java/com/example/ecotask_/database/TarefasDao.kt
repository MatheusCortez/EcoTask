package com.example.ecotask_.database

import androidx.room.*
import com.example.ecotask_.model.Tarefa
import kotlinx.coroutines.flow.Flow

@Dao
interface TarefasDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(tarefa: Tarefa)

    @Query("SELECT * FROM tarefas_table ORDER BY id ASC")
    fun queryAllTask(): Flow<List<Tarefa>>

    @Query("SELECT * FROM tarefas_table WHERE id = :id")
    fun queryById(id:Int): Flow<Tarefa?>

    @Update
    suspend fun updateRoom(tarefa:Tarefa)

    @Delete
    suspend fun deleteTaskRoom(tarefa: Tarefa)

}