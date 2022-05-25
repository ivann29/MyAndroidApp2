package fragments;



import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import java.util.List;

import adapterClasses.AdapterUsers;
import be.kuleuven.myandroidapp2.R;
import models.ModelUsers;

//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    RecyclerView recyclerView;
    AdapterUsers adapterUsers;
    List<ModelUsers> usersList;
    //FirebaseAuth firebaseAuth;
    private RequestQueue requestQueue;
    private RequestQueue requestQueue1;
    private static final String QUEUE_URL = "https://studev.groept.be/api/a21pt206/userDay/";
    private static final String QUEUE_URL2 = "https://studev.groept.be/api/a21pt206/findDay/";
    private SharedPreferences newPreference;
    protected  String interest1;
    private ProgressDialog progressDialog;
    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        recyclerView = view.findViewById(R.id.recyclep);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        usersList = new ArrayList<>();
         progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading, please wait");
        progressDialog.show();
        getInterests();
        getAllUsers();
        System.out.println("hey2");
        return view;
    }
    private void getInterests(){
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());


        //usersList.clear();
        newPreference = getActivity().getSharedPreferences("details", Context.MODE_PRIVATE);
        String email = newPreference.getString("email", null);
        String password = newPreference.getString("password", null);
        String requestURL = QUEUE_URL2+email ;

        JsonArrayRequest submitRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {


                            for (int i = 0; i < response.length(); i++) {
                                Log.e("db", "inside responce");

                                String interest = "";


                                JSONObject curObject = response.getJSONObject(i);

                                interest += curObject.getString("freetime");

                                interest1=interest;
                                System.out.println(interest1);
                                getAllUsers();




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
    private void getAllUsers() {
        System.out.println("hey");
        requestQueue1 = Volley.newRequestQueue(getActivity().getApplicationContext());


        //usersList.clear();
        newPreference = getActivity().getSharedPreferences("details", Context.MODE_PRIVATE);
        String email = newPreference.getString("email", null);
        String password = newPreference.getString("password", null);
        String requestURL = QUEUE_URL+email+ "/"+interest1 ;
        System.out.println(requestURL);

        JsonArrayRequest submitRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            progressDialog.dismiss();


                            for (int i = 0; i < response.length(); i++) {
                                Log.e("db", "inside responce");
                                String id = "";
                                String email = "";
                                String freeTime = "";
                                String location = "";
                                String image = "";
                                String interests = "";


                                JSONObject curObject = response.getJSONObject(i);
                                id += curObject.getString("idprofiles");
                                location += curObject.getString("location");
                                interests += curObject.getString("interests");
                                freeTime += curObject.getString("freetime");
                                email += curObject.getString("emaill");
                                image += curObject.getString("profileImage");


                                //System.out.println(title);
                                ModelUsers modelUsers = new ModelUsers( email,  location, "ivan",  freeTime, image,  location);

                                usersList.add(modelUsers);
                                adapterUsers = new AdapterUsers(getActivity(), usersList);
                                recyclerView.setAdapter(adapterUsers);


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


        requestQueue1.add(submitRequest);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }
}