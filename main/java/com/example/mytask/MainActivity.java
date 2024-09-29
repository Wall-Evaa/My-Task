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
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);


        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Fetch data
        listingdata();
    }

    private void listingdata() {
        ApiInterface apiInterface = Retrofit.getRetrofit().create(ApiInterface.class);
        Call<Pojo> call = apiInterface.getData();

        call.enqueue(new Callback<Pojo>() {
            @Override
            public void onResponse(Call<Pojo> call, Response<Pojo> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Pojo.Result> results = response.body().getResult();
                    Log.d("API Response", "Results: " + results.size());

                    if (results != null && !results.isEmpty()) {
                        RecycleAdapter adapter = new RecycleAdapter(MainActivity.this, results);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Log.d("API Response", "Results are empty or null.");
                    }
                } else {
                    Log.d("API Response", "Response was not successful or body is null.");
                }
            }

            @Override
            public void onFailure(Call<Pojo> call, Throwable throwable) {
                Log.e("Error", "Failed to fetch data: " + throwable.getMessage());
            }
        });
    }

    private static class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
        private final Context context;
        private final List<Pojo.Result> list;

        public RecycleAdapter(Context context, List<Pojo.Result> result) {
            this.context = context;
            this.list = result;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eachitem, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Pojo.Result item = list.get(position);
            Log.d("Adapter", "Binding item at position: " + position + ", Title: " + item.getTitle());

            holder.tvTitle.setText(item.getTitle());
            holder.tvDesc.setText(item.getDescription());

            Glide.with(context)
                    .load(item.getImage())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(holder.img);

            holder.linearLayout.setOnClickListener(v -> {
                Intent intent = new Intent(context, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("promocode", item.getPromoCode());
                bundle.putString("startdate", item.getValidityStart());
                bundle.putString("enddate", item.getValidityEnd());
                bundle.putString("category", item.getCategName());
                bundle.putString("description", item.getDescription());
                bundle.putString("image", item.getImage());
                bundle.putString("title", item.getTitle());
                bundle.putString("storename", item.getStoreName());

                intent.putExtras(bundle);
                context.startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return list != null ? list.size() : 0;
        }

        static class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tvTitle, tvDesc;
            ShapeableImageView img;
            LinearLayout linearLayout;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.headingTitle);
                tvDesc = itemView.findViewById(R.id.headingDescription);
                img = itemView.findViewById(R.id.headingImage);
                linearLayout = itemView.findViewById(R.id.linearLayout);
            }
        }
    }
}
