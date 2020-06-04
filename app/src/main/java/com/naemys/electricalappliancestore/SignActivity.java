package com.naemys.electricalappliancestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.naemys.electricalappliancestore.units.Unit;

import java.util.HashMap;
import java.util.Map;

public class SignActivity extends AppCompatActivity {
    private EditText loginEditText, passwordEditText;
    private Button signButton;
    private TextView toggleTextView;

    private boolean isSignIn = true;

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        requestQueue = Volley.newRequestQueue(this);

        loginEditText = findViewById(R.id.loginEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        signButton = findViewById(R.id.signButton);
        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = loginEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (isSignIn) {
                    signIn(login, password);
                } else {
                    signUp(login, password);
                }
            }
        });

        toggleTextView = findViewById(R.id.toggleTextView);
    }

    private void signUp(final String login, final String password) {

        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST,
                Unit.URL_SIGN_UP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(SignActivity.class.getSimpleName(), "onResponse: " + response);
                        if(response.equals("{\"success\":1,\"message\":\"User successfully created.\"}")) {
                            Toast.makeText(SignActivity.this, "Sign up successfully", Toast.LENGTH_LONG).show();

                            startActivity(new Intent(SignActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(SignActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> m = new HashMap<>();
                m.put("login", login);
                m.put("password", password);
                return m;
            }
        };

        requestQueue.add(stringRequest);

    }

    private void signIn(final String login, final String password) {

        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST,
                Unit.URL_SIGN_IN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(SignActivity.class.getSimpleName(), "onResponse: " + response);
                        if(response.equals("{\"success\":1,\"message\":\"Sign in successfully.\"}")) {
                            Toast.makeText(SignActivity.this, "Sign in successfully.", Toast.LENGTH_LONG).show();

                            startActivity(new Intent(SignActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(SignActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> m = new HashMap<>();
                m.put("login", login);
                m.put("password", password);
                return m;
            }
        };

        requestQueue.add(stringRequest);
    }


    public void toggle(View view) {
        if (isSignIn) {
            signButton.setText(R.string.signUpButton);
            toggleTextView.setText(R.string.toggle_sign_in);

            isSignIn = false;
        } else {
            signButton.setText(R.string.sign_in_button);
            toggleTextView.setText(R.string.toggle_sign_up);

            isSignIn = true;
        }
    }
}
