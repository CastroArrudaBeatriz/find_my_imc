package com.example.find_my_imc

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.pow


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        height_field.addTextChangedListener(MaskEditUtil.mask(height_field, MaskEditUtil.FORMAT_DECIMAL_HEIGHT));
        weight_field.addTextChangedListener(MaskEditUtil.mask(weight_field, MaskEditUtil.FORMAT_DECIMAL_WEIGHT));

        calculate_button.setOnClickListener {
            var imc = calculateImc()
            imc_display.text = getString(R.string.imc_display_text) +"   "+ String.format("%.1f", imc)
            chooseImcStep(imc)
        }

        clear_button.setOnClickListener {
            height_field.text.clear()
            weight_field.text.clear()
            imc_display.text = getString(R.string.imc_display_text)
            clearButtonsColor()
        }
    }

    private fun calculateImc(): Double{
        var imc: Double
        var height = if (height_field.text.toString().isNullOrEmpty()) 0.0 else height_field.text.toString().toDouble()
        var weight = if (weight_field.text.toString().isNullOrEmpty()) 0.0 else weight_field.text.toString().toDouble()
        imc = weight.div(height.pow(2))
        return imc
    }

    private fun chooseImcStep(imc: Double){
        clearButtonsColor()
        when {
            imc < 18.5 -> {
                changeButtonColor(slimness_display, false)
            }
            imc in 18.5..24.9 -> {
                changeButtonColor(normal_display, false)
            }
            imc in 25.0..29.9 -> {
                changeButtonColor(overweight_display, false)
            }
            imc in 30.0..39.9 -> {
                changeButtonColor(obesity_display, false)
            }
            imc > 40.0 -> {
                changeButtonColor(gravity_obesity_display, false)
            }
        }
    }

    private fun clearButtonsColor(){
        changeButtonColor(slimness_display, true)
        changeButtonColor(normal_display, true)
        changeButtonColor(overweight_display, true)
        changeButtonColor(obesity_display, true)
        changeButtonColor(gravity_obesity_display, true)
    }

    private fun changeButtonColor(button: Button, isClear: Boolean){
        if(isClear) button.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.colorDisableButton))
        else button.setBackgroundColor(ContextCompat.getColor(applicationContext,R.color.colorAccent))
    }

}