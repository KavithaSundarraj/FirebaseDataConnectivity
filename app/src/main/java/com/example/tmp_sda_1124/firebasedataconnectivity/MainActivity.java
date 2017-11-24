package com.example.tmp_sda_1124.firebasedataconnectivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText userEditText;
    private EditText passwordEditText;
    private Button newUserButton;
    private Button loginButton;

    DatabaseReference databaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        databaseUser= FirebaseDatabase.getInstance().getReference("users");

        //Get UI elements
        userEditText = (EditText) findViewById(R.id.user);
        passwordEditText=(EditText) findViewById(R.id.password);
        newUserButton=(Button) findViewById(R.id.newUser);
        loginButton=(Button) findViewById(R.id.login);


        newUserButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                addUser();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateUser();
            }
        });
    }

    private void validateUser()
    {

    }
    private void addUser()
    {
        String namevalue=userEditText.getText().toString().trim();
        String passvalue=passwordEditText.getText().toString().trim();
        if(!TextUtils.isEmpty(namevalue))
        {
            String id=databaseUser.push().getKey();
            User user=new User(id,namevalue,passvalue);
            databaseUser.child(id).setValue(user);
            Toast.makeText(this,"New User Added",Toast.LENGTH_LONG).show();
        }else
        {
            Toast.makeText(this,"U should enter a name",Toast.LENGTH_LONG).show();
        }
    }
}
