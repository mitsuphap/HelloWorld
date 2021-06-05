package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    int result = 0;
    TextView tvResult;
    Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        result = intent.getIntExtra("result", 0);

        Bundle bundle = intent.getBundleExtra("cBundle");
        int x = bundle.getInt("x");
        int y = bundle.getInt("y");
        int z = bundle.getInt("z");

        CooridinateSerializable c2 = (CooridinateSerializable)
                intent.getSerializableExtra("cSerializable");

        CooridinateParcelable c3 = intent.getParcelableExtra("cParcelable");

        initInstances();
    }

    private void initInstances() {
        tvResult = (TextView) findViewById(R.id.tvResult);
        tvResult.setText("Result = " + result);

        btnOk = (Button) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}