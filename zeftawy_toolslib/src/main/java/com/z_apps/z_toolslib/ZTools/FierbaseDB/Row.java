package com.z_apps.z_toolslib.ZTools.FierbaseDB;


import java.util.HashMap;


public class Row {
    Cell[] cell ;
    String[]values ;

    public Cell[] getCell() {
        return cell;
    }

    HashMap<String , Object > row = new HashMap<String , Object>() ;

    public HashMap<String, Object> getRow() {
        return row;
    }

    public Row(Cell[] cell) {

        this.cell = cell;
        int i = 0;
        int c = cell.length;
        while (i<c){
            row.put(cell[i].Name , cell[i].getValue());
            i++;
        }
    }
    public Row() {

    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        if (cell.length == values.length){
            this.values = values;
            int i = 0;   int c = cell.length;
            while (i<c){
                setValuew(cell[i],values[i]);
                i++;
            }
        }
    }

    public Object getvalue(Cell cell ){

        return    row.get(cell.getName());
    }

    public Object getvalue(String cell ){

        return    row.get(cell );
    }
    public Object getvalue(int   cells ){

        return    row.get(cell[cells].getName() );
    }
    public void setValuew(Cell key , String val){

        row.put(key.getName(),val);

    }

    public void setValuew(Cell cel[]  ){
        for (Cell c :cel
             ) {
            row.put(c.getName(),c.getValue());

        }
      cell = cel ;
    }

}
