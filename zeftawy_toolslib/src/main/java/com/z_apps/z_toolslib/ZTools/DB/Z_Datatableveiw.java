package com.z_apps.z_toolslib.ZTools.DB;

import android.database.Cursor;
import android.util.Log;

import org.json.JSONArray;

import java.util.ArrayList;


/**
 * Created by New on 25/01/2017.
 */
public class  Z_Datatableveiw
{
       Cell[] cell ;


    Row row ;
    ArrayList<Row> Rows = new ArrayList<Row>();
    ArrayList<Row> maineRows = new ArrayList<Row>();

String[] [] Table;
    public Z_Datatableveiw(Cell[] cell) {
        this.cell = cell;
        row = new Row(cell);

    }
    public Z_Datatableveiw(ArrayList<Row> table)  {

        Rows  = table;
        maineRows = table;

        cursrRows = Rows.size();
    }

    int rowCullom  , cursrRows  ;

    public Cell[] getCell() {
        return cell;
    }

    public void setCell(Cell[] cell) {
        this.cell = cell;
    }

    public Row getRow() {
        return row;
    }

    public void setRow(Row row) {
        this.row = row;
    }

    public ArrayList<Row> getRows() {
        return Rows;
    }

    public void setRows(ArrayList<Row> rows) {
        Rows = rows;
    }

    public ArrayList<Row> getMaineRows() {
        return maineRows;
    }

    public void setMaineRows(ArrayList<Row> maineRows) {
        this.maineRows = maineRows;
    }

    public String[][] getTable() {
        return Table;
    }

    public void setTable(String[][] table) {
        Table = table;
    }

    public int getcullomcount() {
        return rowCullom;
    }
    public int getcullomcountReport() {
        return Reportcell.size();
    }
    public void setRowCullom(int rowCullom) {
        this.rowCullom = rowCullom;
    }

    public int getrowcont() {
        return cursrRows;
    }

    public void setCursrRows(int cursrRows) {
        this.cursrRows = cursrRows;
    }

    public String[][] get_Rows(Cursor cursor){
        int ic = 0 ; int ir = 0 ;
        cursrRows = cursor .getCount() ;
        rowCullom= cell.length;
        cursor.moveToFirst();

        String[] [] table= new String[cursrRows][rowCullom];
        while (ir < cursrRows ){
            ic = 0 ;
            Row rowval = new Row(cell) ;
            String[] stringval = new String[rowCullom];
while (ic < rowCullom){
table[ir][ic] =  cursor.getString(cursor.getColumnIndex(cell[ic].name));
stringval[ic] = cursor.getString(cursor.getColumnIndex(cell[ic].name));

    rowval.setValues(stringval);
   ic ++ ;
}
            Rows.add(rowval);
            maineRows.add(rowval);
         cursor.moveToNext();
            ir++;
        }
        Log.i("row list no ", ""+ ir );
        Table = table;
        return  Table;
    }
    public ArrayList<Row> get_Rowslist(Cursor cursor){
    int ic = 0 ; int ir = 0 ;
    cursrRows = cursor .getCount() ;
    rowCullom= cell.length;
    cursor.moveToFirst();

    String[] [] table= new String[cursrRows][rowCullom];
    while (ir < cursrRows ){
        ic = 0 ;
        Row rowval = new Row(cell) ;
        String[] stringval = new String[rowCullom];
        while (ic < rowCullom){
            table[ir][ic] =  cursor.getString(cursor.getColumnIndex(cell[ic].name));
            stringval[ic] = cursor.getString(cursor.getColumnIndex(cell[ic].name));
            rowval.setValues(stringval);
            ic ++ ;
        }
        Rows.add(rowval);
        cursor.moveToNext();
        ir++;
    }


    return  Rows;
}
    public ArrayList<Row> get_Rowslist_Search(int c, CharSequence Val){
        ArrayList<Row> rowlist = new ArrayList<Row>() ;
        if(c>rowCullom ){c=rowCullom-1;}


       int i = 0 ;
     while (i < cursrRows) //for search
          {
              String value = maineRows.get(i).values[c] ;

              if (value.contains(Val)) {
                  rowlist.add(maineRows.get(i) );
              }i++;
          }

        return  rowlist ;
    }

    public ArrayList<Row> get_Rows(JSONArray cursor){
        int ic = 0 ; int ir = 0 ;
        cursrRows = cursor .length() ;
        rowCullom= cell.length;

        String[] [] table= new String[cursrRows][rowCullom];
        while (ir < cursrRows ){
            ic = 0 ;
            Row rowval = new Row(cell) ;
            String[] stringval = new String[rowCullom];
            while (ic < rowCullom){
             try {
                 table[ir][ic] =   cursor.getJSONObject(ir).getString( cell[ic].name) ;
                stringval[ic] =   cursor.getJSONObject(ir).getString( cell[ic].name)  ;
                rowval.setValues(stringval);
                ic ++ ;}catch (Exception e ){
                 Log.i("Get row error ", e.getMessage());
             }
            }
            Rows.add(rowval);
            maineRows.add(rowval);

            ir++;
        }
        Log.i("row list no ", ""+ ir );
        Table = table;
       return  Rows ;
    }


    public String getvalue(int c , int r ){
 if(c>rowCullom ){c=rowCullom-1;} if(r>cursrRows){r=cursrRows-1;}
        return   Rows.get(r).values[c];
    }
    public  void addrow(Row row){
        Rows.add(row);
    }

public  void SetTableToReport(){
        ArrayList<Cell> Cellsarry = new ArrayList();
}
public ArrayList<Cell> Reportcell ;

    public ArrayList<Cell> getReportcell() {
        return Reportcell;
    }

    public void setReportcell(ArrayList<Cell> reportcell) {
        Reportcell = reportcell;
    }

    public void setReportshow() {

        Reportcell = new ArrayList<>();
        for (Cell c : cell){
            if (c.isIsinReport()){
                Reportcell.add(c);
            }
        }


    }
}

