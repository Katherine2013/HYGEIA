package com.example.hygeia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hygeia.ui.home.DetailActivity;
import com.example.hygeia.ui.home.HomeFragment;
import com.example.hygeia.ui.maproute.WalkRouteActivity;

public class PublishTask extends AppCompatActivity {

    private Button mForwardButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_task);

        mForwardButton = findViewById(R.id.button_forward);
        mForwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PublishTask.this," published successfully！",Toast.LENGTH_SHORT).show();
            }
        });
    }
}