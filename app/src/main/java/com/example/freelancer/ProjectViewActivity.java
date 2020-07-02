package com.example.freelancer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class ProjectViewActivity extends AppCompatActivity {
    TextView tvProgressLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_view);

        // set a change listener for SeekBar
        SeekBar seekBar = findViewById(R.id.progress_seek_bar);
        //seekBar.OnSeekBarChangeListener(seekBarChangeListener);
        int progress = seekBar.getProgress();
        tvProgressLabel = findViewById(R.id.progress_text);
        tvProgressLabel.setText("Progress: "+ progress);
    }

    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as user slided the thumb
            tvProgressLabel.setText("Progress: "+ progress);

            // code below to call API to update progress in backend
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // called when user first touches seek bar
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // called after user finishes moving seek bar
        }
    };


    public void openProjectView2(View view) {
        // Go to ProjectView2Activity
        Intent openProjectView2 = new Intent(this, ProjectView2Activity.class);
        startActivity(openProjectView2);
    }
}
