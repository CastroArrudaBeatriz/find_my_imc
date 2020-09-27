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

            var imc = ImcUtil.calculateImc(height_field,weight_field)
            imc_display.text = getString(R.string.imc_display_text) +"   "+ String.format("%.1f", imc)

            ImcUtil.chooseImcStep(slimness_display,
                normal_display,
                overweight_display,
                obesity_display,
                gravity_obesity_display,
                applicationContext,
                imc)
        }

        clear_button.setOnClickListener {

            height_field.text.clear()
            weight_field.text.clear()
            imc_display.text = getString(R.string.imc_display_text)

            ImcUtil.clearButtonsColor(slimness_display,
                normal_display,
                overweight_display,
                obesity_display,
                gravity_obesity_display,
                applicationContext)
        }
    }

}