package com.example.tiptime

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.getDoubleExtra("cost", 0.0) != 0.0) {
            binding.costOfService.editText?.setText(intent.getDoubleExtra("cost", 0.0).toString())
        }

        val spinner = binding.changeCurrencySpinner
        if (spinner != null) {
            val adapter = ArrayAdapter.createFromResource(
                this,
                R.array.currency_list,
                android.R.layout.simple_spinner_item
            )
            spinner.adapter = adapter
        }

        binding.calculateButton.setOnClickListener {
            calculateTip()
        }

        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(view, keyCode)
        }
    }

    private fun calculateTip() {
        val currency = binding.changeCurrencySpinner.selectedItem.toString()
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        var cost = stringInTextField.toDoubleOrNull()
        val euro = cost

        //bad return, check inverse or do it in a function (best way is in a get of class)
        if (cost == null) {
            Toast.makeText(applicationContext, R.string.toast, Toast.LENGTH_SHORT).show()
            return
        }

        var total = when (currency){
            "Dollars" -> cost * 0.9814
            "Pounds" -> cost * 0.8675
            else -> cost
        }

        cost = total

        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 20
            R.id.option_eighteen_percent -> 18
            R.id.option_fifteen_percent -> 15
            else -> 0
        }

        var tip = tipPercentage * (cost / 100)
        var roundUp = tip
        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }

        total += tip

        roundUp = tip - roundUp

        val intent = Intent(this@MainActivity, SummaryPage::class.java)
        intent.putExtra("total", total)
        intent.putExtra("roundUp", roundUp)
        intent.putExtra("tip", tip)
        intent.putExtra("currency", currency)
        intent.putExtra("cost", cost)
        intent.putExtra("euro", euro)
        intent.putExtra("percentage", tipPercentage)
        startActivity(intent)
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}