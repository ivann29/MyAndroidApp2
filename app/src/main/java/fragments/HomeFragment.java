package fragments;


import android.app.ProgressDialog;
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

import adapterClasses.AdapterPosts;
import be.kuleuven.myandroidapp2.R;
import models.ModelPost;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    //FirebaseAuth firebaseAuth;
    private RequestQueue requestQueue;
    private static final String QUEUE_URL = "https://studev.groept.be/api/a21pt206/TestIvan";
    String myuid;
    RecyclerView recyclerView;
    List<ModelPost> posts;
    AdapterPosts adapterPosts;
    private  ProgressDialog progressDialog;

    public HomeFragment() {
        // Required empty public constructor
        //loadPosts();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //firebaseAuth = FirebaseAuth.getInstance();
        recyclerView = view.findViewById(R.id.postrecyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        posts = new ArrayList<>();
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading, please wait");
        progressDialog.show();
        loadPosts();

        return view;

    }

    private void loadPosts() {
        //requestQueue = Volley.newRequestQueue(this);
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());




        String requestURL = QUEUE_URL ;

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
                                String description = "";
                                String title = "";
                                String image = "";
                                String time = "";
                                String likes = "";

                                JSONObject curObject = response.getJSONObject(i);
                                id += curObject.getString("idBlogg");
                                email += curObject.getString("email");
                                description += curObject.getString("description");
                                title += curObject.getString("title");
                                image += curObject.getString("uimage");
                                time += curObject.getString("time");
                                likes += curObject.getString("likes");

                                System.out.println(title);
                                ModelPost modelPost = new ModelPost( description,  id, time,  "0", title,  "b",  email,  "c",  image,  email, likes);
                                posts.add(modelPost);
                                adapterPosts = new AdapterPosts(getActivity(), posts);
                                recyclerView.setAdapter(adapterPosts);

                            }



                        } catch (JSONException e) {
                            Log.e("Database", e.getMessage(), e);
                        }
                    }

                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e("db", "error");
                    }
                }
        );


        requestQueue.add(submitRequest);

    }
        /*DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Posts");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                posts.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    ModelPost modelPost = dataSnapshot1.getValue(ModelPost.class);
                    posts.add(modelPost);
                    adapterPosts = new AdapterPosts(getActivity(), posts);
                    recyclerView.setAdapter(adapterPosts);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });*/


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

}



