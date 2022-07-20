package com.generation.calculadora_de_imc


import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.*
import com.generation.calculadora_de_imc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var genero: String = ""
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
        binding.buttonMulher.setOnClickListener {
            binding.buttonMulher.setBackgroundResource(R.drawable.ic_mulher_clicked)
            binding.buttonHomem.setBackgroundResource(R.drawable.ic_homem_)
            genero = "mulher"

        }
        binding.buttonHomem.setOnClickListener {
            binding.buttonMulher.setBackgroundResource(R.drawable.ic_mulher_)
            binding.buttonHomem.setBackgroundResource(R.drawable.ic_homem_clicked)
            genero = "homem"
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
            if(!validarCampos()){
                Toast.makeText(this, "Confira se todos os campos foram preenchidos", Toast.LENGTH_SHORT).show()
            }
            else{
                showIMCResult()
            }
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

        when{
            calcularIMC() <16 -> categoria.text = String.format("Peso muito")
            calcularIMC() in 16.0..16.9 -> categoria.text = String.format("Peso muito baixo")
            calcularIMC() in 16.9..18.4 -> categoria.text = String.format("Abaixo do peso")
            calcularIMC() in 18.4..24.9 -> categoria.text = String.format("Normal")
            calcularIMC() in 24.9..29.9 -> categoria.text = String.format("Sobre peso")
            calcularIMC() in 29.9..34.9 -> categoria.text = String.format("Obesidade grau I")
            calcularIMC() in 34.9..39.9 -> categoria.text = String.format("Obesidade grau II")
            calcularIMC() >40 -> categoria.text = String.format("Obesidade grau III")
        }
    }

    private fun calcularIMC(): Double {
        val imc = (peso/(altura*altura).toDouble())*10000
        return imc
    }

    private fun validarCampos(): Boolean {
        var error = true
        if (genero == "" || altura == 0){
             error = false
        }
        return error
    }

}