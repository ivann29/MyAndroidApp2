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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private RequestQueue requestQueue;
    private static final String QUEUE_URL = "https://studev.groept.be/api/a21pt206/LogIn/";

    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
                            for (int i = 0; i < response.length(); i++) {
                                Log.e("db", "inside responce");

                                JSONObject curObject = response.getJSONObject(i);
                                responseEmail += curObject.getString("email");
                                responsePassword += curObject.getString("password");
                                System.out.println(responseEmail);

                            }

                            if (!responseEmail.equals("") && !responsePassword.equals("")) {
                                Intent intent = new Intent(caller.getContext(), MainMenu.class);
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
}