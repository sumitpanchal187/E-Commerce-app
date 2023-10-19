package com.example.ecommerce.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.ecommerce.Activity.ProductDetailActivity;
import com.example.ecommerce.Domain.populerDomain;
import com.example.ecommerce.R;

import java.util.ArrayList;

public class populerListAdapter extends RecyclerView.Adapter<populerListAdapter.Viewholder> {

    ArrayList<populerDomain> items ;
    Context context ;

    public populerListAdapter(ArrayList<populerDomain> items) {

        this.items = items;
    }

    @NonNull
    @Override
    public populerListAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.populer_viewholder_list,parent,false);
        context = parent.getContext();

        return new Viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull populerListAdapter.Viewholder holder, int position) {
    holder.titleText.setText(items.get(position).getTitle());
    holder.feetext.setText("₹" + items.get(position).getPrice());
    holder.ReviewText.setText(" "+items.get(position).getRatingtx());
//    holder.pic.setImageResource(items.get(position).getPicUrl());

    int drawableResourceId = holder.itemView.getResources().getIdentifier(items.get(position).getPicUrl(),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .transform(new GranularRoundedCorners(30,30,0,0))
                .into(holder.pic);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), ProductDetailActivity.class);
            intent.putExtra("object",items.get(position));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class  Viewholder extends  RecyclerView.ViewHolder {
        TextView titleText, feetext , ReviewText ;
        ImageView pic ;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.populertitletext);
            feetext = itemView.findViewById(R.id.feetext);
            ReviewText = itemView.findViewById(R.id.scoretext);
            pic = itemView.findViewById(R.id.gridviewmainimage);

        }
    }
}
