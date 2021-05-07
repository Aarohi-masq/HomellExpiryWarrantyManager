package com.homell.Homell;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {


    Context context;

    ArrayList<Expire> expires;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Expiry Items").child(FirebaseAuth.getInstance().getCurrentUser().getUid());




    public Adapter(Context c, ArrayList<Expire> e){
        context=c;
        expires=e;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent,false));


    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.editTextName.setText(expires.get(position).getEditTextName());

        holder.productdesc.setText(expires.get(position).getProductdesc());
        holder.realbrand.setText(expires.get(position).getRealbrand());
        holder.date.setText(expires.get(position).getDate());


        //image set
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), editproduct.class);
                intent.putExtra("productId",expires.get(position).getProductId());
                intent.putExtra("date", expires.get(position).getDate());
                intent.putExtra("editTextName", expires.get(position).getEditTextName());
                intent.putExtra("productdesc", expires.get(position).getProductdesc());

                intent.putExtra("realbrand", expires.get(position).getRealbrand());
                v.getContext().startActivity(intent);
            }
        });

        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {


                String name = expires.get(position).getEditTextName();
                showDeleteDialog(name,position);

            }
        });
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
    private void showDeleteDialog(String name, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete");
        builder.setMessage("Are you sure to delete "+name+"?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseReference reference =databaseReference.child(expires.get(position).getProductId());
                reference.getRef().removeValue();

                Toast.makeText(context,"Item Deleted Successfully!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, expirylist.class);
                context.startActivity(intent);

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();

    }


    @Override
    public int getItemCount() {
        return expires.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView editTextName, productmonth, date, productdesc, realbrand;
        ImageView edit, del,wstamp;



        public MyViewHolder(View itemView){
            super(itemView);
            editTextName=(TextView) itemView.findViewById(R.id.pronamelist);

            productdesc=(TextView) itemView.findViewById(R.id.crddesc);
            date =(TextView) itemView.findViewById(R.id.crdmfg);
            realbrand=(TextView) itemView.findViewById(R.id.crdcategory);

            Calendar c = Calendar.getInstance();
            String currentDate = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());
           if (currentDate == String.valueOf(date)) {
               wstamp.setVisibility(View.VISIBLE);

           }
            wstamp=(ImageView) itemView.findViewById(R.id.wstamp);
            edit=(ImageButton) itemView.findViewById(R.id.editbtn);
            del=(ImageButton) itemView.findViewById(R.id.delbtn);
            edit.setClickable(true);
        }

    }

}
