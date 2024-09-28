package com.example.mytask;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

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

                    if (results != null && !results.isEmpty()) {
                        recycleadapter adapter = new recycleadapter(MainActivity.this,results);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged(); // Notify the adapter of data changes
                    } else {
                        Log.d("API Response", "Results are empty or null.");
                    }
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
        private final Context context;
        List<Pojo.Result>list;

        public recycleadapter(Context context,List<Pojo.Result> result) {
            this.context=context;
            this.list=result;
        }

        @NonNull
        @Override
        public recycleadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eachitem, parent,false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Pojo.Result item = list.get(position);
            Log.d("Adapter", "Binding item at position: " + position + ", Title: " + item.getTitle());

            holder.tvTitle.setText(item.getTitle());
            holder.tvDesc.setText(item.getDescription());

            Glide.with(MainActivity.this)
                    .load(item.getImage())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(holder.img);

            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("promocode", item.getPromoCode());
                    bundle.putString("startdate", item.getValidityStart());
                    bundle.putString("enddate", item.getValidityEnd());
                    bundle.putString("category", item.getCategName());
                    bundle.putString("description", item.getDescription());
//                    bundle.putString("storeName", item.getStoreName());
                    bundle.putString("image", item.getImage());


                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }


        @Override
        public int getItemCount() {
            return list != null ? list.size() : 0;
        }
        class MyViewHolder extends RecyclerView.ViewHolder{
              TextView tvTitle, tvDesc;
              ShapeableImageView img;
              LinearLayout linearLayout;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                tvTitle=itemView.findViewById(R.id.headingTitle);
                tvDesc=itemView.findViewById(R.id.headingDescription);
                img=itemView.findViewById(R.id.headingImage);
                linearLayout=itemView.findViewById(R.id.linearLayout);
            }
        }

    }
}
