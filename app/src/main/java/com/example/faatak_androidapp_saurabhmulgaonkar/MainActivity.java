package com.example.faatak_androidapp_saurabhmulgaonkar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.faatak_androidapp_saurabhmulgaonkar.Adapter.MyAdapter;
import com.example.faatak_androidapp_saurabhmulgaonkar.Interface.ApiInterface;
import com.example.faatak_androidapp_saurabhmulgaonkar.Pojo.Main;
import com.example.faatak_androidapp_saurabhmulgaonkar.Pojo.Notice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView rec;
    List<Notice> notice=new ArrayList<Notice>();
    //MyAdapter adp;
    RelativeLayout rel;
    ImageView back;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        dialog=new Dialog(MainActivity.this);

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(MainActivity.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {


        }
        else {
            dialog.setContentView(R.layout.nointernet);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Button btnOk=dialog.findViewById(R.id.btnOk);
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    finish();
                }
            });
            dialog.show();
        }

        back=findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rec=findViewById(R.id.rec);
        rel=findViewById(R.id.rel);

        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://faatak-env.eba-xhhhpvbq.us-east-2.elasticbeanstalk.com/society/notice/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        ApiInterface apiInterface=retrofit.create(ApiInterface.class);

        Call<Main> call=apiInterface.getNotice();
        call.enqueue(new Callback<Main>() {
            @Override
            public void onResponse(Call<Main> call, Response<Main> response) {

                if(response.body().getCode()==200) {
                    rel.setVisibility(View.GONE);


                    Log.e("MainActivity", "onResponse -> " + response.body().getData().get(0).getMessage());
                    notice = response.body().getData();
                    rec.setAdapter(new MyAdapter(MainActivity.this, notice));
                    rec.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                }

            }

            @Override
            public void onFailure(Call<Main> call, Throwable t) {

                Toast.makeText(MainActivity.this,"Some unexpected error occurred while fetching the data \nPlease reopen the app again",Toast.LENGTH_SHORT).show();
                Log.e("MainActivity","onError -> "+t);
            }
        });




    }
}