package com.github.liketechnik.mentalMathTrainer.utils

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.Switch
import com.github.liketechnik.mentalMathTrainer.NewTrainingSession
import com.github.liketechnik.mentalMathTrainer.R
import com.github.liketechnik.mentalMathTrainer.utils.TrainingSessionConstants.Companion.ADDITION
import com.github.liketechnik.mentalMathTrainer.utils.TrainingSessionConstants.Companion.FIRST_ACCESS
import com.github.liketechnik.mentalMathTrainer.utils.TrainingSessionConstants.Companion.MAX_ADDITION_VALUE
import com.github.liketechnik.mentalMathTrainer.utils.TrainingSessionConstants.Companion.NUMBER_OF_EXERCISES

/**
 * @author Florian Warzecha
 * @version 1.0
 * @date 11 of June 2017
 */
fun packTrainingSessionIntent(caller: AppCompatActivity, startClass: Class<*>): Intent {
    val intent: Intent = Intent(caller, startClass)

    val addition: Boolean
    var maxAdditionValue: Int
    var numberOfExercises: Int

    val additionSwitch = caller.findViewById(R.id.addition_switch) as Switch
    addition = additionSwitch.isChecked

    val maxAdditionValueEditText = caller.findViewById(R.id.max_addition_value) as EditText
    val maxAdditionValueString = maxAdditionValueEditText.text.toString()
    try {
        maxAdditionValue = Integer.parseInt(maxAdditionValueString)
    } catch (e: NumberFormatException) {
        maxAdditionValue = TrainingSessionConstants.defaultMaxAdditionValue
    }

    val numberOfExercisesEditText = caller.findViewById(R.id.number_of_exercises) as EditText
    val numberOfExercisesString = numberOfExercisesEditText.text.toString()
    try {
        numberOfExercises = Integer.parseInt(numberOfExercisesString)
    } catch (e: NumberFormatException) {
        numberOfExercises = TrainingSessionConstants.defaultNumberOfExercises
    }

    intent.putExtra(ADDITION, addition)
    intent.putExtra(MAX_ADDITION_VALUE, maxAdditionValue)
    intent.putExtra(NUMBER_OF_EXERCISES, numberOfExercises)
    intent.putExtra(FIRST_ACCESS, false)

    return intent
}

fun applyTrainingSessionIntent(caller: AppCompatActivity) {
    val intent = caller.intent

    if (intent.getBooleanExtra(FIRST_ACCESS, true)) {
        return
    }

    val addition = intent.getBooleanExtra(ADDITION, true)
    val maxAdditionValue = intent.getIntExtra(MAX_ADDITION_VALUE,
            TrainingSessionConstants.defaultMaxAdditionValue)
    val numberOfExercises = intent.getIntExtra(NUMBER_OF_EXERCISES,
            TrainingSessionConstants.defaultNumberOfExercises)

    val additionSwitch = caller.findViewById(R.id.addition_switch) as Switch
    additionSwitch.isChecked = addition

    val maxAdditionValueEditText = caller.findViewById(R.id.max_addition_value) as EditText
    maxAdditionValueEditText.setText(maxAdditionValue.toString())

    val numberOfExercisesEditText = caller.findViewById(R.id.number_of_exercises) as EditText
    numberOfExercisesEditText.setText(numberOfExercises.toString())
}