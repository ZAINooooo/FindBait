package com.danyal.findabait;

import android.annotation.SuppressLint;
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

 class Ticket_History_Adapter extends RecyclerView.Adapter<FlowerViewHolder2> {

    private Context mContext;
    private List<TicketHistoryPojo> mFlowerList;
    int building_id;

    Ticket_History_Adapter(Context mContext, List<TicketHistoryPojo> mFlowerList ) {
        this.mContext = mContext;
        this.mFlowerList = mFlowerList;
    }

    @Override
    public FlowerViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_ticket_history_item, parent, false);
        return new FlowerViewHolder2(mView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final FlowerViewHolder2 holder, final int position) {

        Glide.with(mContext).load(mFlowerList.get(position).getLogo()).into(holder.icon_service);
        Log.d("IDiS222" , ""+mFlowerList.get(position).getID() +            "                 " +mFlowerList.get(position).getLogo());

        holder.date_value22.setText(mFlowerList.get(position).getName());
        holder.date_value23.setText(mFlowerList.get(position).getStatus());
        holder.date_value28.setText(""+mFlowerList.get(position).getServiceRequestNo());
        holder.date_value25.setText(mFlowerList.get(position).getState());

        holder.view_clicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext , TicketDetail.class);

                intent.putExtra("service_id" ,mFlowerList.get(position).getID() );
//                intent.putExtra("service_name" ,mFlowerList.get(position).getName() );
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFlowerList.size();
    }


    public void setDataList(ArrayList<TicketHistoryPojo> contactList) {

        this.mFlowerList = contactList;
    }
}

class FlowerViewHolder2 extends RecyclerView.ViewHolder {

    ImageView icon_service,view_clicked;
    TextView date_value22,date_value23,date_value28,date_value25;
//    CardView mCardView;

    FlowerViewHolder2(View itemView) {
        super(itemView);

        icon_service = itemView.findViewById(R.id.image);
        date_value22 = itemView.findViewById(R.id.date_value22);
        date_value23 = itemView.findViewById(R.id.date_value23);
        date_value28 = itemView.findViewById(R.id.date_value28);
        date_value25 = itemView.findViewById(R.id.date_value25);

        view_clicked = itemView.findViewById(R.id.view_clicked);




    }
}
