package com.example.tiptime

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.SummaryPageBinding
import java.text.NumberFormat

class SummaryPage : AppCompatActivity(){

    private lateinit var binding: SummaryPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = SummaryPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.roundUp.text = ""
        binding.tipResult.text =""
        binding.total.text = ""
        var cost = intent.getDoubleExtra("cost", 0.0)
        text()

        binding.backButton.setOnClickListener {
            val intent = Intent(this@SummaryPage, MainActivity::class.java)
            intent.putExtra("cost", cost)
            startActivity(intent)
        }
    }

    fun text(){
        var total = intent.getDoubleExtra("total", 0.0)
        var roundUp = intent.getDoubleExtra("roundUp", 0.0)
        var tip = intent.getDoubleExtra("tip", 0.0)

        val formattedTotal = NumberFormat.getCurrencyInstance().format(total)
        binding.total.text = getString(R.string.round_up, formattedTotal)

        val formattedRoundUp = NumberFormat.getCurrencyInstance().format(roundUp)
        binding.roundUp.text = getString(R.string.round_up, formattedRoundUp)

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}