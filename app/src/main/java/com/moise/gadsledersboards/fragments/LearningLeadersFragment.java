package com.moise.gadsledersboards.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moise.gadsledersboards.adapters.HoursAdapter;
import com.moise.gadsledersboards.others.HourLeader;
import com.moise.gadsledersboards.services.MyService;
import com.moise.gadsledersboards.R;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LearningLeadersFragment extends Fragment {

    private RecyclerView mRecyclerView;
    public LearningLeadersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_learning_leaders, container, false);
        mRecyclerView = view.findViewById(R.id.learning_leaders_recycler_view);
        mRecyclerView.setLayoutManager( new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        fetchData();

        return view;
    }
    public void fetchData(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gadsapi.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        MyService.GadsService gadsService = retrofit.create(MyService.GadsService.class);




        Call<List<HourLeader>> hourLeaderAsync = gadsService.fetchHourLeaders();
        hourLeaderAsync.enqueue(new Callback<List<HourLeader>>(){
            @Override
            public void onResponse(Call<List<HourLeader>> call, Response<List<HourLeader>> response) {
                Log.i("HOUR LEADERS: SIZE", "response: " + response.body().size());
                mRecyclerView.setAdapter(new HoursAdapter(response.body()));
            }

            @Override
            public void onFailure(Call<List<HourLeader>> call, Throwable throwable) {
                System.out.println(throwable);
            }
        });

    }
}