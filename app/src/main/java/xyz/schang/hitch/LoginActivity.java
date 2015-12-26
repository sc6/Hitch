package xyz.schang.hitch;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;


public class LoginActivity extends AppCompatActivity {

    TextView text_login_error;
    EditText edit_username, edit_password;
    Button button_login, button_register;
    ParseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        currentUser = ParseUser.getCurrentUser();

        if(currentUser != null) { //user already verified, skips login activity
            enterHitch();
        } else {

            edit_username = (EditText) findViewById(R.id.edit_username);
            edit_password = (EditText) findViewById(R.id.edit_reg_password);
            button_login = (Button) findViewById(R.id.button_login);
            button_register = (Button) findViewById(R.id.button_register);
            text_login_error = (TextView) findViewById(R.id.text_login_error);

            button_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    text_login_error.setTextColor(Color.parseColor("#00FF00"));
                    text_login_error.setText("Entering Hitch, please wait...");
                    ParseUser.logInInBackground(edit_username.getText().toString(), edit_password.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (e == null) enterHitch();
                            else {
                                text_login_error.setTextColor(Color.parseColor("#FF0000"));
                                text_login_error.setText("Login error: " + e.getMessage());
                            }
                        }
                    });
                }
            });

            button_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openRegisterDialog();
                }
            });
        }
    }

    private void openRegisterDialog() {
        DialogFragment newFragment = new RegisterDialog();
        newFragment.show(getSupportFragmentManager(), "register");
    }

    private void enterHitch() {
        Intent i = new Intent(getApplicationContext(), HitchActivity.class); // Your list's Intent
        i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(i);
    }


}
