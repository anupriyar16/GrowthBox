package com.example.veggiebowl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class activity_first extends AppCompatActivity {
    EditText TextView1;
    EditText TextView2;
    EditText TextView3;
    EditText TextView4;
    EditText TextView5;
    EditText TextView6;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        TextView1=(EditText) findViewById(R.id.TextView1);

        TextView2=(EditText) findViewById(R.id.TextView2);

        TextView3=(EditText) findViewById(R.id.TextView3);

        TextView5=(EditText) findViewById(R.id.TextView4);

        TextView5=(EditText) findViewById(R.id.TextView5);

        TextView6=(EditText) findViewById(R.id.TextView6);

        button=(Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity_first.this, "Successfully Added", Toast.LENGTH_LONG).show();
                openActivitysec();



            }
        });







    }
    public void openActivitysec()
    {
        Intent intent=new Intent(this, activity_second.class);
        startActivity(intent);
    }



}
