package xyz.schang.hitch;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.telephony.PhoneNumberUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;


/**
 * Created by Steve on 12/20/2015.
 */
public class RegisterDialog extends DialogFragment {

    private EditText edit_reg_email, edit_reg_password, edit_reg_repeatPassword, edit_reg_firstName, edit_reg_lastName, edit_reg_phone;
    private TextView text_reg_error;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.register_layout, null);

        edit_reg_email = (EditText) view.findViewById(R.id.edit_reg_email);
        edit_reg_firstName = (EditText) view.findViewById(R.id.edit_reg_firstName);
        edit_reg_lastName = (EditText) view.findViewById(R.id.edit_reg_lastName);
        edit_reg_password = (EditText) view.findViewById(R.id.edit_reg_password);
        edit_reg_repeatPassword = (EditText) view.findViewById(R.id.edit_reg_repeatPassword);
        edit_reg_phone = (EditText) view.findViewById(R.id.edit_reg_phone);
        text_reg_error = (TextView) view.findViewById(R.id.text_reg_error);

        builder.setView(view)
                .setTitle("Create an account")
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //overridden below in onStart()
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        return builder.create();
    }

    @Override
    public void onStart()
    {
        super.onStart();    //super.onStart() is where dialog.show() is actually called on the underlying dialog, so we have to do it after this point
        final AlertDialog d = (AlertDialog)getDialog();
        if(d != null)
        {
            Button positiveButton = (Button) d.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    boolean wantToCloseDialog = false;

                    if (!edit_reg_password.getText().toString().equals(edit_reg_repeatPassword.getText().toString())) {
                        text_reg_error.setText("Sorry, your password and repeat password must match to register.");
                    } else if (edit_reg_password.getText().toString().length() < 6) {
                        text_reg_error.setText("Sorry, passwords must be at least 6 characters long.");
                    } else if (edit_reg_email.getText().equals("")) {
                        text_reg_error.setText("Sorry, you need to provide your email in order to register.");
                    } else if (edit_reg_firstName.getText().equals("")) {
                        text_reg_error.setText("Sorry, you need to provide your first name in order to register.");
                    } else if (edit_reg_lastName.getText().equals("")) {
                        text_reg_error.setText("Sorry, you need to provide your last name in order to register.");
                    } else if (edit_reg_phone.getText().equals("")) {
                        text_reg_error.setText("Sorry, you need to provide your phone number in order to register.");
                    } else {
                        wantToCloseDialog = true;

                        ParseUser newUser = new ParseUser();
                        newUser.setUsername(edit_reg_email.getText().toString());
                        newUser.setPassword(edit_reg_password.getText().toString());
                        newUser.setEmail(edit_reg_email.getText().toString());
                        newUser.put("fname", edit_reg_firstName.getText().toString());
                        newUser.put("lname", edit_reg_lastName.getText().toString());
                        newUser.put("phone", PhoneNumberUtils.formatNumber(edit_reg_phone.getText().toString(), "US"));

                        newUser.signUpInBackground();
                        Toast.makeText(getContext(), "Great, your account was just created. Now try logging in.", Toast.LENGTH_LONG).show();
                    }

                    //Do stuff, possibly set wantToCloseDialog to true then...
                    if(wantToCloseDialog)
                        d.dismiss();
                    //else dialog stays open. Make sure you have an obvious way to close the dialog especially if you set cancellable to false.
                }
            });
        }
    }



}