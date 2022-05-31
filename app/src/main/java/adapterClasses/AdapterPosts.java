package adapterClasses;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

import be.kuleuven.myandroidapp2.R;
import models.ModelPost;

public class AdapterPosts extends RecyclerView.Adapter<AdapterPosts.MyHolder> {

    Context context;


    public AdapterPosts(Context context, List<ModelPost> modelPosts) {
        this.context = context;
        this.modelPosts = modelPosts;

    }

    List<ModelPost> modelPosts;

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_posts, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, final int position) {

        String nameh = modelPosts.get(position).getUname();
        final String titlee = modelPosts.get(position).getTitle();
        final String descri = modelPosts.get(position).getDescription();
        final String ptime = modelPosts.get(position).getPtime();
        String plike = modelPosts.get(position).getPlike();
        final String image = modelPosts.get(position).getUimage();



        String comm = modelPosts.get(position).getPcomments();
        holder.name.setText(nameh);
        holder.title.setText(titlee);
        holder.description.setText(descri);
        holder.time.setText(ptime);
        holder.like.setText(plike + " Likes");
        holder.comments.setText(comm + " Comments");

        try {


            byte[] imageBytes = Base64.decode(image, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            holder.picture.setImageBitmap(bitmap);

        } catch (Exception e) {

        }
        holder.image.setVisibility(View.VISIBLE);
        try {
            byte[] imageBytes = Base64.decode(image, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            holder.image.setImageBitmap(bitmap);
        } catch (Exception e) {

        }

        holder.likebtn.setOnClickListener(new View.OnClickListener() {

            private RequestQueue requestQueue;
            private static final String SUBMIT_URL = "https://studev.groept.be/api/a21pt206/likes/";
            int check = 1;

            @Override
            public void onClick(View v) {
                int plike = Integer.parseInt(modelPosts.get(holder.getAdapterPosition()).getPlike());

                if (check == 1) {

                    plike += 1;
                    String l = Integer.toString(plike);
                    requestQueue = Volley.newRequestQueue(v.getContext());
                    String requestURL = SUBMIT_URL +
                            l + "/" +
                            modelPosts.get(holder.getAdapterPosition()).getTitle();
                    Log.d("Database", "creating response");

                    StringRequest submitRequest = new StringRequest(Request.Method.GET, requestURL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("Database", "response received");
                                    System.out.println("success");
                                }
                            },

                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            }

                    );


                    requestQueue.add(submitRequest);
                    Log.d("Database", "response sent");

                    modelPosts.get(holder.getAdapterPosition()).setPlike(l);
                    holder.like.setText(plike + " Likes");

                    check = 0;
                } else {

                    plike = plike - 1;
                    String l = Integer.toString(plike);
                    modelPosts.get(holder.getAdapterPosition()).setPlike(l);
                    holder.like.setText(plike + " Likes");
                    check = 1;

                }

            }
        });

    }




    @Override
    public int getItemCount() {
        return modelPosts.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView picture, image;
        TextView name, time, title, description, like, comments;
        ImageButton more;
        Button likebtn, comment;
        LinearLayout profile;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.pimagetv);
            name = itemView.findViewById(R.id.unametv);
            time = itemView.findViewById(R.id.utimetv);
            more = itemView.findViewById(R.id.morebtn);
            title = itemView.findViewById(R.id.ptitletv);
            description = itemView.findViewById(R.id.descript);
            like = itemView.findViewById(R.id.plikeb);
            comments = itemView.findViewById(R.id.pcommentco);
            likebtn = itemView.findViewById(R.id.like);
            comment = itemView.findViewById(R.id.comment);
            profile = itemView.findViewById(R.id.profilelayout);
        }
    }
}