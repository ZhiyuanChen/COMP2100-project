package cs.anu.edu.au.comp2100.weiming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cs.anu.edu.au.comp2100.weiming.ui.login.ANULogin;

public class LoginOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_options);
    }

    public void loginPress(View view) {
        Intent intent = new Intent(this, ANULogin.class);
        startActivity(intent);
    }

    public void manualPress(View view) {
        Intent intent = new Intent(this, ManualInput.class);
        startActivity(intent);
    }
}
