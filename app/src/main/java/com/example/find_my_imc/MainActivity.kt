package com.example.find_my_imc

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat
import kotlin.math.pow


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        height_field.addTextChangedListener(MaskEditUtil.mask(height_field, MaskEditUtil.FORMAT_DECIMAL_HEIGHT));
        weight_field.addTextChangedListener(MaskEditUtil.mask(weight_field, MaskEditUtil.FORMAT_DECIMAL_WEIGHT));

        calculate_button.setOnClickListener {
            var imc: Double
            var height = if (height_field.text.toString().isNullOrEmpty()) 0.0 else height_field.text.toString().toDouble()
            var weight = if (weight_field.text.toString().isNullOrEmpty()) 0.0 else weight_field.text.toString().toDouble()
            imc = weight.div(height.pow(2))
            imc_display.setText(getString(R.string.imc_display_text) +"   "+ String.format("%.1f", imc))
        }

        clear_button.setOnClickListener {
            height_field.text.clear()
            weight_field.text.clear()
            imc_display.text = getString(R.string.imc_display_text)
        }
    }
}