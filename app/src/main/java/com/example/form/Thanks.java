package com.example.form;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class Thanks extends AppCompatActivity {
    String name;
    TextView nameView;
    TextView genderView;
    TextView applyView;
    TextView peView;
    TextView cityView;
    Button share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanks);
        name = getIntent().getStringExtra("name");
        nameView = (TextView) findViewById(R.id.name);
        genderView =(TextView) findViewById(R.id.gender);
        applyView = (TextView) findViewById(R.id.apply);
        peView = (TextView) findViewById(R.id.pe);
        cityView = (TextView) findViewById(R.id.city);
        share =(Button) findViewById(R.id.share);
        nameView.setText(name);
        genderView.setText(getIntent().getStringExtra("gender"));
        applyView.setText(getIntent().getStringExtra("program"));
        peView.setText(getIntent().getStringExtra("pe"));
        cityView.setText(getIntent().getStringExtra("city"));
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                String shareBody = "Your Form Details\nName: "+name+"\n"+getIntent().getStringExtra("gender")+
                        "\n"+getIntent().getStringExtra("program")+"\n"+getIntent().getStringExtra("pe")+
                        "\n"+getIntent().getStringExtra("city");
                intent.setType("text/plain");
                /*Applying information Subject and Body.*/
                intent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(intent, "Share Via"));
            }
        });

    }
}
