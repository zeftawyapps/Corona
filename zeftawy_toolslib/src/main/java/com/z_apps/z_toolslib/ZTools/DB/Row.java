package com.z_apps.z_toolslib.ZTools.DB;


import java.util.HashMap;

public class  Row {
    Cell[] cell ;
    String[]values ;

    public Cell[] getCell() {
        return cell;
    }

    HashMap<String , String > row = new HashMap<String , String >() ;


    public Row(Cell[] cell) {

        this.cell = cell;
        int i = 0;
        int c = cell.length;
        while (i<c){
            String s ;
            if (cell[i].getValue() == null ){s = "" ; }else {s = cell[i].getValue() + "" ; }
            row.put(cell[i].name , s);
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
                setValue(cell[i],values[i]);
                i++;
            }
        }
    }

    public String getvalue(Cell cell ){

        return    row.get(cell.getName());
    }

    public String getvalue(String cell ){

        return    row.get(cell );
    }
    public String getvalue(int   cells ){

        return    row.get(cell[cells].getName() );
    }
    public void setValue(Cell key , String val){

        row.put(key.getName(),val);

    }

}
