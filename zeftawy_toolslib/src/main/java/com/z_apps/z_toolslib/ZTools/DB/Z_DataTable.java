package com.z_apps.z_toolslib.ZTools.DB;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * تخصص الادوات المساعدة للاتصال بقواعد البيانات و التعامل معها
 */



public class Z_DataTable {
    public  Cell[] getCell() {
        return Cell;
    }

    Z_Database_Connection  connection ;

    public Z_Database_Connection getConnection() {
        return connection;
    }
    Activity activity ;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Activity getActivity() {
        if (context instanceof Activity){
            return (Activity) context;
        }else
        {  return activity;}
    }
    public Context getContext() {
        return context;
    }

    public String getSQLstatment() {
        return SQLstatment;
    }

    public void setSQLstatment(String SQLstatment) {
        this.SQLstatment = SQLstatment;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    Cell [] Cell ;
    Cell[] HaVing;
    Cell[] Wherecells; String[] WhereValues;
ArrayList<String> prpsStringses = new ArrayList<String>();

    String orderby  = "";
    public  String setOrderby (Cell cell , boolean isAssceid){
        String Ass ;
        if (isAssceid){Ass = "ASC"; }else {Ass = "DESC"; }

        return " ORDER BY " +  cell.getName() + " " + Ass ;
    }

    void setwherevalues (){


    int i , c  ;
    i = 0 ;
    c  = Wherecells.length;
    WhereValues = new String[c];
    while (i<c )
    {

WhereValues[i] = Wherecells[i].getWhereValue();
Log.d("? "+ i , Wherecells[i].getWhereValue());

        i++;
    }
}

    public void setHaVing( Cell[] haVing) {
        HaVing = haVing;
    }

    public void setWherecells(Cell[] wherecells) {
        Wherecells = wherecells;
setwherevalues();
    }

    public void setCell(Cell[] cell) {
        Cell = cell;
    }
    public void setCell(Cell[] cell,Cell [] Wherecell ) {
        Cell = cell;
        setWherecells(Wherecell);
    }


    Z_Datatableveiw datatableveiw;
String SQLstatment ;
Context context ;
    Baseadabtor baseadabtor;

    public Baseadabtor getBaseadabtor() {
        return baseadabtor;
    }

    public Z_DataTable( Context c ,String DB ) {
        this.connection = new Z_Database_Connection(c , DB+".db");
        context = connection.mContext;
    }


    public Z_DataTable(Z_Database_Connection connection) {
        this.connection = connection;
context = connection.mContext;
    }
    public Z_DataTable(String Tablename) {
      this.TableName =Tablename;
    }
    public Z_DataTable(int Rlayout, Context cont) {
        context = cont ;
        layoutR = Rlayout;
    }

    public int getLayoutR() {
        return layoutR;
    }

    public void setLayoutR(int layoutR) {
        this.layoutR = layoutR;
    }

    public String getTableName() {
        return TableName;
    }

    int layoutR ;

    public DataView getDataView() {
        return dataView;
    }

    public void setDataView(DataView dataView) {
        this.dataView = dataView;
    }

    DataView dataView;
    public void setConnection(Z_Database_Connection connection) {
        this.connection = connection;
    }
    public String getDatabaseName (){
        return connection.DBNAME ;
    }
    public  int getDatabaseverison (){
        return connection.DBVirsoin ;
    }

    public void setTableName(String tableName) {
        TableName = tableName;
    }

      String TableName ;
   public  class    SQL {
       final String sql_text_type = "TEXT";
       final String sql_Primary_key = "INTEGER PRIMARY KEY   AUTOINCREMENT ";
       final String sql_REAL_type = "REAL";
       final String sql_Int_type = "INT";
       final String sql_NUMERIC_type = "NUMERIC";
       final String sql_Date_type = "DATE";
       final String sql_Bool_type = "Bool";

       final String Create_="CREATE TABLE ";
       final String Drop = "DROP TABLE IF EXISTS ";

   }
    public String SelectAll(String Where){
        SQLstatment =   "Select * from  "+  TableName + " Where " + Where ;
        Log.i("SQl select", SQLstatment);
        return  SQLstatment;
    }
    public String SelectAll(){
        SQLstatment =   "Select * from  "+  TableName  ;
        Log.i("SQl select", SQLstatment);
        return  SQLstatment;
    }
public String Selectfields(){
    String s = "";
    int c = Cell.length;
    int i = 0;
    while (i< c){
        if (i == 0 ){s = Cell[i].name; i ++ ; continue;}else  {
            s= s+","+Cell[i].name ;} i++;
    }
    SQLstatment = "Select "+ s+ " From " + TableName ;
    Log.i("SQl select", SQLstatment);
    return  SQLstatment;
}

    public String Selectfields(String Where){
        String s = "";

        String wh =" where " + Where;
        if (Where==""){
            wh="";
        }
        int c = Cell.length;
        int i = 0;
        while (i< c){
            if (i == 0 ){s = Cell[i].name; i ++ ; continue;}else  {
                s= s+","+Cell[i].name ;} i++;
        }
        SQLstatment = "Select "+ s+ " From " + TableName + wh;
        Log.i("SQl select", SQLstatment);
        return  SQLstatment;
    }
    public Curseradaptor Load_Dataviewtolist( Cursor cur  ){
        Cursor cursor = cur ;
        Curseradaptor customrow = new Curseradaptor(context,cursor,layoutR, dataView);
        return customrow ;
    }
    public Curseradaptor Load_Dataviewtolist( String sql ){
        Cursor cursor = get_tableCurser(sql);
        Curseradaptor customrow = new Curseradaptor(context,cursor,layoutR, dataView);
        return customrow ;
    }
    public void Setup_Datatableview( ){

          datatableveiw = new Z_Datatableveiw(Cell);


    }
    public void Setup_Datatableview( Cell [] Cells){

          datatableveiw = new Z_Datatableveiw(Cells);


    }
public  void Addrow_Datatableview (Row row ){
    if( datatableveiw == null ){ datatableveiw = new Z_Datatableveiw(Cell);}
    datatableveiw.addrow(row);
}

    public Baseadabtor Load_Dataviewtolistbase ( String sql ){
        Cursor cur = get_tableCurser(sql);
          datatableveiw = new Z_Datatableveiw(Cell); datatableveiw.get_Rows(cur);
        baseadabtor = new Baseadabtor(context,datatableveiw.Rows,layoutR,dataView);

        return baseadabtor ;
    }




    public ArrayList<Row> Loadinrow(String SQLstatment){


        Cursor cur = get_tableCurser(SQLstatment);
        datatableveiw = new Z_Datatableveiw(Cell);  datatableveiw.get_Rows(cur);
   return  datatableveiw.Rows;
    }
    public  ArrayList<Row> getRows(){
   return      datatableveiw.getRows();

    }

    public  ArrayList<Row> getTable(){
        return      datatableveiw.getRows();

    }

    public Z_Datatableveiw LoadinDatatableview(String SQLstatment){


        Cursor cur = get_tableCurser(SQLstatment);
        datatableveiw = new Z_Datatableveiw(Cell);  datatableveiw.get_Rows(cur);
        return  datatableveiw;
    }
    public Baseadabtor Load_Dataviewtolistbase (String sql , Cell[] cells ){
        Cursor cur = get_tableCurser(sql);
          datatableveiw = new Z_Datatableveiw(cells); datatableveiw.get_Rows(cur);
        baseadabtor = new Baseadabtor(context,datatableveiw.Rows,layoutR,dataView);
        return baseadabtor ;
    }
    public Baseadabtor Load_Dataviewtolistbase (Cursor cur , Cell[] cells ){

          datatableveiw = new Z_Datatableveiw(cells); datatableveiw.get_Rows(cur);
        baseadabtor = new Baseadabtor(context,datatableveiw.Rows,layoutR,dataView);
        return baseadabtor ;
    }
    public Baseadabtor Search_Dataviewtolistbase (Cursor cur , Cell[] cells , int Cullom , String val ){

        datatableveiw = new Z_Datatableveiw(cells); datatableveiw.get_Rows(cur);
        baseadabtor = new Baseadabtor(context,datatableveiw.get_Rowslist_Search(Cullom , val),layoutR,dataView);
        return baseadabtor ;
    }
    public Baseadabtor Search_Dataviewtolistbase (String sql , int Cullom , String val ){

        datatableveiw = new Z_Datatableveiw(Cell); datatableveiw.get_Rows(get_tableCurser(sql));
        baseadabtor = new Baseadabtor(context,datatableveiw.get_Rowslist_Search(Cullom , val),layoutR,dataView);
        return baseadabtor ;
    }

    public  int getRowCount(){
        Cursor c =connection.getTableCurser(SQLstatment);
        return  c.getCount();
    }
    public  int get_tablecolmentcount(){
        Cursor c = connection.getTableCurser(SQLstatment);
        return  c.getColumnCount();
    }
    public Cursor get_tableCurser(String select) {
     Log.i("SQL", select);
        if (WhereValues == null) {
            return connection.getTableCurser(select );

        } else {
            return connection.getTableCurser(select,WhereValues);
        }
    }
    public ContentValues setvalue(ContentValues[]contentValues){
    ContentValues cv = new ContentValues();
    int c =  contentValues.length;
    int i = 0 ;
    while (i<c){
        cv.putAll(contentValues[i]);
        i++;
    }

    return  cv ;
}
    public ContentValues setvalue( ){

        ContentValues cv = new ContentValues();
        int c =  contentValues.length;
        int i = 0 ;
        while (i<c){
            cv.putAll(contentValues[i]);
            i++;
        }

        return  cv ;
    }


    public void setContentValues(ContentValues[] contentValues) {
        this.contentValues = contentValues;
    }

    ContentValues[]contentValues ;
    public  void  Insert(){
if (contentValues!= null ) {
    connection.Insert(TableName, setvalue(contentValues));
}else {
   ContentValues cvs[]  =new ContentValues[Cell.length];
    int i = 0 ;
    while (i<Cell.length){

        cvs[i] = Cell[i].getContantvalue();i++;
    }
    connection.Insert(TableName, setvalue(cvs));

}
    }


    public  void Delete(){
        connection.Delete(TableName);
    }
    public  void Delete(String where){
        connection.Delete(TableName, where);
    }
    public  void  Update(String where){


        if (contentValues!= null ) {
            connection.Update(TableName,setvalue(contentValues),where);
        }else {
            ContentValues cvs[]  =new ContentValues[Cell.length];
            int i = 0 ;
            while (i<Cell.length){

                cvs[i] = Cell[i].getContantvalue();i++;
            }
            connection.Update(TableName,setvalue(cvs),where);

        }
    }

public String SQL_Where() {
    // حتى لا يتم تكرار المعاملات و لا يتكرر الشرط

    int i = 0;
    int c = Wherecells.length;
    String s = "";
    while (i < c) {
        if (i == 0) {

            s = Wherecells[i].Wherestring;
            i++;
            continue;

        } else {

            s = s + Wherecells[i].getAndOR() + Wherecells[i].getWherestring();
            i++;
            continue;

        }

    }
    Log.i("SQL Where ", s );
    return s;
}


   public String SQL_SelectExp(String Where){
        String s = "";

        if (Cell != null){
            int c = Cell.length;
            int i = 0;
            while (i< c){

                if (i == 0 ){s = selectsum(Cell[i]); i ++ ; continue;}else  {
                    s= s+","+ selectsum(Cell [i]) ;} i++;
            }

            i = 0 ;
            String grob = "";
            while (i< c){

                if (grob == "" ){
                    if (Cell[i].isSelectAsSum()  ){i++ ; continue; }else {
                        grob = Cell[i].getSelects() ; i ++ ; continue;}}else  {

                    if (Cell[i].isSelectAsSum()  ){i++ ; continue; }else {

                        grob =  grob+","+ Cell[i].getSelects() ;} i++;
                }}
            String Groupby ;

            if (grob == "" ){
                Groupby = "";
            }else {
                Groupby = "  GROUP BY  "+ grob;}

            String Wheres  ;
            if (Where == "" ){
                Wheres = "";
            }else {
                Wheres = " WHERE "+ Where;}

            String Havings;
            if (HaVing == null ){Havings="";}else{
                Havings =  SQL_Having();
            }

            SQLstatment = "Select "+ s+ " From " + TableName + " " + Wheres +  " "+ Groupby +" " + Havings;
            System.out.println(SQLstatment);
            return  SQLstatment;
        }else{
            SQLstatment = "Select * From " + TableName + " Where ";
            System.out.println(SQLstatment);
            return  SQLstatment;

        }

    }
    String selectsum(Cell cell){
        if (cell.isSelectAsSum() ){
            return cell.selectsum();
        }else{

                return cell.getSelects()+" As "+cell.getName() ;}

        }

    public String SQL_Having()
    {
        // حتى لا يتم تكرار المعاملات و لا يتكرر الشرط

        int i = 0 ;int c = HaVing.length;
        String s = "";
        while (i<c) {
            if (i==0){


                s = HaVing[i].getHavingString();  }
            else{

                s=s+HaVing[i].getAndOR()+" "+HaVing[i].getHavingString();
            }
            prpsStringses.add(HaVing[i].getValue()+"");
            i++;
        }
        System.out.println(s);
        return "Having "+ s;
    }

    public void  testSelect(String we){
        Cursor c = get_tableCurser(Selectfields(we));
        int i = 0 ;
        int ic = 0 ;
        int cc = c.getColumnCount();
        c.moveToFirst();
        while (i<c.getCount()){
            Log.i("row no ", i + " ");
        while (ic<cc){
String coll = c.getColumnName(ic);
            String val = c.getString(ic);
            Log.i(c.getColumnName(ic),c.getString(ic)+""
            );
            ic++;
        }i++;ic= 0 ;
        c.moveToNext();}

    }
public  Baseadabtor LoadAutoLoadadbator(String SQL, int R ){ setLayoutR(R);
    Cursor cur = get_tableCurser(SQL);
    datatableveiw = new Z_Datatableveiw(Cell); datatableveiw.get_Rows(cur);

return AutoLoaddabtore();
}
    public Baseadabtor AutoLoaddabtore() {
        setLayoutR(getLayoutR());
        DataView dv = new DataView() {


            @Override
            public void bindview(View view, Cursor cursor) {

            }

            @Override
            public View bindview(final int position, View convertView, Context context, int Rlayou, final ArrayList<Row> rows) {


                View conatnv = AutoLoadView(position , rows);
                if (addvew != null ){
                    convertView =   addvew.Addview(conatnv , position , rows);
                }else {
                    convertView =   conatnv ;
                }
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("pos" , position + "");
                        int i = 0 ; int c = getCell().length;
                        while (i<c){
                            getCell()[i].setValue(datatableveiw.getRows().get(position).getvalue(getCell()[i]));
                            i ++ ;
                        }
                        if (getEventcontarers()!=null) {
                            getEventcontarers().ItemCleicked(v ,  getCell());
                        }

                     /*   if (getConentR()!=0){
                            setinputvalue(getCell());
                        }*/

                    }
                });
                return  convertView;
            }

            @Override
            public View bindviewOnline(int position, View convertView, Context context, int Rlayou, ArrayList<JSONObject> rows) {
                return null;
            }
        };


       baseadabtor =   (new Baseadabtor(getContext(), datatableveiw.getRows(), this.getLayoutR(), dv));
        return baseadabtor;
    }

    public Baseadabtor LoadAutoLoaddabtore(final ArrayList<Row> row , int R) {
        setLayoutR(R);
        DataView dv = new DataView() {


            @Override
            public void bindview(View view, Cursor cursor) {

            }

            @Override
            public View bindview(final int position, View convertView, Context context, int Rlayou, final ArrayList<Row> rows) {

setCell(rows.get(0).getCell());
                View conatnv = AutoLoadView(position , rows);
                if (addvew != null ){
                    convertView =   addvew.Addview(conatnv , position , rows);
                }else {
                    convertView =   conatnv ;
                }
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("pos" , position + "");
                        int i = 0 ; int c = getCell().length;
                        while (i<c){
                            getCell()[i].setValue(row.get(position).getvalue(getCell()[i]));
                            i ++ ;
                        }
                        if (getEventcontarers()!=null) {
                            getEventcontarers().ItemCleicked(v,  getCell());
                        }

                     /*   if (getConentR()!=0){
                            setinputvalue(getCell());
                        }*/

                    }
                });
                return  convertView;
            }

            @Override
            public View bindviewOnline(int position, View convertView, Context context, int Rlayou, ArrayList<JSONObject> rows) {
                return null;
            }
        };


        baseadabtor =   (new Baseadabtor(getContext(), row, this.getLayoutR(), dv));
        return baseadabtor;
    }

    public View AutoLoadView(int position , ArrayList<Row> rows){

        LayoutInflater inflater;
        inflater = LayoutInflater.from(context);
        View convertView = inflater.inflate(getLayoutR(), null);

        int i , c  ;
        i = 0 ;  c = getCell().length ;
        while (i<c) {
            if (getCell()[i].getR() != 0) {
               /* if (getCell()[i].isImg()) {
                    ImageView img = (ImageView) convertView.findViewById(getCell()[i].getR());
                    String imguri = rows.get(position).getvalue(getCell()[i]) + "";

                    Picasso.with(context).load(imguri).fit().into(img);

                } else {*/
                    TextView txt = convertView.findViewById(getCell()[i].getR());
                    String txts = rows.get(position).getvalue(getCell()[i]) + "";

                    txt.setText(txts);

                }
            i++;
            }



        return convertView;
    }

    AddnewFeactuerAdaptor addvew ;

    public void setAddvew(AddnewFeactuerAdaptor addvew) {
        this.addvew = addvew;
    }

    public AddnewFeactuerAdaptor getAddvew() {
        return addvew;
    }

    EventContanterintmCliked eventcontarers;
    public EventContanterintmCliked getEventcontarers() {
        return eventcontarers;
    }

    public void setEventcontarers(EventContanterintmCliked eventcontarers) {
        this.eventcontarers = eventcontarers;
    }


RecyclerView recyclerView ;

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public void onloadDataRecyclerview(final ArrayList<Row> Table, LinearLayoutManager len , int Rlayout) {
         recyclerView.setLayoutManager(len);

         recycleViewAdabtor = new RecycleViewAdabtor( getContext()  , Table , Rlayout  );
        if (addvew != null) {
            recycleViewAdabtor.setaddview(addvew);
        }
        recyclerView.setAdapter(recycleViewAdabtor);
        recycleViewAdabtor.setContainerclick(new Recyclecontainerclecked() {
            @Override
            public void onclick(View v ,  Cell[] cell) {
                if (getEventcontarers()!=null){
                    getEventcontarers().ItemCleicked( v ,  cell);
                }
            }
        });
    }

public  void Load_DataAutoloadRecyclerveiw(String SQL , RecyclerView rec , LinearLayoutManager len , int Rlayout )
{

    recyclerView = rec;
    Cursor cur = get_tableCurser(SQL);
    datatableveiw = new Z_Datatableveiw(Cell); datatableveiw.get_Rows(cur);
onloadDataRecyclerview(datatableveiw.Rows , len , Rlayout);
}


    public  void Load_DataAutoloadRecyclerveiw(final ArrayList<Row> table , RecyclerView rec , LinearLayoutManager len , int Rlayout )
    {

        recyclerView = rec;
         onloadDataRecyclerview(table, len , Rlayout);
    }

    RecycleViewAdabtor recycleViewAdabtor ;

    public RecycleViewAdabtor getRecycleViewAdabtor() {
        return recycleViewAdabtor;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public LinearLayoutManager GetRecyclervewlayoutManagerLinearVertecal (){
        LinearLayoutManager lm  =new LinearLayoutManager( getContext());
        lm.setOrientation(RecyclerView.VERTICAL);

        return lm;}

    public LinearLayoutManager GetRecyclervewlayoutManagerLinearHorezental (){
        LinearLayoutManager lm  =new LinearLayoutManager( getContext());
        lm.setOrientation(RecyclerView.HORIZONTAL);
        return lm;}


    public LinearLayoutManager GetRecyclervewlayoutManagerGride(int Culoms){
        return new GridLayoutManager(getContext() , Culoms);

    }



    public void RecyclerviewdeleteItemAnimation(View rowView, final int position, int Animation, int duration) {

        android.view.animation.Animation anim = AnimationUtils.loadAnimation( getContext(),
                Animation);
        anim.setDuration(duration);
        rowView.startAnimation(anim);

        new Handler().postDelayed(new Runnable() {
            public void run() {

                datatableveiw.getRows()  .remove(position); //Remove the current content from the array
             recycleViewAdabtor  .    notifyDataSetChanged(); //Refresh list

                if  (datatableveiw.getRows() .size() == 0) {
                    // addEmptyView(); // adding empty view instead of the RecyclerView
                    getActivity().finish();

                    return;
                }
            }

        }, anim.getDuration());
    }

    public void RecyclerviewAddItemAnimation(final Row r , View rowView, int Animation, int duration) {

        android.view.animation.Animation anim = AnimationUtils.loadAnimation( getContext(),
                Animation);
        anim.setDuration(duration);
        rowView.startAnimation(anim);

        new Handler().postDelayed(new Runnable() {
            public void run() {

                datatableveiw.getRows()  .add(r); //Remove the current content from the array
                recycleViewAdabtor  .    notifyDataSetChanged(); //Refresh list

                if  (datatableveiw.getRows() .size() == 0) {
                    // addEmptyView(); // adding empty view instead of the RecyclerView
                    getActivity().finish();

                    return;
                }
            }

        }, anim.getDuration());
    }

    public LayoutAnimationController GetRecyclerviewAnimationLayout(int resId){

        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
        return animation;
    }


}
