package com.example.ecotask_.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tarefas_table")
data class Tarefa(
  @PrimaryKey(autoGenerate = true)
  val id: Int,
  @SerializedName("name")
  var name: String,
  @SerializedName("description")
  var description: String,
  @SerializedName("assignetTo")
  var assignetTo: String,
  @SerializedName("dueDate")
  var dueDate: String,
  @SerializedName("status")
  var status: String
  )

