package com.github.liketechnik.mentalMathTrainer;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import com.github.liketechnik.mentalmathtrainer.R;

public class CheckYourSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_check_your_settings);

        Intent intent = getIntent();

        boolean addition = intent.getBooleanExtra(NewTrainingSession.ADDITION, true);
        int maxAdditionValue = intent.getIntExtra(NewTrainingSession.MAX_ADDITION_VALUE,
                NewTrainingSession.defaultMaxAdditionValue);
        int numberOfExercises = intent.getIntExtra(NewTrainingSession.NUMBER_OF_EXERCISES,
                NewTrainingSession.defaultNumberOfExercises);

        Switch additionSwitch = (Switch) findViewById(R.id.addition_switch_check);
        additionSwitch.setChecked(addition);

        EditText maxAdditionValueEditText = (EditText) findViewById(R.id.max_addition_value_check);
        maxAdditionValueEditText.setText(String.valueOf(maxAdditionValue));

        EditText numberOfExercisesEditText = (EditText) findViewById(R.id.number_of_exercises_check);
        numberOfExercisesEditText.setText(String.valueOf(numberOfExercises));


    }

    /** Called when the user edits the Maximum addition value */
    public void onClickMaxAdditionValue (View view) {
        Intent intent = getIntent();

        int maxAdditionValue = intent.getIntExtra(NewTrainingSession.MAX_ADDITION_VALUE,
                NewTrainingSession.defaultMaxAdditionValue);

        EditText maxAdditionValueEditText = (EditText) findViewById(R.id.max_addition_value_check);
        maxAdditionValueEditText.setText(String.valueOf(maxAdditionValue));

        DialogFragment helpDialog = new noEditingHelpDialogFragment();
        helpDialog.show(getSupportFragmentManager(), "noEditingHelpDialogFragment");
    }

    /** Called when the user edits the Number fo Exercises */
    public void onClickNumberOfExercises (View view) {
        Intent intent = getIntent();

        int numberOfExercises = intent.getIntExtra(NewTrainingSession.NUMBER_OF_EXERCISES,
                NewTrainingSession.defaultNumberOfExercises);

        EditText numberOfExercisesEditText = (EditText) findViewById(R.id.number_of_exercises_check);
        numberOfExercisesEditText.setText(String.valueOf(numberOfExercises));

        DialogFragment helpDialog = new noEditingHelpDialogFragment();
        helpDialog.show(getSupportFragmentManager(), "noEditingHelpDialogFragment");
    }

    /** Called when the user clicks on the Addition switch */
    public void onClickAdditionSwitch(View view) {
        Intent intent = getIntent();

        boolean addition = intent.getBooleanExtra(NewTrainingSession.ADDITION, true);

        Switch additionSwitch = (Switch) findViewById(R.id.addition_switch_check);
        additionSwitch.setChecked(addition);

        DialogFragment helpDialog = new noEditingHelpDialogFragment();
        helpDialog.show(getSupportFragmentManager(), "noEditingHelpDialogFragment");
    }

    /** Builder for the help dialog about not editing */
    static public class noEditingHelpDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.no_editing_help)
                    .setTitle(R.string.no_editing_help_title)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick (DialogInterface dialog, int id) {
                            // Do nothing, user knows what he wanted to know
                        }
                    });

            return builder.create();
        }
    }
}
