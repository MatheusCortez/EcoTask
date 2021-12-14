package com.example.ecotask_

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ecotask_.databinding.FragmentEnergiaBinding

const val LINK_ENERGIA =
    "https://brasilescola.uol.com.br/fisica/dicas-para-economia-energia-eletrica.htm"

class FragmentEnergia : Fragment() {
    lateinit var binding : FragmentEnergiaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEnergiaBinding.inflate(inflater, container, false)

        binding.materialToolbarEnergia.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentEnergia_to_fragmentDicas)
        }

        binding.linkParaSite3.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(LINK_ENERGIA)))
        }
        return binding.root
    }
}