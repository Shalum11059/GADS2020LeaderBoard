package com.moise.gadsledersboards.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.moise.gadsledersboards.others.HourLeader;
import com.moise.gadsledersboards.others.SkillIqLeader;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public interface GadsService {
        @GET("api/hours")
        Call<List<HourLeader>> fetchHourLeaders();
        @GET("api/skilliq")
        Call<List<SkillIqLeader>> fetchSkillIqLeaders();
        @FormUrlEncoded
        @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
        Call<Void> submitForm(
                @Field("entry.1877115667") String firstName,
                @Field("entry.2006916086") String lastName,
                @Field("entry.1824927963") String emailAddress,
                @Field("entry.284483984") String link);
    }
}
