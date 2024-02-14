package com.z_apps.z_toolslib.ZTools.DB;

import android.database.sqlite.SQLiteDatabase;

public   interface Z_DBConnectionTools{
    String DB_name()  ; int versoin();
   void DB_Create(SQLiteDatabase db);
           void  DB_Drop(SQLiteDatabase db);}
