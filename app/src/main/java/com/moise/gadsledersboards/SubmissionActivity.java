package com.moise.gadsledersboards;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.moise.gadsledersboards.services.DialogService;
import com.moise.gadsledersboards.others.Submission;

public class SubmissionActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButton;
    private EditText mFirstName, mLastName, mEmailAddress, mGitHubLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mButton = findViewById(R.id.submit_btn2);
        mButton.setOnClickListener(this);

        mFirstName = findViewById(R.id.first_name);
        mLastName = findViewById(R.id.last_name);
        mEmailAddress = findViewById(R.id.email);
        mGitHubLink = findViewById(R.id.github_link);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void onClick(View view) {
        DialogService dialogService = new DialogService(this);
        Submission sub = new Submission(
                mFirstName.getText().toString(),
                mLastName.getText().toString(),
                mEmailAddress.getText().toString(),
                mGitHubLink.getText().toString()
        );
        dialogService.showAreYouSureDialog(sub);
    }
}