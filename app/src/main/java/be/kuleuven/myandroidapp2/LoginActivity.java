package be.kuleuven.myandroidapp2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private RequestQueue requestQueue;
    private static final String QUEUE_URL = "https://studev.groept.be/api/a21pt206/LogIn/";
    public String email;
    private SharedPreferences Shared_pref;

    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Shared_pref = getSharedPreferences("details", MODE_PRIVATE);
    }

    public void onConfirmLogin_Clicked  (View caller)
    {
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);

    }

    public void onBtnLogin_Clicked (View caller) {
        requestQueue = Volley.newRequestQueue(this);

        EditText textEmailField = (EditText) findViewById(R.id.mailET);
        EditText textPasswordField = (EditText) findViewById(R.id.passET);


        String requestURL = QUEUE_URL + textEmailField.getText() + "/" + textPasswordField.getText();

        JsonArrayRequest submitRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            String responseEmail = "";
                            String responsePassword = "";
                            for (int i = 0; i < response.length(); i++)
                            {
                                Log.e("db", "inside responce");

                                JSONObject curObject = response.getJSONObject(i);
                                responseEmail += curObject.getString("email");
                                responsePassword += curObject.getString("password");
                                SharedPreferences.Editor editor = Shared_pref.edit();
                                editor.putString("email", responseEmail);
                                editor.putString("password", responsePassword);
                                editor.apply();
                                System.out.println(responseEmail);

                            }

                            if (!responseEmail.equals("") && !responsePassword.equals(""))
                            {


                                Intent intent = new Intent(caller.getContext(), Menu.class);
                                startActivity(intent);
                            }


                        } catch (JSONException e) {
                            Log.e("Database", e.getMessage(), e);
                        }
                    }

                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("db", "error");
                    }
                }
        );


        requestQueue.add(submitRequest);

    }

    public  String getEmail(){
        EditText textEmailField = (EditText) findViewById(R.id.mailET);
        String email1 = textEmailField.getText().toString();
        return "djd";

    }
}