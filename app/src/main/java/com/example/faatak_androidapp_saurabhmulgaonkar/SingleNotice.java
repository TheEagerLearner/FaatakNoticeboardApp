package com.example.faatak_androidapp_saurabhmulgaonkar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faatak_androidapp_saurabhmulgaonkar.Interface.ApiInterface;
import com.example.faatak_androidapp_saurabhmulgaonkar.Pojo.Main;
import com.example.faatak_androidapp_saurabhmulgaonkar.Pojo.Single;
import com.example.faatak_androidapp_saurabhmulgaonkar.Pojo.SingleNot;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SingleNotice extends AppCompatActivity {

    TextView txtTitle,txtMessage,txtDateTime,txtName;
    RelativeLayout rel;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_notice);

        getSupportActionBar().hide();

        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rel=findViewById(R.id.rel);
        txtTitle=findViewById(R.id.txtTitle);
        txtDateTime=findViewById(R.id.txtDateTime);
        txtMessage=findViewById(R.id.txtMessage);
        txtName=findViewById(R.id.txtName);

        int id= getIntent().getIntExtra("id",0);


        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://faatak-env.eba-xhhhpvbq.us-east-2.elasticbeanstalk.com/society/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        ApiInterface apiInterface=retrofit.create(ApiInterface.class);

        Call<SingleNot> call=apiInterface.getSingleNotice(id);

        call.enqueue(new Callback<SingleNot>() {
            @Override
            public void onResponse(Call<SingleNot> call, Response<SingleNot> response) {
                if(response.body().getCode()==200) {
                    rel.setVisibility(View.GONE);
                    String title = response.body().getData().getTitle();
                    txtTitle.setText(title.substring(0, 1).toUpperCase() + title.substring(1, title.length()).toLowerCase());

                    txtMessage.setText(response.body().getData().getMessage());
                    txtName.setText(response.body().getData().getSenderName());
                    Date date = new Date(response.body().getData().getCreatedDate());
                    int Hours = 0;
                    String ap;
                    String dateTime;
                    if (date.getHours() <= 12 && date.getHours() >= 1) {
                        Hours = date.getHours();
                        if (Hours == 12) {
                            ap = "PM";
                        }
                        ap = "AM";
                    } else if (date.getHours() == 0) {
                        Hours = 12;
                        ap = "AM";
                    } else {
                        Hours = date.getHours() - 12;
                        ap = "PM";
                    }

                    if (date.getMinutes() < 10) {
                        String min = "0" + date.getMinutes();
                        dateTime = date.getDay() + "/" + date.getMonth() + "/" +(date.getYear()+1900) + " " + Hours + ":" + min + " " + ap;

                    } else {
                        dateTime = date.getDay() + "/" + date.getMonth() + "/" + (date.getYear()+1900) + " " + Hours + ":" + date.getMinutes() + " " + ap;

                    }

                    txtDateTime.setText(dateTime);
                    Log.e("SingleNotice", "onResponse -> " + response.body());
                }

            }

            @Override
            public void onFailure(Call<SingleNot> call, Throwable t) {
                Toast.makeText(SingleNotice.this,"Some unexpected error occurred while fetching the data \nPlease reopen the app again",Toast.LENGTH_SHORT).show();

            }
        });


    }
}