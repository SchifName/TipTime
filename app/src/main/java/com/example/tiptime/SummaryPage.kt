package com.example.tiptime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding
import com.example.tiptime.databinding.SummaryPageBinding

class SummaryPage : AppCompatActivity(){

    private lateinit var binding: SummaryPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = SummaryPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.roundUp.text = ""
        binding.tipResult.text =""
        binding.total.text = ""
        binding.backButton.setOnClickListener {  }
    }
}