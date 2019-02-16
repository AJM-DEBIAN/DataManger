package com.example.alumno.dbmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.util.Log;


/**
 * Created by ALUMNO on 13/02/2019.
 */

public class dataManager {
  private SQLiteDatabase db;
    public static final String tableRowId="_id";
    public static final String tableRowName="name";
    private static final String dbName="cursoAndroid";
    private static final int dbVersion=1;
    private static final String tableName="tester";

    public dataManager(Context context){
        MyCustomSQLiteOpenHelper helper= new MyCustomSQLiteOpenHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insert(String name){

        String query = "INSERT INTO "+ tableName+ " ("+tableRowName+") VALUES (" + "'"+name+"');";
        Log.i("Insert",query);
        db.execSQL(query);
    }

    public void delete(String name){
        String queryDelete = "DELETE FROM "+tableName+ " WHERE " + tableRowName+" = "+"'"+name+"';";
        Log.i("Delete",queryDelete);
        db.execSQL(queryDelete);
    }

    public Cursor search(String name){
        String querySearch = " Select "+tableRowName+" From "+tableName+
                " where "+tableRowName+" = "+"'"+name+"';";
        Log.i("Search",querySearch);
        Cursor c = db.rawQuery(querySearch,null);
        return c;
    }

    public Cursor selectAll(){
        String queryAll = " Select * FROM "+ tableName + ";";
        Log.i("ALL",queryAll);
        Cursor c = db.rawQuery(queryAll,null);
        return c;
    }

    private class MyCustomSQLiteOpenHelper extends SQLiteOpenHelper{
        public MyCustomSQLiteOpenHelper(Context context) {
            super(context, dbName, null, dbVersion);
        }
        @Override
        public void onCreate(SQLiteDatabase db){
        String newTableQuery = "Create TABLE "+tableName + " ("
                                                         + tableRowId + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                                                         + tableRowName + " TEXT NOT NULL);";
            Log.i("DB Creation",newTableQuery);
            Log.i("valores",""+db);
            db.execSQL(newTableQuery);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        }

    }
}

