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
import com.github.liketechnik.mentalMathTrainer.utils.TrainingSessionConstants.Companion.maxNumberOfExercises
import com.github.liketechnik.mentalMathTrainer.utils.TrainingSessionConstants.Companion.myMaxAdditionValue
import com.github.liketechnik.mentalMathTrainer.utils.applyTrainingSessionIntent
import com.github.liketechnik.mentalMathTrainer.utils.packTrainingSessionIntent


class NewTrainingSession : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_training_session)

        applyTrainingSessionIntent(this)
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
        startActivity(packTrainingSessionIntent(this, CheckYourSettings::class.java))
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
}
