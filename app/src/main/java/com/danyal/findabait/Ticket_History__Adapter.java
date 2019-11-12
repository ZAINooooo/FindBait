package com.danyal.findabait;

import android.content.Context;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

 class Ticket_History_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // Array List (String Variables)
    private ArrayList<Integer> id = new ArrayList<Integer>();
    private ArrayList<String> name = new ArrayList<String>();
    private ArrayList<String> logo = new ArrayList<String>();
    private ArrayList<String> status = new ArrayList<String>();
    private ArrayList<String> serviceRequestNo = new ArrayList<String>();
    private ArrayList<String> state = new ArrayList<String>();

    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_ITEM = 2;

    private Context c;

    private int cPage;
    private int tPage;

     Ticket_History_Adapter(ArrayList<Integer> id, ArrayList<String> name, ArrayList<String> logo, ArrayList<String> status, ArrayList<String> serviceRequestNo, ArrayList<String> state, Context context, int total , int current) {

        this.id = id;
        this.name = name;
        this.logo = logo;
        this.status = status;
        this.serviceRequestNo = serviceRequestNo;
        this.state = state;

        this.c = context;

        this.cPage = current;
        this.tPage = total;

        Log.d("Total_Pages22" , ""+total);
    }





    @Override
    public int getItemViewType(int position) {
        if (position == id.size()) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_ITEM) {
            //Inflating recycle view item layout
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_ticket_history_item, viewGroup, false);
            return new MyViewHolder(itemView);
        } else if (viewType == TYPE_FOOTER) {
            //Inflating footer view
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_holder_footer, viewGroup, false);
            return new FooterViewHolder(itemView);
        } else return null;

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {


        if (holder instanceof MyViewHolder) {
            MyViewHolder itemViewHolder = (MyViewHolder) holder;

//            itemViewHolder.myLineOne.setText(name.get(position));
//            itemViewHolder.myLineTwo.setText(status.get(position));

            Glide.with((c)).load(logo.get(position)).into(itemViewHolder.icon_service);
//            Log.d("IDiS222" , ""+mFlowerList.get(position).getID() +            "                 " +mFlowerList.get(position).getLogo());

            itemViewHolder.date_value22.setText(name.get(position));

            Log.d("Logoss" , name.get(position));
            Log.d("Logoss33333" , logo.get(position));

            Log.d("Logoss2" , state.get(position));

            itemViewHolder.date_value23.setText(status.get(position));
            itemViewHolder.date_value28.setText(serviceRequestNo.get(position));
            itemViewHolder.date_value25.setText(state.get(position));

            itemViewHolder.view_clicked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(c , TicketDetail.class);
                    intent.putExtra("service_id" ,id.get(position) );
                    (c).startActivity(intent);
                }
            });

            // add more components here


        } else if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerHolder = (FooterViewHolder) holder;

            try {

                cPage = cPage + 1;
//                cPage = cPage + 1;

                tPage =  ((TicketHistory) c).totalPages;
                Log.e("Current      ", String.valueOf(cPage)  +               "       total      " + tPage);

                if (cPage == tPage) {
                    footerHolder.footerBtn.setVisibility(View.GONE);
                    Toast.makeText(c, "All Data Loaded", Toast.LENGTH_LONG).show();
//                    ((TicketHistory) c).getPage(cPage);

                }
                if(cPage < tPage)
                {
                    footerHolder.footerBtn.setVisibility(View.VISIBLE);
                    ((TicketHistory) c).getPage(cPage);
                }

//                Log.e("Status","Current Page"+ cPage);
//                Log.e("Status","Total Pages"+ tPage);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public void setcPage(int cPage)
    {
        this.cPage = cPage;
    }

    @Override
    public int getItemCount() {
        return id.size() + 1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView icon_service,view_clicked;
        TextView date_value22,date_value23,date_value28,date_value25;
//    CardView mCardView;

//        TextView myLineOne;
//        TextView myLineTwo;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            myLineOne = itemView.findViewById(R.id.line);
//            myLineTwo = itemView.findViewById(R.id.pagenum);
            icon_service = itemView.findViewById(R.id.image);
            date_value22 = itemView.findViewById(R.id.date_value22);
            date_value23 = itemView.findViewById(R.id.date_value23);
            date_value28 = itemView.findViewById(R.id.date_value28);
            date_value25 = itemView.findViewById(R.id.date_value25);

            view_clicked = itemView.findViewById(R.id.view_clicked);


        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

       LinearLayout footerBtn;

        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
            footerBtn = itemView.findViewById(R.id.footer_text);


        }
    }
}

