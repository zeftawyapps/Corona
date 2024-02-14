package com.z_apps.z_toolslib.ZTools.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

import androidx.annotation.RequiresApi;

public class Z_Database_Connection extends SQLiteOpenHelper {

     String DBNAME ; int DBVirsoin ; public Context mContext;
    String packagenames  ;
    //public static final String DBLOCATION = "/data/data/com.yaser.sqlight_local_db/databases/";

      String getPackagenames() {
        return packagenames;
    }
String DBLOCATION(){
    return Environment.getDataDirectory() + "/data/"+getPackagenames()+"/databases/";
}

    private SQLiteDatabase mDatabase;

    Z_DBConnectionTools z_dbConnectionTools;
    public Z_Database_Connection (Context context , Z_DBConnectionTools z_dbC){
        super(context, z_dbC.DB_name(), null ,z_dbC.versoin());
        mContext = context;
z_dbConnectionTools = z_dbC;
DBVirsoin = z_dbC.versoin();
    }

    public Z_Database_Connection (Context context , String name  ){
        super(context,  name, null , 1);
        mContext = context;
        packagenames = mContext.getPackageName();
     DBNAME = name ;

        z_dbConnectionTools = null;
checkdb();
    }


    public void CopyFile_out() {

        try {


            File sd = mContext.getExternalCacheDir();
            File data = mContext.getDatabasePath(DBNAME);
Log.i("coppeid to ", mContext.getExternalCacheDir().getPath());

            String currentDBPath = mContext.getDatabasePath(DBNAME).getPath();

            String backupDBPath = DBNAME;

            if (data.canWrite()) {
                File currentDB = new File(currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
Log.i("file coppeid to ",sd.getPath() );
                } else {
                   Log.e("Coppy error ", "File not found");
                }
            }

        } catch (Exception e) {
            Log.e("Coppy error ", e.getMessage());
        }
    }


    private SQLiteDatabase myDataBase;

    public Cursor get_DB_tabledata() {
        if (z_dbConnectionTools == null){openDatabase();}
        SQLiteDatabase sqs =getReadableDatabase();
        Cursor res =  sqs.rawQuery("SELECT name FROM sqlite_master", null);
        return res;
    }
    public String get_DB_tabledata_name(int i ) {
        if (z_dbConnectionTools == null){openDatabase();}
        SQLiteDatabase sqs =getReadableDatabase();
        Cursor res =  sqs.rawQuery("SELECT name FROM sqlite_master", null);


        res.moveToPosition(i);
        return res.getString(0);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (z_dbConnectionTools == null){return;}

        z_dbConnectionTools.DB_Drop(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
if (z_dbConnectionTools == null){return;}
           z_dbConnectionTools.DB_Create(db);


            // Toast.makeText(cont, "Created", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Log.i("Creat error ", e.getMessage());
        }}
        private  void  checkdb(){
            File database = mContext.getDatabasePath( DBNAME);
            if (false == database.exists()) {
                getReadableDatabase();
                if (copyDatabase(mContext)) {
                    Log.i("copeid", "true");
                    //Toast.makeText(MainActivity.this, "تم نسخ قاعدة البيانات بنجاح", Toast.LENGTH_SHORT).show();
                } else {
                    Log.i("copeid", "fales");
                    // Toast.makeText(MainActivity.this, "خطأ لم يتم نسخ قاعدة البيانات", Toast.LENGTH_SHORT).show();
                    return;
                }
            }else{
                Log.i("should", "open");
            }

        }
        public void openDatabase() {
            String dbPath = mContext.getDatabasePath(DBNAME).getPath();
            if (mDatabase != null && mDatabase.isOpen()) {
                return;
            }
            mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
            Log.i("opend", "true");
        }
    public Cursor getTableCurser(String SQLstatment) {
if (z_dbConnectionTools == null){openDatabase();}
        SQLiteDatabase sqs =  getReadableDatabase();
        Cursor res =  sqs.rawQuery(SQLstatment , null);
Log.i("Courser load count"," "+  res.getCount());

        return res;
    }


    public Cursor getTableCurser(String SQLstatment, String[] Arges ) {
        if (z_dbConnectionTools == null){openDatabase();}
        SQLiteDatabase sqs =  getReadableDatabase();
        Cursor res =  sqs.rawQuery(SQLstatment,Arges);

        return res;
    }


    public void closeDatabase() {
            if (mDatabase != null) {
                mDatabase.close();
            }
        }


        private boolean copyDatabase(Context context) {
            try {
                InputStream inputStream = context.getAssets().open( DBNAME);
                String outFileName =  DBLOCATION() +  DBNAME;
                OutputStream outputStream = new FileOutputStream(outFileName);
                byte[] buff = new byte[1024];
                int length = 0;
                while ((length = inputStream.read(buff)) > 0) {
                    outputStream.write(buff, 0, length);
                }
                outputStream.flush();
                outputStream.close();
                return true;
            } catch (Exception e) {

                Log.i("copyerror", e.getMessage());
                return  false;
            }

        }
    public  void Insert(String Tablename , ContentValues cv ){
        try {
            SQLiteDatabase db ;
            db = this.getWritableDatabase();
            db.insert(Tablename , null,cv);

            //   Toast.makeText(cont,"inserted",Toast.LENGTH_LONG).show();
            Log.i("insert db  ","true");
            db.close();}catch (Exception e ){
            Log.i("insert   error ",e.getMessage());}

    }

    public  void Update(String Tablename , ContentValues cv, String where ){
        try {
            SQLiteDatabase db ;
            db = this.getWritableDatabase();
            db.update(Tablename,cv,where,null);
            //   Toast.makeText(cont,"inserted",Toast.LENGTH_LONG).show();
            Log.i("update db  ","true");
            db.close();}catch (Exception e ){
            Log.i("Ubdate   error ",e.getMessage());}

    }

    public  void Delete(String TableName){
        SQLiteDatabase db = getWritableDatabase();
        db.delete( TableName ,"",null);
        db.close();
    }
    public  void Delete(String TableName, String Where){
        SQLiteDatabase db = getWritableDatabase();
        db.delete( TableName ,Where ,null);
        db.close();
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.disableWriteAheadLogging();
    }
}
