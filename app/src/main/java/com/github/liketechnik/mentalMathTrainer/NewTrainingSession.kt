package com.github.liketechnik.mentalMathTrainer

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import com.github.liketechnik.mentalMathTrainer.R


class NewTrainingSession : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_training_session)
    }

    /** Called when the user uses the addition on/off switch  */
    fun onClickAdditionSwitch(view: View) {
        //* Makes sure the switch is on, because this is the only arithmetic operation available
        //* and displays a message to the user, saying the switch is not editable
        val context = applicationContext
        val text = "This option is not editable!"
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(context, text, duration)
        toast.show()

        val addition_switch = findViewById(R.id.addition_switch) as Switch
        addition_switch.isChecked = true
    }

    /** Called when the user clicks the ? behind the addition on/off switch  */
    fun onClickAdditionSwitchHelp(view: View) {
        //* Displays a dialog with the requested help
        val helpDialog = additionSwitchHelpDialogFragment()
        helpDialog.show(supportFragmentManager, "additionSwitchHelpDialogFragment")
    }

    /** Called when the user clicks the ? behind the maxAdditionValue EditText  */
    fun onClickMaxAdditionValueHelp(view: View) {
        //* Displays a dialog with the requested help/
        val helpDialog = maxAdditionValueHelpDialogFragment()
        helpDialog.show(supportFragmentManager, "maxAdditionValueHelpDialogFragment")
    }

    /** Called when the user enters a value into maxAdditionValue EditText  */
    fun onClickMaxAdditionValue(view: View) {
        //* Set my maximum value if the users value is bigger
        val editText = findViewById(R.id.max_addition_value) as EditText
        val currentValueString = editText.text.toString()

        var currentValue: Int
        try {
            currentValue = Integer.parseInt(currentValueString)
        } catch (e: NumberFormatException) {
            currentValue = 100
        }

        if (currentValue > myMaxAdditionValue) {
            editText.setText(myMaxAdditionValue.toString())
        }
    }

    /** Called when the user enters a value into NumberOfExercises EditText  */
    fun onClickNumberOfExercises(view: View) {
        //* Set my maximum value if the users value is bigger
        val editText = findViewById(R.id.number_of_exercises) as EditText
        val currentValueString = editText.text.toString()

        var currentValue: Int
        try {
            currentValue = Integer.parseInt(currentValueString)
        } catch (e: NumberFormatException) {
            currentValue = 10
        }

        if (currentValue > maxNumberOfExercises) {
            editText.setText(maxNumberOfExercises.toString())
        }
    }

    /** Called when the user click the ? behind the NumberOfExercises EditText  */
    fun onClickNumberOfExercisesHelp(view: View) {
        //* Displays a dialog with the requested help
        val helpDialog = numberOfExercisesHelpDialogFragment()
        helpDialog.show(supportFragmentManager, "numberOfExercisesHelpDialogFragment")
    }

    /** Called when the user clicks the "Go on!" button  */
    fun onClickNextActivity(view: View) {
        //* Starts the next Activity (Check Your settings)
        val intent = Intent(this, CheckYourSettings::class.java)

        val addition: Boolean
        var maxAdditionValue: Int
        var numberOfExercises: Int

        val additionSwitch = findViewById(R.id.addition_switch) as Switch
        if (additionSwitch.isChecked) {
            addition = true
        } else {
            addition = false
        }

        val maxAdditionValueEditText = findViewById(R.id.max_addition_value) as EditText
        val maxAdditionValueString = maxAdditionValueEditText.text.toString()
        try {
            maxAdditionValue = Integer.parseInt(maxAdditionValueString)
        } catch (e: NumberFormatException) {
            maxAdditionValue = defaultMaxAdditionValue
        }

        val numberOfExercisesEditText = findViewById(R.id.number_of_exercises) as EditText
        val numberOfExercisesString = numberOfExercisesEditText.text.toString()
        try {
            numberOfExercises = Integer.parseInt(numberOfExercisesString)
        } catch (e: NumberFormatException) {
            numberOfExercises = defaultNumberOfExercises
        }

        intent.putExtra(ADDITION, addition)
        intent.putExtra(MAX_ADDITION_VALUE, maxAdditionValue)
        intent.putExtra(NUMBER_OF_EXERCISES, numberOfExercises)

        startActivity(intent)
    }

    /** Builder for the help dialog about the maximumNumberOfExercises EditText  */
    class numberOfExercisesHelpDialogFragment : DialogFragment() {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val builder = AlertDialog.Builder(activity)
            builder.setMessage(R.string.number_of_exercises_help)
                    .setTitle(R.string.number_of_exercises_help_title)
                    .setPositiveButton(R.string.ok) { dialog, id ->
                        // Do nothing, user knows what he wanted to know
                    }

            return builder.create()
        }
    }

    /** Builder for the help dialog about the addition on/off switch  */
    class additionSwitchHelpDialogFragment : DialogFragment() {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val builder = AlertDialog.Builder(activity)
            builder.setMessage(R.string.addition_switch_help)
                    .setTitle(R.string.addition_switch_help_title)
                    .setPositiveButton(R.string.ok) { dialog, id ->
                        // Do nothing, user knows what he wanted to know
                    }

            return builder.create()
        }
    }

    /** Builder for the help dialog about the maxAdditionValue field  */
    class maxAdditionValueHelpDialogFragment : DialogFragment() {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val builder = AlertDialog.Builder(activity)
            builder.setMessage(R.string.max_addition_value_help)
                    .setTitle(R.string.max_addition_value_help_title)
                    .setPositiveButton(R.string.ok) { dialog, id ->
                        // Do nothing, user knows what he wnated to know
                    }

            return builder.create()
        }
    }

    companion object {

        val ADDITION = "liketechnik.mentalmathtrainer.ADDITION"
        val MAX_ADDITION_VALUE = "liketechnik.mentalmathtrainer.MAX_ADDITION_VALUE"
        val NUMBER_OF_EXERCISES = "liketechnik.mentalmathtrainer.NUMBER_OF_EXERCISES"

        val myMaxAdditionValue = 10000
        val maxNumberOfExercises = 1000
        val defaultMaxAdditionValue = 100
        val defaultNumberOfExercises = 10
    }
}
