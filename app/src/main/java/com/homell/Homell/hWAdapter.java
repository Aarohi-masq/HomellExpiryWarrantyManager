package com.homell.Homell;


import android.content.Context;

import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
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

public class hWAdapter extends RecyclerView.Adapter<hWAdapter.MyViewHolder> {
    Context context;
    ArrayList<Warranty> warranties;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Warranty Items").child(FirebaseAuth.getInstance().getCurrentUser().getUid());




    public hWAdapter(Context c, ArrayList<Warranty> e){
        context=c;
        warranties=e;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.homecard,parent,false));



    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.editTextName.setText(warranties.get(position).getEditTextName());


        holder.productdesc.setText(warranties.get(position).getProductdesc());
        holder.realbrand.setText(warranties.get(position).getRealbrand());
        holder.date.setText(warranties.get(position).getDate());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Wshowdata.class);
                intent.putExtra("productId",warranties.get(position).getProductId());
                intent.putExtra("date", warranties.get(position).getDate());
                intent.putExtra("editTextName", warranties.get(position).getEditTextName());
                intent.putExtra("productdesc", warranties.get(position).getProductdesc());
                intent.putExtra("realbrand", warranties.get(position).getRealbrand());
                intent.putExtra("imgpath", warranties.get(position).getImgpath());
                v.getContext().startActivity(intent);

            }
        });


    }


    @Override
    public int getItemCount() {
        return warranties.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView editTextName, date, productdesc, realbrand;
        ImageView edit, del;

        public MyViewHolder(View itemView){
            super(itemView);
            editTextName=(TextView) itemView.findViewById(R.id.hcrdname);
            editTextName.setMovementMethod(new ScrollingMovementMethod());


            productdesc=(TextView) itemView.findViewById(R.id.hdesc);
            date =(TextView) itemView.findViewById(R.id.hcrddate);
            realbrand=(TextView) itemView.findViewById(R.id.hcateg);



        }

    }


}




