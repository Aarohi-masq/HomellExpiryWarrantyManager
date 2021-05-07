package com.homell.Homell;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class hAdapter extends RecyclerView.Adapter<hAdapter.MyViewHolder> {
    Context context;
    ArrayList<Expire> expires;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Expiry Items").child(FirebaseAuth.getInstance().getCurrentUser().getUid());




    public hAdapter(Context c, ArrayList<Expire> e){
        context=c;
        expires=e;

    }
    @NonNull
    @Override
    public hAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new hAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.homecard,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull hAdapter.MyViewHolder holder, final int position) {
        holder.editTextName.setText(expires.get(position).getEditTextName());

        holder.productdesc.setText(expires.get(position).getProductdesc());
        holder.realbrand.setText(expires.get(position).getRealbrand());
        holder.date.setText(expires.get(position).getDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), showdata.class);
                intent.putExtra("productId",expires.get(position).getProductId());
                intent.putExtra("date", expires.get(position).getDate());
                intent.putExtra("editTextName", expires.get(position).getEditTextName());
                intent.putExtra("productdesc", expires.get(position).getProductdesc());

                intent.putExtra("realbrand", expires.get(position).getRealbrand());
                v.getContext().startActivity(intent);

            }
        });



    }


    @Override
    public int getItemCount() {
        return expires.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView editTextName, productmonth, date, productdesc, realbrand;
        ImageView edit, del;
        //ImageView pic;;
        public MyViewHolder(View itemView){
            super(itemView);
            editTextName=(TextView) itemView.findViewById(R.id.hcrdname);

            productdesc=(TextView) itemView.findViewById(R.id.hdesc);
            date =(TextView) itemView.findViewById(R.id.hcrddate);
            realbrand=(TextView) itemView.findViewById(R.id.hcateg);

        }


    }
}
