package com.example.ecotask_.ui.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ecotask_.R
import com.example.ecotask_.databinding.FragmentIncluirTarefaBinding
import com.example.ecotask_.model.Tarefa
import com.example.ecotask_.ui.helpers.formValidate
import com.example.ecotask_.ui.helpers.format
import com.example.ecotask_.viewModel.MainViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.timepicker.MaterialTimePicker
import java.util.*

class incluirTarefaFragment : Fragment() {
    private var _tarefaSelecionada: Tarefa? = null
    private val tarefaSelecionada get() = _tarefaSelecionada!!
    lateinit  var binding:FragmentIncluirTarefaBinding
    val mainViewModel: MainViewModel by activityViewModels()
    val formValidate = formValidate()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentIncluirTarefaBinding.inflate(inflater,container,false)

        DatePicker()
        carregarDados()
        validadeForm()


            binding.buttonSalvar.setOnClickListener {


            _tarefaSelecionada = mainViewModel.tarefaSelecionada
            if (_tarefaSelecionada != null){
                updateTask()
                Toast.makeText(requireContext(),"Tarefa alterada com sucesso",Toast.LENGTH_LONG).show()
            }
            else   {
                addTask()
                Toast.makeText(requireContext(),"Tarefa criada com sucesso",Toast.LENGTH_LONG).show()
            }

            findNavController().navigate(R.id.action_incluirTarefaFragment_to_listFragment)
        }


        binding.materialToolbar.setOnClickListener{
            findNavController().navigate(R.id.action_incluirTarefaFragment_to_listFragment)
        }
        return binding.root



    }

    private fun validadeForm() {
        binding.textNome.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                binding.textInputLayoutNome.helperText =
                    binding.textNome.text?.let {
                        formValidate.NameValidate( binding.textNome.text.toString(),
                            it
                        )
                    }

            }

        }
        binding.textResponsavel.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                binding.textInputLayoutResponsavel.helperText =
                    binding.textResponsavel.text?.let {
                        formValidate.ResposanvelValidade(
                            binding.textResponsavel.text.toString(),
                            it
                        )
                    }
            }
        }
        if(binding.textData.text?.isEmpty()==true)binding.textInputLayoutData.helperText="*Obrigatorio"
        else binding.textInputLayoutData.helperText=""

        if(binding.textHora.text?.isEmpty() == true)binding.textInputLayoutData.helperText="*Obrigatorio"
        else binding.textInputLayoutHora.helperText=""

        binding.textDescricao.setOnFocusChangeListener { _, hasFocus ->

        if(!hasFocus){
            binding.textInputLayoutDescricao.helperText=binding.textData.text?.let{
                formValidate.descricaoValidate(it)
            }
        }


        }

    }


    private fun DatePicker() {
        binding.textData.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .build()
            datePicker.addOnPositiveButtonClickListener {
                binding.textData.setText((Date(it).format().toString()))
            }
            datePicker.show(parentFragmentManager, "DatePicker")
        }

        binding.textHora.setOnClickListener {
            val timePicker=  MaterialTimePicker.Builder().build()
            timePicker.addOnPositiveButtonClickListener {
                val hora=    if(timePicker.hour in 0..9)"0${timePicker.hour.toString()} " else "${timePicker.hour}"
                var minuto = if(timePicker.minute in 0..9)"0${timePicker.minute.toString()}"
                else "${timePicker.minute.toString()}"
                val horaMinuto=" ${hora }:${minuto}"
                binding.textHora.setText(horaMinuto)
            }
            timePicker.show(parentFragmentManager,"TimePicker")
        }
    }


    private fun addTask() {
        val nome = binding.textNome.text.toString()
        val responsvel = binding.textResponsavel.text.toString()
        val data = binding.textData.text.toString()
        val status = "Pendente"
        val hora = binding.textHora.text.toString()
        val descricao =binding.textDescricao.text.toString()
        val dueDate= "${data} ${hora}:00"
        val tarefa = Tarefa(0, nome, descricao, responsvel, dueDate, status)
        mainViewModel.addTask(tarefa)
    }


    private fun updateTask() {
        val nome = binding.textNome.text.toString()
        val responsvel = binding.textResponsavel.text.toString()
        val data = binding.textData.text.toString()
        val status = "Pendente"
        val hora = binding.textHora.text.toString()
        val descricao =binding.textDescricao.text.toString()
        val dueDate= "${data} ${hora}:00"
        val tarefa = Tarefa(tarefaSelecionada.id, nome, descricao, responsvel,
            dueDate,
            status
        )
        mainViewModel.updateTarefa(tarefa)
    }

    private fun carregarDados() {
        _tarefaSelecionada = mainViewModel.tarefaSelecionada
        if (_tarefaSelecionada != null) {
            binding.materialToolbar.title="Editar Tarefa"
            binding.textNome.setText(tarefaSelecionada.name)
            binding.textDescricao.setText(tarefaSelecionada.description)
            binding.textResponsavel.setText(tarefaSelecionada.assignetTo)
          val data =tarefaSelecionada.dueDate.toString()
            val dataList:List<String> =data.split(" ")
            binding.textData.setText(dataList[0])
            binding.textHora.setText(dataList[1])


        } else {
            binding.materialToolbar.title="Adicionar Tarefa"
            binding.textNome.text = null
            binding.textDescricao.text = null
            binding.textResponsavel.text = null
            binding.textData.text = null
        }
    }




}






