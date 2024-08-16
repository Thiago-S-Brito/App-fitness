package co.tiagoaguiar.fitnesstracker

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
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

            val weight = editWeight.text.toString().toInt()
            val height = editHeight.text.toString().toInt()

            val result = calculateImc(weight, height)
            Log.d("teste", "resultado: $result")

            val imcResponseId = imcResponse(result)

            val title = getString(R.string.imc_response, result)

            AlertDialog.Builder(this)

                .setTitle(getString(R.string.imc_response, result))
                .setMessage(imcResponseId)
                //opção 1: utilizando lambda
                .setPositiveButton(android.R.string.ok) { dialog, which ->
                    //aqui vai rodar depois do click
                }
                .create()
                .show()

/*
            //opção 2: dá mais trabalho escrever
//            dialog.setPositiveButton(android.R.string.ok, object  : DialogInterface.OnClickListener {
//                override fun onClick(dialog: DialogInterface?, which: Int) {
//                // aqui vai rodar depois do click
//                }
//
//            })
*/

            val service = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            service.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }
    @StringRes
    private fun imcResponse(imc:Double): Int {
        return when {
            imc < 15.0 -> R.string.imc_severely_low_weight
            imc < 16.0 -> R.string.imc_very_low_weight
            imc < 18.5 -> R.string.imc_low_weight
            imc < 25.0 -> R.string.normal
            imc < 30.0 -> R.string.imc_high_weight
            imc < 35.0 -> R.string.imc_so_high_weight
            imc < 40.0 -> R.string.imc_severely_high_weight
            else -> R.string.imc_extreme_weight

        }
//        if (imc < 15.0) {
//            return R.string.imc_severely_low_weight
//        } else if (imc < 16.0) {
//            return R.string.imc_very_low_weight
//        } else if (imc < 18.5) {
//            return R.string.imc_low_weight
//        } else if (imc < 25.0) {
//            return R.string.normal
//        } else if (imc < 30.0) {
//            return R.string.imc_high_weight
//        } else if (imc < 35.0) {
//            return R.string.imc_so_high_weight
//        } else if (imc < 40.0) {
//            return R.string.imc_severely_high_weight
//        }
//          else {
//            return R.string.imc_extreme_weight
//        }
    }

    private fun  calculateImc(weight: Int, height: Int): Double {
        // peso / (altura * altura) peso dividido pela altura ao quadrado
        return weight / ( (height / 100.0) * (height / 100.0) )
    }

    private fun validate(): Boolean {

        /*
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
*/
        //opção 3: return direto o que for verdadeiro
        return (editWeight.text.toString().isNotEmpty()
            && editHeight.text.toString().isNotEmpty()
            && !editWeight.text.toString().startsWith("0")
            && !editHeight.text.toString().startsWith("0"))
    }
}