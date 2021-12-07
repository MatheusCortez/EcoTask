package com.example.ecotask_.ui.Fragments

import TarefaAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecotask_.R
import com.example.ecotask_.adapter.TaskItemClickListener
import com.example.ecotask_.databinding.FragmentListBinding
import com.example.ecotask_.model.Tarefa
import com.example.ecotask_.viewModel.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListFragment : Fragment(), TaskItemClickListener {
    val mainViewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        val adapter = TarefaAdapter(this, mainViewModel)
        binding.rvList.layoutManager = LinearLayoutManager(context)
        binding.rvList.adapter = adapter
        binding.rvList.setHasFixedSize(true)

        mainViewModel.listTarefas()
        lifecycleScope.launch {
            mainViewModel.myQueryResponse.collect {
                response -> adapter.setData(response)
            }
        }




        binding.iconAdd.setOnClickListener {
            mainViewModel.tarefaSelecionada = null
            findNavController().navigate(R.id.action_listFragment_to_incluirTarefaFragment)
        }

        binding.buttonIdeas.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_fragmentDicas)
        }
        return binding.root
    }

    override fun onTaskClicked(tarefa: Tarefa) {
        mainViewModel.tarefaSelecionada = tarefa
        findNavController().navigate(R.id.action_listFragment_to_incluirTarefaFragment)
    }


}