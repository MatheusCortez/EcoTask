package com.example.ecotask_

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ecotask_.databinding.FragmentDicasBinding

class FragmentDicas : Fragment() {

    lateinit var binding : FragmentDicasBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDicasBinding.inflate(inflater, container, false)

        binding.materialToolbarDicas.setOnClickListener{
            findNavController().navigate(R.id.action_fragmentDicas_to_listFragment)
        }
        return binding.root
    }

}