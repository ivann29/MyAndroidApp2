package adapterClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import be.kuleuven.myandroidapp2.R;
import be.kuleuven.myandroidapp2.findFriends2;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>
{
    private Context context;
    public List<findFriends2> list = new ArrayList<findFriends2>();

    public RecyclerViewAdapter(Context context, List<findFriends2> list)
    {
        this.context = context;
        this.list = list;
    }

    public List<findFriends2> getList()
    {
        return list;
    }



    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_users_display_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position)
    {
        holder.email.setText(list.get(position).getEmail());


    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView email;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            email = itemView.findViewById(R.id.userEmail);
        }
    }
}
