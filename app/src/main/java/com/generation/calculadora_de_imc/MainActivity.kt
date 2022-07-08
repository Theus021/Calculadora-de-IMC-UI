package com.generation.calculadora_de_imc


import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import com.generation.calculadora_de_imc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var genero: String = "Homem"
    private var altura: Int = 0
    private var peso: Int = 60
    private var idade: Int = 15

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getUserGenero()
        getUserAltura()
        getUserPeso()
        getUserIdade()
        onBtnClicked()
    }

    private fun getUserGenero() {
        binding.Homem.setOnClickListener {
            binding.Mulher.setBackgroundResource(R.drawable.cardbackground)
            binding.Homem.setBackgroundResource(R.drawable.cardbackground_clicked)
            genero = "Homem"

        }

        binding.Mulher.setOnClickListener {
            binding.Mulher.setBackgroundResource(R.drawable.cardbackground_clicked)
            binding.Homem.setBackgroundResource(R.drawable.cardbackground)
            genero = "Mulher"
        }
    }

    private fun getUserAltura() {
         binding.sneekBarAltura.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{

           override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

               binding.Tamanho.text = progress.toString()
               altura = progress

           }

           override fun onStartTrackingTouch(seekBar: SeekBar?) {
           }

           override fun onStopTrackingTouch(seekBar: SeekBar?) {
           }


       })
    }

    private fun getUserPeso() {
        binding.maisPeso.setOnClickListener {
        peso++
        binding.contPeso.text = peso.toString()

        }
        binding.menosPeso.setOnClickListener {
        peso--
        binding.contPeso.text = peso.toString()
        }
    }

    private fun getUserIdade() {
        binding.maisIdade.setOnClickListener {
        idade++
        binding.contIdade.text = idade.toString()

        }
        binding.menosIdade.setOnClickListener {
        idade--
        binding.contIdade.text = idade.toString()

        }
    }

    private fun onBtnClicked(){

        binding.buttonCalcular.setOnClickListener {
            showIMCResult()
        }

    }

    private fun showIMCResult() {
        val dialog = Dialog(this)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        dialog.setCancelable(false)

        dialog.setContentView(R.layout.activity_resultado)

        val resultado: TextView = dialog.findViewById(R.id.resultado)

        val sair: ImageView = dialog.findViewById(R.id.sair)

        val categoria: TextView = dialog.findViewById(R.id.imcCcategoria)


        sair.setOnClickListener {
            dialog.dismiss()
        }

        resultado.text = String.format("%.1f", calcularIMC())
        dialog.show()

         if(calcularIMC()<16){
            categoria.text = String.format("Peso muito baixo")

        }else if (calcularIMC()<16.9 && calcularIMC()>16){
            categoria.text = String.format("Peso muito baixo")


        }else if (calcularIMC()<18.4 && calcularIMC()>16.9){
            categoria.text = String.format("Abaixo do peso")

        } else if (calcularIMC()<24.9 && calcularIMC()>18.4){
            categoria.text = String.format("Normal")

        }else if (calcularIMC()<29.9 && calcularIMC()>24.9){
            categoria.text = String.format("Sobre peso")

        }else if (calcularIMC()<34.9 && calcularIMC()>29.9){
            categoria.text = String.format("Obesidade grau I")

        }else if (calcularIMC()<39.9 && calcularIMC()>34.9){
            categoria.text = String.format("Obesidade grau II")

        }else{
            categoria.text = String.format("Obesidade grau III")
        }
    }




    private fun calcularIMC(): Double {
        val imc = (peso/(altura*altura).toDouble())*10000
        return imc
    }
}