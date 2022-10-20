package com.example.tiptime

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.SummaryPageBinding
import java.text.NumberFormat

class SummaryPage : AppCompatActivity() {

    private lateinit var binding: SummaryPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = SummaryPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //init 2 new info
        binding.cost.text = ""
        binding.percentage.text = ""
        binding.roundUp.text = ""
        binding.tipResult.text = ""
        binding.total.text = ""

        //recompile and reload for percentage too
        val cost = intent.getDoubleExtra("cost", 0.0)
        text()

        binding.backButton.setOnClickListener {
            val intent = Intent(this@SummaryPage, MainActivity::class.java)
            intent.putExtra("cost", cost)
            startActivity(intent)
        }
    }

    //made it private
    private fun text() {
        //variable switched from var to val
        val total = intent.getDoubleExtra("total", 0.0)
        val roundUp = intent.getDoubleExtra("roundUp", 0.0)
        val tip = intent.getDoubleExtra("tip", 0.0)

        //declaring more info
        val cost = intent.getDoubleExtra("cost", 0.0)
        val percentage = intent.getIntExtra("percentage", 0)

        //adding 2 new info
        val formattedCost = NumberFormat.getCurrencyInstance().format(cost)
        binding.cost.text = getString(R.string.cost, formattedCost)
        binding.percentage.text = getString(R.string.percentage, "$percentage%")

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)

        val formattedRoundUp = NumberFormat.getCurrencyInstance().format(roundUp)
        binding.roundUp.text = getString(R.string.round_up, formattedRoundUp)

        val formattedTotal = NumberFormat.getCurrencyInstance().format(total)
        binding.total.text = getString(R.string.total, formattedTotal)
    }
}