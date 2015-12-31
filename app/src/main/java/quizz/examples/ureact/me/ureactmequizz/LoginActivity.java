package quizz.examples.ureact.me.ureactmequizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import me.ureact.tracker.User;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void startGame(View button) {
        EditText userNameField = (EditText) findViewById(R.id.user_name);
        EditText userEmailField = (EditText) findViewById(R.id.user_email);

        String name = userNameField.getText().toString();
        String email = userEmailField.getText().toString();

        // Validate for empty fields
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please, inser your name and email", Toast.LENGTH_LONG).show();
            return;
        }

        // Validate for invalid email format
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please, insert a valid e-mail", Toast.LENGTH_LONG).show();
            return;
        }

        // Set UReact.me user data
        User user = User.getInstance(this)
                .setId(email)
                .setName(name);

        Intent intent = new Intent(this, QuizzActivity.class);
        startActivity(intent);
    }


}
