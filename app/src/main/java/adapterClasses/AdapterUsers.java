package adapterClasses;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import be.kuleuven.myandroidapp2.R;
import models.ModelUsers;

//import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterUsers extends RecyclerView.Adapter<AdapterUsers.MyHolder> {

    Context context;
    FirebaseAuth firebaseAuth;
    String uid;

    public AdapterUsers(Context context, List<ModelUsers> list) {
        this.context = context;
        this.list = list;
        //firebaseAuth = FirebaseAuth.getInstance();
        //uid = firebaseAuth.getUid();
    }

    List<ModelUsers> list;

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_users, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        final String hisuid = list.get(position).getUid();
        String userImage = list.get(position).getImage();
        String username = list.get(position).getName();
        String usermail = list.get(position).getEmail();
        String location = list.get(position).getUid();

        holder.name.setText(username);
        holder.email.setText(usermail);
        holder.profiletv.setVisibility(View.VISIBLE);
        try {
            byte[] imageBytes = Base64.decode(userImage, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            holder.profiletv.setImageBitmap(bitmap);
        } catch (Exception e) {

        }
        /*try {
            Glide.with(context).load(userImage).into(holder.profiletv);
        } catch (Exception e) {
        }*/
        holder.profiletv.setOnClickListener(new View.OnClickListener() {

            private RequestQueue requestQueue;

            @Override
            public void onClick(View v) {
                Toast.makeText(context, location, Toast.LENGTH_SHORT).show();


            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        ImageView profiletv;
        TextView name, email;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            profiletv = itemView.findViewById(R.id.imagep);
            name = itemView.findViewById(R.id.namep);
            email = itemView.findViewById(R.id.emailp);
        }
    }
}

