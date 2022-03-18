package com.example.toiletpapercalculator

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.btnVerifica)
        button.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val txtQtd1: TextView = findViewById(R.id.qtdRolos1)
        val qtd1: BigDecimal = BigDecimal(txtQtd1.text.toString())
        val txtQtd2: TextView = findViewById(R.id.qtdRolos2)
        val qtd2: BigDecimal = BigDecimal(txtQtd2.text.toString())
        val txtComprimento1: TextView = findViewById(R.id.metrosRolo1)
        val comprimento1: BigDecimal = BigDecimal(txtComprimento1.text.toString())
        val txtComprimento2: TextView = findViewById(R.id.metrosRolo2)
        val comprimento2: BigDecimal = BigDecimal(txtComprimento2.text.toString())
        val txtPreco1: TextView = findViewById(R.id.precoRolo1)
        val preco1: BigDecimal = BigDecimal(txtPreco1.text.toString())
        val txtPreco2: TextView = findViewById(R.id.precoRolo2)
        val preco2: BigDecimal = BigDecimal(txtPreco2.text.toString())

        val resultado: String =
            calculaMelhorOpcao(qtd1, comprimento1, preco1, qtd2, comprimento2, preco2)

        val txtResultado: TextView = findViewById(R.id.txtResultado)
        txtResultado.text = resultado
    }

    private fun calculaMelhorOpcao(
        qtd1: BigDecimal,
        comprimento1: BigDecimal,
        preco1: BigDecimal,
        qtd2: BigDecimal,
        comprimento2: BigDecimal,
        preco2: BigDecimal
    ): String {
        var msgRetorno: String = ""
        val escala: Int = 10
        val arredondamento: RoundingMode = RoundingMode.HALF_UP
        val precoPorMetro1: BigDecimal =
            preco1.divide(qtd1.times(comprimento1), escala, arredondamento)
        val precoPorMetro2: BigDecimal =
            preco2.divide(qtd2.times(comprimento2), escala, arredondamento)
        val resultadoComparacao: Int = precoPorMetro1.compareTo(precoPorMetro2)
        if (resultadoComparacao == 0) {
            msgRetorno = "Os preços são iguais R$ $precoPorMetro1 por metro"
        } else if (resultadoComparacao == 1) {
            msgRetorno =
                "Marca 2 R$ $precoPorMetro2 por metro (melhor)" +
                        "\nMarca1 R$ $precoPorMetro1 por metro"
        } else {
            msgRetorno =
                "Marca 1 R$ $precoPorMetro1 por metro (melhor)" +
                        "\nMarca2 R$ $precoPorMetro2 por metro"
        }
        return msgRetorno
    }
}