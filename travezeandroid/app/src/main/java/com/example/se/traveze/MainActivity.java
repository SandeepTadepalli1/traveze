package com.example.se.traveze;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnSignIn = (Button) findViewById(R.id.btnSingIn);
        final Button btnRegister = (Button) findViewById(R.id.btnRegister);

        btnSignIn.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent i = null;
        switch(v.getId()){
            case R.id.btnSingIn:
                i = new Intent(this,SignInActivity.class);
                break;
            case R.id.btnRegister:
                i = new Intent(this,RegisterActivity.class);
                break;
        }
        startActivity(i);
    }

}
