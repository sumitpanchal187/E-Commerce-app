package com.example.ecommerce.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.ecommerce.Domain.populerDomain;

import java.util.ArrayList;

public class ManagmentCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagmentCart(Context context)
    {
        this.context=context;
        this.tinyDB=new TinyDB(context);
    }

    public void insertFood(populerDomain item)
    {
        ArrayList<populerDomain> listpop = getListCart();
        boolean existalready = false;
        int n = 0;
        for (int i = 0; i < listpop.size(); i++) {
            if (listpop.get(i).getTitle().equals(item.getTitle()))
            {
                existalready=true;
                n=i;
                break;
            }
        }
        if (existalready)
        {
            listpop.get(n).setNumberincart(item.getNumberInCart());
        }
        else {
            listpop.add(item);
        }
        tinyDB.putListObject("CartList",listpop);
        Toast.makeText(context,"Added to your cart",Toast.LENGTH_SHORT).show();

    }

    public ArrayList<populerDomain> getListCart() {
        return tinyDB.getListObject("CartList");
    }
    public  void minusNumberItem(ArrayList<populerDomain>lisItem,int position,ChangeNumberItemListener changeNumberItemListener)
    {
        if (lisItem.get(position).getNumberInCart()==1)
        {
            lisItem.remove(position);
        }
        else {
            lisItem.get(position).setNumberincart(lisItem.get(position).getNumberInCart()-1);
        }
        tinyDB.putListObject("CartList",lisItem);
        changeNumberItemListener.change();
    }
    public  void plusNumberItem(ArrayList<populerDomain>lisItem,int position,ChangeNumberItemListener changeNumberItemListener)
    {
        lisItem.get(position).setNumberincart(lisItem.get(position).getNumberInCart()+1);
        tinyDB.putListObject("CartList",lisItem);
        changeNumberItemListener.change();
    }

    public Double getTotalFee()
    {
        ArrayList<populerDomain> listitem = getListCart();
        double fee = 0 ;
        for (int i = 0; i < listitem.size(); i++) {
            fee = fee+(listitem.get(i).getPrice()*listitem.get(i).getNumberInCart());
        }
        return fee;
    }
}
