package be.kuleuven.myandroidapp2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class TestIvan extends AppCompatActivity {
    private RequestQueue requestQueue;
    private static final String SUBMIT_URL = "https://studev.groept.be/api/a21pt206/SignUp/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_ivan);
    }
    public void onBackLoginButton_Clicked  (View caller)
    {
        Intent intent = new Intent(this,MainActivity.class);
        //intent.putExtra("Order",order);
        startActivity(intent);

    }
    public void onBtnNext(View caller) {

        EditText textEmail = (EditText) findViewById(R.id.emailET);
        EditText textPassword = (EditText) findViewById(R.id.passwordET);
        EditText textName = (EditText) findViewById(R.id.nameET);
        EditText textGender = (EditText) findViewById(R.id.genderET);
        EditText textAge = (EditText) findViewById(R.id.ageET);

        requestQueue = Volley.newRequestQueue(this);

        Bundle info = getIntent().getExtras();

        String requestURL = SUBMIT_URL  +
                textName.getText()  +"/" +
                textEmail.getText() + "/" +
                textPassword.getText() + "/" +
                textGender.getText() + "/" +
                textAge.getText() ;
        Log.d("Database","creating response");

        StringRequest submitRequest = new StringRequest(Request.Method.GET, requestURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Database","response received");
                        Intent intent = new Intent(caller.getContext(), MainActivity.class);
                        startActivity(intent);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }

        );


        requestQueue.add(submitRequest);
        Log.d("Database","response sent");
    }



}



