package be.kuleuven.myandroidapp2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import adapterClasses.RecyclerViewAdapter;

public class findFriends extends AppCompatActivity
{

    private Toolbar mtoolbar;
    private EditText searchInputText;
    private RecyclerView recyclerView;
    private ImageButton searchButton;
    private static final String QUEUE_URL = "https://studev.groept.be/api/a21pt206/searchPeople/";
    private RequestQueue requestQueue;

    private ArrayList<findFriends2> users;

    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friends);



        searchInputText = (EditText) findViewById(R.id.searchtextId);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchButton = (ImageButton) findViewById(R.id.searchButton);
        users = new ArrayList<findFriends2>();

       adapter = new RecyclerViewAdapter(this, users);
       recyclerView.setAdapter(adapter);



    }


    public void onSearchButton_Clicked(View caller)
    {
        requestQueue = Volley.newRequestQueue(this);


        String requestURL = QUEUE_URL + searchInputText.getText();

        JsonArrayRequest submitRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,

                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            String responseEmail = "";

                            for (int i = 0; i < response.length(); i++)
                            {
                                Log.e("db", "inside responce");

                                JSONObject curObject = response.getJSONObject(i);
                                responseEmail += curObject.getString("email");
                               // System.out.println(responseEmail);


                            }


                            if (!responseEmail.equals(""))
                            {
                                Iterator<findFriends2> it= users.iterator();
                                while(it.hasNext())
                                {
                                    findFriends2 element= it.next();
                                    it.remove();
                                }

                                for (int i = 0; i < response.length(); i++)
                                {


                                    JSONObject curObject = response.getJSONObject(i);
                                    responseEmail += curObject.getString("email");
                                    findFriends2 UserToAdd = new findFriends2(responseEmail);


                                    users.add(UserToAdd);
                                    setAdapter();
                                    System.out.println(responseEmail);


                                }

                            }


                        }
                        catch (JSONException e) {
                            Log.e("Database", e.getMessage(), e);
                        }
                    }

                },

                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("db", "error");
                    }
                }
        );


        requestQueue.add(submitRequest);

    }

    private void setAdapter()
    {

        //adapter = new RecyclerViewAdapter(this, users);
        recyclerView.setAdapter(adapter);
    }






}

