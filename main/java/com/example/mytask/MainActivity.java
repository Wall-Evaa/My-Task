package com.example.mytask;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        recyclerView=findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(llm);
        listingdata();

    }

    private void listingdata() {
        ApiInterface apiInteface = Retrofit.getRetrofit().create(ApiInterface.class);
        Call<Pojo> listingdata=apiInteface.getData();
        listingdata.enqueue(new Callback<Pojo>() {
            @Override
            public void onResponse(Call<Pojo> call, Response<Pojo> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Pojo.Result> results = response.body().getResult();
                    Log.d("API Response", "Results: " + results.size());

                    recycleadapter adapter = new recycleadapter(results);
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.d("API Response", "Response was not successful or body is null.");
                }
            }


            @Override
            public void onFailure(Call<Pojo> call, Throwable throwable) {
                Log.d("error", throwable.getMessage().toString());
            }
        });
    }

    class recycleadapter extends RecyclerView.Adapter<recycleadapter.MyViewHolder>{

        List<Pojo.Result>list;

        public recycleadapter(List<Pojo.Result> result) {

            this.list=list;
        }

        @NonNull
        @Override
        public recycleadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eachitem, null);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
           holder.tvTitle.setText(list.get(position).getTitle());
           holder.tvDesc.setText(list.get(position).getDescription());
            Picasso.get()
                    .load(list.get(position).getImage())
                    .placeholder(R.drawable.ic_launcher_background)
                    .fit()
                    .into(holder.img);
        }

        @Override
        public int getItemCount() {
            return list != null ? list.size() : 0;
        }
        class MyViewHolder extends RecyclerView.ViewHolder{
              TextView tvTitle, tvDesc;
              ShapeableImageView img;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                tvTitle=itemView.findViewById(R.id.headingTitle);
                tvDesc=itemView.findViewById(R.id.headingdDescription);
                img=itemView.findViewById(R.id.headingImage);
            }
        }

    }
}
