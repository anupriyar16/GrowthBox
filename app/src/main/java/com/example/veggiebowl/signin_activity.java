package com.example.veggiebowl;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class signin_activity extends AppCompatActivity {
private EditText user,password;
public static int date2,month2,year2;
private TextView tv;
private Button button;
FirebaseDatabase database;
DatabaseReference users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_activity);
        tv=(TextView) findViewById(R.id.textView5);
        button=(Button) findViewById(R.id.button);
        user=(EditText) findViewById(R.id.editText3);
        password=(EditText) findViewById(R.id.editText5);
        database=FirebaseDatabase.getInstance() ;
        users=database.getReference().child("Users");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              openActivity2();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    public void openActivity2()
    {
        Intent intent=new Intent(this, sign_up.class);
        startActivity(intent);
    }

    private void login()
    {
        final String username=user.getText().toString();
        final String passw=password.getText().toString();
        Query query=users.orderByChild("name").equalTo(username);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    for (DataSnapshot user : dataSnapshot.getChildren()) {

                        User usersBean= user.getValue(User.class);

                        if(usersBean.password.equals(passw))
                        {
                            String date1=usersBean.getDate();
                             date2=Integer.parseInt(date1);
                            String month1=usersBean.getMonth();
                            month2=Integer.parseInt(month1);
                            String year1=usersBean.getYear();
                            year2=Integer.parseInt(year1);

                            //Intent intent=new Intent(signin_activity.this,activity_first.class);
                            //startActivity(intent);
                            Toast.makeText(signin_activity.this, "Login Successfull ", Toast.LENGTH_LONG).show();
                            openActivity3();
                        } else {
                            Toast.makeText(signin_activity.this, "Incorrect Password ", Toast.LENGTH_LONG).show();

                        }
                    }

                } else {
                    Toast.makeText(signin_activity.this, "User Not Found, SignUp ", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void openActivity3()
    {
        Intent intent=new Intent(this, activity_first.class);
        startActivity(intent);
    }
}
