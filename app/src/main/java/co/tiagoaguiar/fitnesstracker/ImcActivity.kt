package co.tiagoaguiar.fitnesstracker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ImcActivity : AppCompatActivity() {

    private lateinit var editWeight: EditText
    private lateinit var editHeight: EditText

    override fun onCreate(savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)

        editWeight = findViewById(R.id.edit_imc_weigth)
        editHeight = findViewById(R.id.edit_imc_heigth)

        val btnSend: Button = findViewById(R.id.btn_imc_send)
        btnSend.setOnClickListener {
        if (!validate()) {
            Toast.makeText(this, R.string.fields_messages, Toast.LENGTH_SHORT).show()
            return@setOnClickListener
        }
            //aqui deu sucesso
        }
    }

    private fun validate(): Boolean {
        // não pode inserir valores nulos / vazio
        // não podde inserir/começar com sezo (0)

        //true && true = true
        //true && false = false
        //false && true = false
        //false && false = false

        // opção 1: usar if e else
//        if (editWeight.text.toString().isNotEmpty()
//            && editHeight.text.toString().isNotEmpty()
//            && !editWeight.text.toString().startsWith("0")
//            && !editHeight.text.toString().startsWith("0"))
//        {
//            return true
//        } else {
//            return false
//        }

//        //opção 2: usar somente return para simular o if/else
//        if (editWeight.text.toString().isNotEmpty()
//            && editHeight.text.toString().isNotEmpty()
//            && !editWeight.text.toString().startsWith("0")
//            && !editHeight.text.toString().startsWith("0"))
//        {
//            return true
//        }
//            return false

        //opção 3: return direto o que for verdadeiro
        return (editWeight.text.toString().isNotEmpty()
            && editHeight.text.toString().isNotEmpty()
            && !editWeight.text.toString().startsWith("0")
            && !editHeight.text.toString().startsWith("0"))
    }
}