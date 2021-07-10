 package com.example.database_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

 public class MainActivity extends AppCompatActivity {
    DBManager dbManager;
    EditText userName;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager=new DBManager(this);
        userName=findViewById(R.id.userName);
        password=findViewById(R.id.password);
    }

     public void saveButton(View view) {
         ContentValues values=new ContentValues();
         values.put(DBManager.columnUserName,userName.getText().toString());
         values.put(DBManager.columnUserPassword,password.getText().toString());
         long id=dbManager.Insert(values);
         if(id>0){
             Toast.makeText(this, "Data is added and user id:", Toast.LENGTH_SHORT).show();
         }
         else
         {
             Toast.makeText(this,"cannot insert",Toast.LENGTH_SHORT).show();
         }
     }

     public void loadButton(View view) {
     }
 }