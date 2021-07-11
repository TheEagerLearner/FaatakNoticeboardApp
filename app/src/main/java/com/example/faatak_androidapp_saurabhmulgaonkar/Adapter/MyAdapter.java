package com.example.faatak_androidapp_saurabhmulgaonkar.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faatak_androidapp_saurabhmulgaonkar.Pojo.Notice;
import com.example.faatak_androidapp_saurabhmulgaonkar.R;
import com.example.faatak_androidapp_saurabhmulgaonkar.SingleNotice;

import java.util.Date;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyView> {
    Context context;
    List<Notice> notice;
    public MyAdapter(Context ctx, List<Notice> not){
        context=ctx;
        notice=not;

    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =LayoutInflater.from(context).inflate(R.layout.row,parent,false);

        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        if(notice.get(position).getTitle().length()>15){
            holder.txtTitle.setTextSize(16);
        }

        Date date=new Date(notice.get(position).getCreatedDate());
        String title=notice.get(position).getTitle();
        holder.txtTitle.setText(title.substring(0,1).toUpperCase()+title.substring(1,title.length()).toLowerCase());
        holder.txtEvent.setText(notice.get(position).getEventType());
        holder.txtDate.setText(""+date.getDate()+"/"+date.getMonth()+"/"+(date.getYear()+1900));
        //Log.e("MainActitvity","date->"+date.toString());
        holder.txtSender.setText(notice.get(position).getSenderName());
        holder.txtSenderType.setText(notice.get(position).getSenderType());
        try{
            holder.txtMessage.setText(notice.get(position).getMessage().substring(0,90)+"...");
        }
        catch (IndexOutOfBoundsException e)
        {
            holder.txtMessage.setText(notice.get(position).getMessage());
        }

        holder.NoticeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent =new Intent(context, SingleNotice.class);
                intent.putExtra("id",notice.get(position).getNoticeId());
                context.startActivity(intent);
                //((Activity)context).finish();
            }
        });


    }

    @Override
    public int getItemCount() {
        return notice.size();
    }

    public class MyView extends RecyclerView.ViewHolder {
        TextView txtTitle,txtSender,txtSenderType,txtEvent,txtDate,txtMessage;
        RelativeLayout NoticeCard;
        public MyView(@NonNull View itemView) {
            super(itemView);
            txtTitle=itemView.findViewById(R.id.txtTitle);
            txtDate=itemView.findViewById(R.id.txtDate);
            txtEvent=itemView.findViewById(R.id.txtEventType);
            txtSender=itemView.findViewById(R.id.txtSender);
            txtSenderType=itemView.findViewById(R.id.txtsenderType);
            txtMessage=itemView.findViewById(R.id.txtMessage);
            NoticeCard=itemView.findViewById(R.id.NoticeCard);

        }
    }
}
