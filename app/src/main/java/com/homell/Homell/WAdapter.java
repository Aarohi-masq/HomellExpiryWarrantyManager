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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.homell.Homell.Expire;
import com.homell.Homell.R;
import com.homell.Homell.editproduct;
import com.homell.Homell.expirylist;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WAdapter extends RecyclerView.Adapter<WAdapter.MyViewHolder> {
    Context context;
    ArrayList<Warranty> warranties;
    ImageView wstamp;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Warranty Items").child(FirebaseAuth.getInstance().getCurrentUser().getUid());




    public WAdapter(Context c, ArrayList<Warranty> e){
        context=c;
        warranties=e;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.wcardview,parent,false));


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {



        holder.editTextName.setText(warranties.get(position).getEditTextName());

        holder.productdesc.setText(warranties.get(position).getProductdesc());
        holder.realbrand.setText(warranties.get(position).getRealbrand());
        holder.date.setText(warranties.get(position).getDate());







        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Weditproduct.class);
                intent.putExtra("productId",warranties.get(position).getProductId());
                intent.putExtra("date", warranties.get(position).getDate());
                intent.putExtra("editTextName", warranties.get(position).getEditTextName());
                intent.putExtra("productdesc", warranties.get(position).getProductdesc());

                intent.putExtra("realbrand", warranties.get(position).getRealbrand());
                v.getContext().startActivity(intent);
            }
        });

        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                String name = warranties.get(position).getEditTextName();
                showDeleteDialog(name,position);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Wshowdata.class);
                intent.putExtra("productId",warranties.get(position).getProductId());
                intent.putExtra("date", warranties.get(position).getDate());
                intent.putExtra("editTextName", warranties.get(position).getEditTextName());
                intent.putExtra("productdesc", warranties.get(position).getProductdesc());
                //  intent.putExtra("productmonth", expires.get(position).getproductmonth());
                intent.putExtra("realbrand", warranties.get(position).getRealbrand());
                intent.putExtra("imgpath", warranties.get(position).getImgpath());
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
                DatabaseReference reference =databaseReference.child(warranties.get(position).getProductId());
                reference.getRef().removeValue();
                Toast.makeText(context,"Item Deleted Successfully!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, Warrantylist.class);
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
        return warranties.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView editTextName, productmonth, date, productdesc, realbrand;
        ImageView edit, del;


        public MyViewHolder(View itemView) {
            super(itemView);
            editTextName = (TextView) itemView.findViewById(R.id.wpronamelist);
            productdesc = (TextView) itemView.findViewById(R.id.wcrddesc);
            wstamp= (ImageView) itemView.findViewById(R.id.wstamp);
            date = (TextView) itemView.findViewById(R.id.wcrdmfg);
            realbrand = (TextView) itemView.findViewById(R.id.wcrdcategory);

            edit = (ImageButton) itemView.findViewById(R.id.weditbtn);
            del = (ImageButton) itemView.findViewById(R.id.wdelbtn);

            edit.setClickable(true);
        }



    }

}



