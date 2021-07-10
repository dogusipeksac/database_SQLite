package com.example.database_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBManager {
    private SQLiteDatabase sqLiteDatabase;
    static  final  String DBName="students";
    static  final  String tableName="Login";
    static  final  String columnUserName="UserName";
    static  final  String columnUserPassword="Password";
    static final int DBVersion=3;
    //create table Login(Id integer primary key autincrement,Username text,Password text)

    static  final String createTable=" CREATE TABLE IF NOT EXISTS " +
            tableName+"(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            columnUserName+" TEXT,"+columnUserPassword+" TEXT);";
    static class DatabaseHelperUser extends SQLiteOpenHelper{
        Context context;
        DatabaseHelperUser(Context context){
            super(context,DBName,null,DBVersion);
            this.context=context;
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable);
            Toast.makeText(context, "Table is created", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table IF EXISTS "+tableName);
        onCreate(db);
        }
    }
    //adb root
    //adb -e pull /data/data/com.example.database_sqlite/databases/students Desktop/data
    DBManager(Context context){
        DatabaseHelperUser db=new DatabaseHelperUser(context);
        sqLiteDatabase=db.getWritableDatabase();
    }
    public long Insert(ContentValues contentValues){
        long ID=sqLiteDatabase.insert(tableName,"",contentValues);
        //could insert id is user id ,or fail id is or equal 0
        System.out.println("afssssssssssssssssss"+ID);
        return ID;

    }
}
