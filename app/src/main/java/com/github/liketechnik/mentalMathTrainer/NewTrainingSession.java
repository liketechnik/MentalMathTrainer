package com.github.liketechnik.mentalMathTrainer;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import com.github.liketechnik.mentalmathtrainer.R;


public class NewTrainingSession extends AppCompatActivity {

    static public final String ADDITION = "liketechnik.mentalmathtrainer.ADDITION";
    static public final String MAX_ADDITION_VALUE = "liketechnik.mentalmathtrainer.MAX_ADDITION_VALUE";
    static public final String NUMBER_OF_EXERCISES = "liketechnik.mentalmathtrainer.NUMBER_OF_EXERCISES";

    static public final int myMaxAdditionValue = 10000;
    static public final int maxNumberOfExercises = 1000;
    static public final int defaultMaxAdditionValue = 100;
    static public final int defaultNumberOfExercises = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_training_session);
    }

    /** Called when the user uses the addition on/off switch */
    public void onClickAdditionSwitch(View view) {
        //* Makes sure the switch is on, because this is the only arithmetic operation available
        //* and displays a message to the user, saying the switch is not editable
        Context context = getApplicationContext();
        CharSequence text = "This option is not editable!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        Switch addition_switch = (Switch) findViewById(R.id.addition_switch);
        addition_switch.setChecked(true);
    }

    /** Called when the user clicks the ? behind the addition on/off switch */
    public void onClickAdditionSwitchHelp (View view) {
        //* Displays a dialog with the requested help
        DialogFragment helpDialog = new additionSwitchHelpDialogFragment();
        helpDialog.show(getSupportFragmentManager(), "additionSwitchHelpDialogFragment");
     }

    /** Called when the user clicks the ? behind the maxAdditionValue EditText */
    public void onClickMaxAdditionValueHelp (View view) {
        //* Displays a dialog with the requested help/
        DialogFragment helpDialog = new maxAdditionValueHelpDialogFragment();
        helpDialog.show(getSupportFragmentManager(), "maxAdditionValueHelpDialogFragment");
    }

    /** Called when the user enters a value into maxAdditionValue EditText */
    public void onClickMaxAdditionValue (View view) {
        //* Set my maximum value if the users value is bigger
        EditText editText = (EditText) findViewById(R.id.max_addition_value);
        String currentValueString = editText.getText().toString();

        int currentValue;
        try {
            currentValue = Integer.parseInt(currentValueString);
        }
        catch(NumberFormatException e) {
            currentValue = 100;
        }

       if(currentValue > myMaxAdditionValue) {
            editText.setText(String.valueOf(myMaxAdditionValue));
        }
    }

    /** Called when the user enters a value into NumberOfExercises EditText */
    public void onClickNumberOfExercises(View view) {
        //* Set my maximum value if the users value is bigger
        EditText editText = (EditText) findViewById(R.id.number_of_exercises);
        String currentValueString = editText.getText().toString();

        int currentValue;
        try {
            currentValue = Integer.parseInt(currentValueString);
        }
        catch(NumberFormatException e) {
            currentValue = 10;
        }

        if(currentValue > maxNumberOfExercises) {
            editText.setText(String.valueOf(maxNumberOfExercises));
        }
    }

    /** Called when the user click the ? behind the NumberOfExercises EditText */
    public void onClickNumberOfExercisesHelp(View view) {
        //* Displays a dialog with the requested help
        DialogFragment helpDialog = new numberOfExercisesHelpDialogFragment();
        helpDialog.show(getSupportFragmentManager(), "numberOfExercisesHelpDialogFragment");
    }

    /** Called when the user clicks the "Go on!" button */
    public void onClickNextActivity(View view) {
        //* Starts the next Activity (Check Your settings)
        Intent intent = new Intent(this, CheckYourSettings.class);

        boolean addition;
        int maxAdditionValue;
        int numberOfExercises;

        Switch additionSwitch = (Switch) findViewById(R.id.addition_switch);
        if(additionSwitch.isChecked()) {
            addition = true;
        }
        else {
            addition = false;
        }

        EditText maxAdditionValueEditText = (EditText) findViewById(R.id.max_addition_value);
        String maxAdditionValueString = maxAdditionValueEditText.getText().toString();
        try {
            maxAdditionValue = Integer.parseInt(maxAdditionValueString);
        }
        catch (NumberFormatException e) {
            maxAdditionValue = defaultMaxAdditionValue;
        }

        EditText numberOfExercisesEditText = (EditText) findViewById(R.id.number_of_exercises);
        String numberOfExercisesString = numberOfExercisesEditText.getText().toString();
        try {
            numberOfExercises = Integer.parseInt(numberOfExercisesString);
        }
        catch (NumberFormatException e) {
            numberOfExercises = defaultNumberOfExercises;
        }

        intent.putExtra(ADDITION, addition);
        intent.putExtra(MAX_ADDITION_VALUE, maxAdditionValue);
        intent.putExtra(NUMBER_OF_EXERCISES, numberOfExercises);

        startActivity(intent);
    }

    /** Builder for the help dialog about the maximumNumberOfExercises EditText */
    static public class numberOfExercisesHelpDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.number_of_exercises_help)
                    .setTitle(R.string.number_of_exercises_help_title)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick (DialogInterface dialog, int id) {
                            // Do nothing, user knows what he wanted to know
                        }
                    });

            return builder.create();
        }
    }

    /** Builder for the help dialog about the addition on/off switch */
    static public class additionSwitchHelpDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.addition_switch_help)
                    .setTitle(R.string.addition_switch_help_title)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Do nothing, user knows what he wanted to know
                        }
                    });

            return builder.create();
        }
    }

    /** Builder for the help dialog about the maxAdditionValue field */
    static public class maxAdditionValueHelpDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.max_addition_value_help)
                    .setTitle(R.string.max_addition_value_help_title)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick (DialogInterface dialog, int id) {
                            // Do nothing, user knows what he wnated to know
                        }
                    });

            return builder.create();
        }
    }
}
