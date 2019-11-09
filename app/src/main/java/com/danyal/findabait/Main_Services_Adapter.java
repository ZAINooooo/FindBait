package com.danyal.findabait;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Main_Services_Adapter extends RecyclerView.Adapter<FlowerViewHolder> {

    private Context mContext;
    private List<ServicesCategories_Pojo> mFlowerList;
    int building_id;

    Main_Services_Adapter(Context mContext, List<ServicesCategories_Pojo> mFlowerList , int building_id) {
        this.mContext = mContext;
        this.mFlowerList = mFlowerList;
        this.building_id = building_id;

    }

    @Override
    public FlowerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_services_item, parent, false);
        return new FlowerViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final FlowerViewHolder holder, final int position) {

        holder.text_my.setText(mFlowerList.get(position).getName());
        Log.d("IDiS" , ""+mFlowerList.get(position).getID());

        Glide.with(mContext).load(mFlowerList.get(position).getImage()).into(holder.icon_service);

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext , Submit_Request.class);

                intent.putExtra("service_id" ,mFlowerList.get(position).getID() );
                intent.putExtra("service_name" ,mFlowerList.get(position).getName() );
                intent.putExtra("service_image" ,mFlowerList.get(position).getImage() );
                intent.putExtra("building_id" , building_id );


                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFlowerList.size();
    }


    public void setDataList(ArrayList<ServicesCategories_Pojo> contactList) {

        this.mFlowerList = contactList;
    }
}

class FlowerViewHolder extends RecyclerView.ViewHolder {

    ImageView icon_service;
    TextView text_my;
    CardView mCardView;

    FlowerViewHolder(View itemView) {
        super(itemView);

        icon_service = itemView.findViewById(R.id.icon_service);
        text_my = itemView.findViewById(R.id.text_my);
        mCardView = itemView.findViewById(R.id.cardclicked);


    }
}
