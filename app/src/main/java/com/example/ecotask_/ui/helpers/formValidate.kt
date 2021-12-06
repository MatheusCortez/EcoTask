package com.example.ecotask_.ui.helpers

import android.text.Editable

class formValidate{


    fun NameValidate(value: String,valueEditable: Editable):String {
            if(value.length <4)return "Valor minimo de 4 digitos"
            if(valueEditable.isEmpty()) return "Campo Obrigatorio"
                return ""
    }
    fun ResposanvelValidade(value:String,valueEditable: Editable):String{
        if(value.length <4)return "Valor minimo de 4 digitos"
        if(valueEditable.isEmpty()) return "Campo Obrigatorio"

        return ""
    }
    fun DataValidade(valueEditable: Editable):String{
        if(valueEditable.isEmpty()) return "Campo Obrigatorio"
        return ""
    }
    fun HoraValidade(valueEditable: Editable):String{
        if(valueEditable.isEmpty()) return "Campo Obrigatorio"
        return ""
    }

    fun descricaoValidate(valueEditable: Editable):String{
        if(valueEditable.isEmpty()) return "Campo Obrigatorio"
        return ""
    }


}

