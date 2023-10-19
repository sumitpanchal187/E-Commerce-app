

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

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder>{
    ArrayList<populerDomain> listItemSelected ;
    private ManagmentCart managmentCart;
    ChangeNumberItemListener changeNumberItemListener;


    public CartListAdapter(ArrayList<populerDomain> listItemSelected,Context context, ChangeNumberItemListener changeNumberItemListener) {
        this.listItemSelected = listItemSelected;
        managmentCart= new ManagmentCart(context);
        this.changeNumberItemListener = changeNumberItemListener;
    }

    @NonNull
    @Override
    public CartListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListAdapter.ViewHolder holder, int position) {
        holder.title.setText(listItemSelected.get(position).getTitle());
        holder.feeeachitem.setText("$"+listItemSelected.get(position).getPrice());
        holder.totalEachItem.setText("$"+Math.round((listItemSelected.get(position).getNumberInCart()*listItemSelected.get(position).getPrice())));
        holder.num.setText(String.valueOf(listItemSelected.get(position).getNumberInCart()));

        int drawableResourceId = holder.itemView.getContext().getResources()
                .getIdentifier(listItemSelected.get(position).getPicUrl(),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId).transform(new GranularRoundedCorners(30,30,30,30))
                .into(holder.pic);

        holder.plusitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyDataSetChanged();
                changeNumberItemListener.change();
            }
        });

        holder.minusitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyDataSetChanged();
                changeNumberItemListener.change();
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
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
        TextView title , feeeachitem , plusitem ,minusitem ;
        ImageView pic ,delete;
        TextView totalEachItem , num;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView16);
            pic = itemView.findViewById(R.id.imageView5);
            feeeachitem = itemView.findViewById(R.id.textView19);
            totalEachItem = itemView.findViewById(R.id.textView18);
            plusitem = itemView.findViewById(R.id.textView27);
            minusitem= itemView.findViewById(R.id.minusitem2);
            num = itemView.findViewById(R.id.textView28);
            delete = itemView.findViewById(R.id.deleteItem);
        }

    }
}
