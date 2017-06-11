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
import com.github.liketechnik.mentalMathTrainer.utils.TrainingSessionConstants.Companion.ADDITION
import com.github.liketechnik.mentalMathTrainer.utils.TrainingSessionConstants.Companion.MAX_ADDITION_VALUE
import com.github.liketechnik.mentalMathTrainer.utils.TrainingSessionConstants.Companion.NUMBER_OF_EXERCISES
import com.github.liketechnik.mentalMathTrainer.utils.TrainingSessionConstants.Companion.defaultMaxAdditionValue
import com.github.liketechnik.mentalMathTrainer.utils.TrainingSessionConstants.Companion.defaultNumberOfExercises
import com.github.liketechnik.mentalMathTrainer.utils.applyTrainingSessionIntent
import com.github.liketechnik.mentalMathTrainer.utils.packTrainingSessionIntent


class CheckYourSettings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_check_your_settings)

        applyTrainingSessionIntent(this)
    }

    /** Called when the user edits the Maximum addition value  */
    fun onClickMaxAdditionValue(view: View) {
        val intent = intent

        val maxAdditionValue = intent.getIntExtra(MAX_ADDITION_VALUE,
                defaultMaxAdditionValue)

        val maxAdditionValueEditText = findViewById(R.id.max_addition_value) as EditText
        maxAdditionValueEditText.setText(maxAdditionValue.toString())

        val helpDialog = noEditingHelpDialogFragment()
        helpDialog.show(supportFragmentManager, "noEditingHelpDialogFragment")
    }

    /** Called when the user edits the Number fo Exercises  */
    fun onClickNumberOfExercises(view: View) {
        val intent = intent

        val numberOfExercises = intent.getIntExtra(NUMBER_OF_EXERCISES,
                defaultNumberOfExercises)

        val numberOfExercisesEditText = findViewById(R.id.number_of_exercises) as EditText
        numberOfExercisesEditText.setText(numberOfExercises.toString())

        val helpDialog = noEditingHelpDialogFragment()
        helpDialog.show(supportFragmentManager, "noEditingHelpDialogFragment")
    }

    /** Called when the user clicks on the Addition switch  */
    fun onClickAdditionSwitch(view: View) {
        val intent = intent

        val addition = intent.getBooleanExtra(ADDITION, true)

        val additionSwitch = findViewById(R.id.addition_switch) as Switch
        additionSwitch.isChecked = addition

        val helpDialog = noEditingHelpDialogFragment()
        helpDialog.show(supportFragmentManager, "noEditingHelpDialogFragment")
    }

    /** Builder for the help dialog about not editing  */
   inner class noEditingHelpDialogFragment : DialogFragment() {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val builder = AlertDialog.Builder(activity)
            builder.setMessage(R.string.no_editing_help)
                    .setTitle(R.string.no_editing_help_title)
                    .setPositiveButton(R.string.ok) { _, _ ->
                        // Do nothing, user knows what he wanted to know
                    }
                    .setNegativeButton(R.string.back) { _, _ ->
                        startActivity(packTrainingSessionIntent(this@CheckYourSettings, NewTrainingSession::class.java))
                    }
            return builder.create()
        }
    }
}
