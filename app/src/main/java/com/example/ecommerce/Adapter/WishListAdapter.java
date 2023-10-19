

package com.example.ecommerce.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.ecommerce.Domain.populerDomain;
import com.example.ecommerce.Helper.ChangeNumberItemListener;
import com.example.ecommerce.Helper.ManagmentCart;
import com.example.ecommerce.R;

import java.util.ArrayList;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ViewHolder>{
    ArrayList<populerDomain> listItemSelected ;
    private ManagmentCart managmentCart;
    ChangeNumberItemListener changeNumberItemListener;


    public WishListAdapter(ArrayList<populerDomain> listItemSelected,Context context, ChangeNumberItemListener changeNumberItemListener) {
        this.listItemSelected = listItemSelected;
        managmentCart= new ManagmentCart(context);
        this.changeNumberItemListener = changeNumberItemListener;
    }

    @NonNull
    @Override
    public WishListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_viewholder,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull WishListAdapter.ViewHolder holder, int position) {
        populerDomain item = listItemSelected.get(position);
        holder.title.setText(listItemSelected.get(position).getTitle());
        holder.scoretxt.setText(String.valueOf(listItemSelected.get(position).getReview()));
//        holder.offrate.setText(String.valueOf(listItemSelected.get(position).getOffperce()));


        holder.ratingtxt.setText(String.valueOf(listItemSelected.get(position).getRatingtx()));
        holder.feeeachitem.setText("$"+listItemSelected.get(position).getPrice());
        double originalPrice = item.getPrice();
        int discount = 5;
        double discountedPrice = originalPrice - (originalPrice * discount / 100.0);

        // Set the calculated discounted price in discountTxt
        holder.discountTxt.setText("$" + String.format("%.2f", discountedPrice)); // Format to show two decimal places

        int drawableResourceId = holder.itemView.getContext().getResources()
                .getIdentifier(listItemSelected.get(position).getPicUrl(),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId).transform(new GranularRoundedCorners(30,30,30,30))
                .into(holder.pic);
        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove the item from the list and update the adapter
                listItemSelected.remove(position);
                notifyDataSetChanged();
                // Optionally, you can also perform any other actions like updating the database.
            }
        });

    }

    @Override
    public int getItemCount() {
        return listItemSelected.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title , feeeachitem ,ratingtxt , scoretxt , discountTxt ,offrate;
        ImageView pic , deleteItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView162);
            pic = itemView.findViewById(R.id.imageView52);
            feeeachitem = itemView.findViewById(R.id.textView192);
            deleteItem = itemView.findViewById(R.id.deleteWl);


            ratingtxt = itemView.findViewById(R.id.ratingTextwl);
            scoretxt = itemView.findViewById(R.id.scoretextwl);
            discountTxt=itemView.findViewById(R.id.striketextprice);
            offrate = itemView.findViewById(R.id.discountTag1);
        }

    }
}
