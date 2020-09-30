package com.example.find_my_imc

import android.content.Context
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat
import kotlin.math.pow

object ImcUtil {

    /**
     * Método para calculor de imc
     *
     * @param height_field
     * @param weight_field
     * @return imc
     */
    fun calculateImc(height_field: EditText , weight_field: EditText): Double{
        var imc: Double
        var height = if (height_field.text.toString().isNullOrEmpty()) 0.0 else height_field.text.toString().toDouble()
        var weight = if (weight_field.text.toString().isNullOrEmpty()) 0.0 else weight_field.text.toString().toDouble()
        imc = weight.div(height.pow(2))
        return imc
    }

    /**
     * Método para escolher a faixa de imc
     *
     * @param slimness_display
     * @param  normal_display
     * @param overweight_display
     * @param obesity_display
     * @param gravity_obesity_display
     * @return void
     * */
     fun chooseImcStep(slimness_display: Button,
                              normal_display: Button,
                              overweight_display: Button,
                              obesity_display: Button,
                              gravity_obesity_display: Button,
                              applicationContext: Context,
                              imc: Double){

        clearButtonsColor(slimness_display,
            normal_display,
            overweight_display,
            obesity_display,
            gravity_obesity_display,
            applicationContext)

        when {
            imc < 18.5 -> {
                changeButtonColor(slimness_display, false, applicationContext)
            }
            imc in 18.5..24.9 -> {
                changeButtonColor(normal_display, false, applicationContext)
            }
            imc in 25.0..29.9 -> {
                changeButtonColor(overweight_display, false, applicationContext)
            }
            imc in 30.0..39.9 -> {
                changeButtonColor(obesity_display, false, applicationContext)
            }
            imc > 40.0 -> {
                changeButtonColor(gravity_obesity_display, false, applicationContext)
            }
        }
    }

    /**
     * Método para resetar a cor default dos botões de niveis do imc
     *
     * @param slimness_display
     * @param  normal_display
     * @param overweight_display
     * @param obesity_display
     * @param gravity_obesity_display
     * @return void
     * */
     fun clearButtonsColor(slimness_display: Button,
                                  normal_display: Button,
                                  overweight_display: Button,
                                  obesity_display: Button,
                                  gravity_obesity_display: Button,
                                  applicationContext: Context){

        changeButtonColor(slimness_display, true, applicationContext)
        changeButtonColor(normal_display, true, applicationContext)
        changeButtonColor(overweight_display, true, applicationContext)
        changeButtonColor(obesity_display, true, applicationContext)
        changeButtonColor(gravity_obesity_display, true, applicationContext)
     }

    /**
     * Método para modificar a cor do botão para em destaque ou não destaque
     *
     * @param button
     * @param isClear
     * @param applicationContext
     * @return void
     * **/
    private fun changeButtonColor(button: Button, isClear: Boolean, applicationContext: Context){
        if(isClear) button.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.colorDisableButton))
        else button.setBackgroundColor(ContextCompat.getColor(applicationContext,R.color.colorAccent))
    }
}