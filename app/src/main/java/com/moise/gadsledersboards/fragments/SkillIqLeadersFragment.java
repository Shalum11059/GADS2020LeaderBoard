package com.moise.gadsledersboards.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moise.gadsledersboards.adapters.SkillIqAdapter;
import com.moise.gadsledersboards.others.SkillIqLeader;
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

public class SkillIqLeadersFragment extends Fragment {

    private RecyclerView mRecyclerView;
    public SkillIqLeadersFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_skill_iq_leaders, container, false);
        mRecyclerView = view.findViewById(R.id.skill_iq_recycler_view);
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




        Call<List<SkillIqLeader>> hourLeaderAsync = gadsService.fetchSkillIqLeaders();
        hourLeaderAsync.enqueue(new Callback<List<SkillIqLeader>>(){
            @Override
            public void onResponse(Call<List<SkillIqLeader>> call, Response<List<SkillIqLeader>> response) {
                Log.i("HOUR LEADERS: SIZE", "response: " + response.body().size());
                mRecyclerView.setAdapter(new SkillIqAdapter(response.body()));

            }

            @Override
            public void onFailure(Call<List<SkillIqLeader>> call, Throwable throwable) {
                System.out.println(throwable);
            }
        });

    }
}