package com.example.ecotask_


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ecotask_.databinding.FragmentReciclagemBinding
import android.content.Intent
import android.net.Uri


const val LINK_MATERIAIS =
    "https://www.pensamentoverde.com.br/reciclagem/quais-sao-os-materiais-reciclaveis-e-nao-reciclaveis"
const val LINK_ECYCLE =
    "https://www.ecycle.com.br/postos/reciclagem.php"

class FragmentReciclagem : Fragment() {

    lateinit var binding: FragmentReciclagemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentReciclagemBinding.inflate(inflater, container, false)


        binding.materialToolbarReciclagem.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentReciclagem_to_fragmentDicas)
        }

        binding.linkParaSite1.setOnClickListener{
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(LINK_MATERIAIS)))
        }

        binding.linkParaSite2.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(LINK_ECYCLE)))
        }
        return binding.root
    }
}