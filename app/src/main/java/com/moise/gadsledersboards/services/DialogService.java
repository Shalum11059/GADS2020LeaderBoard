package com.moise.gadsledersboards.services;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.moise.gadsledersboards.R;
import com.moise.gadsledersboards.others.Submission;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DialogService {

    Activity mActivity;
    public DialogService(Activity activity){
        mActivity = activity;
    }

    public void showAreYouSureDialog(Submission submission){
        Dialog dialog = new Dialog(mActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.are_you_sure);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView closeDialog = dialog.findViewById(R.id.imageView);
        closeDialog.setOnClickListener(v -> dialog.dismiss());

        Button buttonOk = dialog.findViewById(R.id.button);
        buttonOk.setOnClickListener(v -> {
            dialog.dismiss();
            if(submission.validate()){
                submitData(submission);
            }else{
                showErrorDialog();
            }
        });

        dialog.show();
    }
    public void showSuccessDialog(){
        Dialog dialog = new Dialog(mActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.success_submission_icon);
        dialog.show();
    }
    public void showErrorDialog(){
        Dialog dialog = new Dialog(mActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.error_submission_icon);
        dialog.show();
    }
    public void submitData(Submission submission){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/e/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        MyService.GadsService gadsService = retrofit.create(MyService.GadsService.class);
        gadsService.submitForm(submission.firstName, submission.lastName, submission.email, submission.githubLink)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        showSuccessDialog();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        showErrorDialog();
                    }
                });

    }
}
