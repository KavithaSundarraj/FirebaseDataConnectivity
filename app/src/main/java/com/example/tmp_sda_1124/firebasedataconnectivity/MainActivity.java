package com.example.tmp_sda_1124.firebasedataconnectivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText userEditText;
    private EditText passwordEditText;
    private Button newUserButton;
    private Button loginButton;

    private ArrayList<User> users;
    private User validUser;
    DatabaseReference databaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        users=new ArrayList<>();

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

    @Override
    protected void onStart() {
        super.onStart();

        databaseUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                users.clear();
                for(DataSnapshot userSnapshot: dataSnapshot.getChildren())
                {
                    User user=userSnapshot.getValue(User.class);
                    users.add(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void validateUser()
    {
        String namevalue=userEditText.getText().toString().trim();
        String passvalue=passwordEditText.getText().toString().trim();
        boolean found=false;

        for(User u:users)
        {
            if(u.getUserName().equals(namevalue) && u.getUserpassword().equals(passvalue )) {
                validUser = new User(u.getUserId(), u.getUserName(), u.getUserpassword(), u.getHighScore(), u.getScore());
                found = true;
            }
        }
        if(found)
        {
            Toast.makeText(this,"Valid User",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"InValid User.Please register",Toast.LENGTH_LONG).show();
        }

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
