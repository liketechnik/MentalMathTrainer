package com.github.liketechnik.mentalMathTrainer

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Switch
import com.github.liketechnik.mentalMathTrainer.R

class CheckYourSettings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_check_your_settings)

        val intent = intent

        val addition = intent.getBooleanExtra(NewTrainingSession.ADDITION, true)
        val maxAdditionValue = intent.getIntExtra(NewTrainingSession.MAX_ADDITION_VALUE,
                NewTrainingSession.defaultMaxAdditionValue)
        val numberOfExercises = intent.getIntExtra(NewTrainingSession.NUMBER_OF_EXERCISES,
                NewTrainingSession.defaultNumberOfExercises)

        val additionSwitch = findViewById(R.id.addition_switch_check) as Switch
        additionSwitch.isChecked = addition

        val maxAdditionValueEditText = findViewById(R.id.max_addition_value_check) as EditText
        maxAdditionValueEditText.setText(maxAdditionValue.toString())

        val numberOfExercisesEditText = findViewById(R.id.number_of_exercises_check) as EditText
        numberOfExercisesEditText.setText(numberOfExercises.toString())


    }

    /** Called when the user edits the Maximum addition value  */
    fun onClickMaxAdditionValue(view: View) {
        val intent = intent

        val maxAdditionValue = intent.getIntExtra(NewTrainingSession.MAX_ADDITION_VALUE,
                NewTrainingSession.defaultMaxAdditionValue)

        val maxAdditionValueEditText = findViewById(R.id.max_addition_value_check) as EditText
        maxAdditionValueEditText.setText(maxAdditionValue.toString())

        val helpDialog = noEditingHelpDialogFragment()
        helpDialog.show(supportFragmentManager, "noEditingHelpDialogFragment")
    }

    /** Called when the user edits the Number fo Exercises  */
    fun onClickNumberOfExercises(view: View) {
        val intent = intent

        val numberOfExercises = intent.getIntExtra(NewTrainingSession.NUMBER_OF_EXERCISES,
                NewTrainingSession.defaultNumberOfExercises)

        val numberOfExercisesEditText = findViewById(R.id.number_of_exercises_check) as EditText
        numberOfExercisesEditText.setText(numberOfExercises.toString())

        val helpDialog = noEditingHelpDialogFragment()
        helpDialog.show(supportFragmentManager, "noEditingHelpDialogFragment")
    }

    /** Called when the user clicks on the Addition switch  */
    fun onClickAdditionSwitch(view: View) {
        val intent = intent

        val addition = intent.getBooleanExtra(NewTrainingSession.ADDITION, true)

        val additionSwitch = findViewById(R.id.addition_switch_check) as Switch
        additionSwitch.isChecked = addition

        val helpDialog = noEditingHelpDialogFragment()
        helpDialog.show(supportFragmentManager, "noEditingHelpDialogFragment")
    }

    /** Builder for the help dialog about not editing  */
    class noEditingHelpDialogFragment : DialogFragment() {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val builder = AlertDialog.Builder(activity)
            builder.setMessage(R.string.no_editing_help)
                    .setTitle(R.string.no_editing_help_title)
                    .setPositiveButton(R.string.ok) { dialog, id ->
                        // Do nothing, user knows what he wanted to know
                    }
                    .setNegativeButton(R.string.back) { dialogInterface, i -> }

            return builder.create()
        }
    }
}
