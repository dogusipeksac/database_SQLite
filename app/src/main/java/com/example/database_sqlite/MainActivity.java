 package com.example.database_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

 public class MainActivity extends AppCompatActivity {
    DBManager dbManager;
    EditText userNameText;
    EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager=new DBManager(this);
        userNameText=findViewById(R.id.userName);
        passwordText=findViewById(R.id.password);
    }

     public void saveButton(View view) {
         ContentValues values=new ContentValues();
         values.put(DBManager.columnUserName,userNameText.getText().toString());
         values.put(DBManager.columnUserPassword,passwordText.getText().toString());
         long id=dbManager.Insert(values);
         if(id>0){
             Toast.makeText(this, "Data is added and user id:", Toast.LENGTH_SHORT).show();
         }
         else
         {
             Toast.makeText(this,"cannot insert",Toast.LENGTH_SHORT).show();
         }
     }
     ArrayList<AdapterItems> listNewsData=new ArrayList<AdapterItems>();
     MyCustomAdapter myCustomAdapter;
     public void loadButton(View view) {
            LoadElemet();
     }
     public void LoadElemet(){
         String[] selectionArgs={"%"+userNameText.getText().toString()+"%"};
         Cursor cursor=dbManager.query(null,"UserName like ? ",selectionArgs,DBManager.columnUserName);
         listNewsData.clear();

         if(cursor.moveToFirst()){
             String tableData="";
             do{
                 /* tableData+=cursor.getString(cursor.getColumnIndex(DBManager.columnUserName))+","+
                        cursor.getString(cursor.getColumnIndex(DBManager.columnUserPassword))+"::";
                 */
                 listNewsData.add(new AdapterItems(cursor.getString(cursor.getColumnIndex(DBManager.columnUserId)),
                         cursor.getString(cursor.getColumnIndex(DBManager.columnUserName)),
                         cursor.getString(cursor.getColumnIndex(DBManager.columnUserPassword))));
                 myCustomAdapter=new MyCustomAdapter(listNewsData);
             }while (cursor.moveToNext());
             Toast.makeText(this, tableData, Toast.LENGTH_LONG).show();
         }
         //String[]  projection={"UserName","Password"};

         ListView lsNews=findViewById(R.id.showStudent);
         lsNews.setAdapter(myCustomAdapter);
     }




     private class MyCustomAdapter extends BaseAdapter{

         public  ArrayList<AdapterItems> listNewsDataAdapter;
         public MyCustomAdapter(ArrayList<AdapterItems> adapterItemsArrayList){
            this.listNewsDataAdapter=adapterItemsArrayList;
        }

         @Override
         public int getCount() {
             return listNewsDataAdapter.size();
         }

         @Override
         public Object getItem(int position) {
             return null;
         }

         @Override
         public long getItemId(int position) {
             return position;
         }

         @Override
         public View getView(int position, View convertView, ViewGroup parent) {

             LayoutInflater inflater=getLayoutInflater();
             View myView=inflater.inflate(R.layout.layout_ticked,null);
             final AdapterItems s=listNewsDataAdapter.get(position);
             TextView userId=myView.findViewById(R.id.textViewId);
             userId.setText(s.id);
             TextView userName=myView.findViewById(R.id.textViewUserName);
             userName.setText(s.userName);
             TextView password=myView.findViewById(R.id.textViewPassword);
             password.setText(s.password);
             Button buttonDelete=myView.findViewById(R.id.deleteButton);
             buttonDelete.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     String[] selectionArgs={s.id};
                     int count=dbManager.Delete("ID=?",selectionArgs);
                     if (count>0){
                        LoadElemet();
                     }
                 }
             });
             Button buttonUpdate=myView.findViewById(R.id.updateButton);
              buttonUpdate.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      userNameText.setText(s.userName);
                      passwordText.setText(s.password);
                  }
              });


             return myView;


         }

     }



 }