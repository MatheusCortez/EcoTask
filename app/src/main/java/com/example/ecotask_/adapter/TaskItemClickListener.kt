package com.example.ecotask_.adapter

import com.example.ecotask_.model.Tarefa

interface TaskItemClickListener {

    fun onTaskClicked(tarefa: Tarefa)
}