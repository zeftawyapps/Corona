package com.z_apps.z_toolslib.ZTools.DB;

import android.content.ContentValues;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class Cell{
    public String name;public Object value; public String Creattyp;
    public String Showvalue; String WhereValue;
     boolean isSelectAsSum; String Caption ;
boolean isNumPositive  ;
int R ;
    public void setR(int r) {
        R = r;
    }

    public int getRinput() {
        return Rinput;
    }

    public void setRinput(int rinput) {
        Rinput = rinput;
    }

    int Rinput ;

    String Errormassege;


    boolean isnullTest ;
    boolean nulltestvalidation;

    public boolean isNulltestvalidation() {
        return nulltestvalidation;
    }

    public void setNulltestvalidation(boolean nulltestvalidation , String Errormsg) {
        this.nulltestvalidation = nulltestvalidation;
        Errormassege = Errormsg;

        if (getInputs()==Inputs.EditText){
            if (editText != null){

                if (isNulltestvalidation()){

                    String s = editText.getText() + "";
                    if (s.equals("")){
                        editText.setError(Errormassege);
                        setErrorfound(true);
                        setValue("");


                    }else {setErrorfound(false);}


                }


            }


        }

    }

    public void setEmailtestvalidation(boolean nulltestvalidation , String Errormsg) {
        this.nulltestvalidation = nulltestvalidation;
        Errormassege = Errormsg;

        if (getInputs()==Inputs.EditText){
            if (editText != null){

                if (isNulltestvalidation()){

                    String s = editText.getText() + "";
                    if (s.equals("")){
                        editText.setError(Errormassege);
                        setErrorfound(true);
                        setValue("");


                    }else {


                        if (! android.util.Patterns.EMAIL_ADDRESS.matcher(s).matches())
                        {
                            editText.setError(Errormassege);
                            setErrorfound(true);


                        }else {setErrorfound(false);}
                    }


                }


            }


        }

    }

    public void setLengthMustGreeterthantestvalidation(int lengvalidation , String Errormsg) {
        this.nulltestvalidation = true;
        Errormassege = Errormsg;

        if (getInputs()==Inputs.EditText){
            if (editText != null){

                if (isNulltestvalidation()){

                    String s = editText.getText() + "";
                    if (s.equals("")){
                        editText.setError(Errormassege);
                        setErrorfound(true);
                        setValue("");


                    }else {
                        if (s.length()<=lengvalidation){ editText.setError(Errormassege);
                            setErrorfound(true);
                        }else { setErrorfound(false);}


                    }


                }


            }


        }

    }

    public void setLengthMustEqualGreeterthantestvalidation(int lengvalidation , String Errormsg) {
        this.nulltestvalidation = true;
        Errormassege = Errormsg;

        if (getInputs()==Inputs.EditText){
            if (editText != null){

                if (isNulltestvalidation()){

                    String s = editText.getText() + "";
                    if (s.equals("")){
                        editText.setError(Errormassege);
                        setErrorfound(true);
                        setValue("");


                    }else {
                        if (s.length()<lengvalidation){ editText.setError(Errormassege);
                            setErrorfound(true);
                            setValue("");
                        }else { setErrorfound(false);}


                    }


                }


            }


        }

    }

    public void setLengthMustLessthantestvalidation(int lengvalidation , String Errormsg) {
        this.nulltestvalidation = true;
        Errormassege = Errormsg;

        if (getInputs()==Inputs.EditText){
            if (editText != null){

                if (isNulltestvalidation()){

                    String s = editText.getText() + "";
                    if (s.equals("")){
                        editText.setError(Errormassege);
                        setErrorfound(true);
                        setValue("");


                    }else {
                        if (s.length()>=lengvalidation){ editText.setError(Errormassege);
                            setErrorfound(true);
                        }else { setErrorfound(false);}


                    }


                }


            }


        }

    }
    public void setLengthMustEquelAndLessthantestvalidation(int lengvalidation , String Errormsg) {
        this.nulltestvalidation = true;
        Errormassege = Errormsg;

        if (getInputs()==Inputs.EditText){
            if (editText != null){

                if (isNulltestvalidation()){

                    String s = editText.getText() + "";
                    if (s.equals("")){
                        editText.setError(Errormassege);
                        setErrorfound(true);
                        setValue("");


                    }else {
                        if (s.length()>lengvalidation){ editText.setError(Errormassege);
                            setErrorfound(true);
                        }else { setErrorfound(false);}


                    }


                }


            }


        }

    }
    public void setLengthMustEquelAndtvalidation(int lengvalidation , String Errormsg) {
        this.nulltestvalidation = true;
        Errormassege = Errormsg;

        if (getInputs()==Inputs.EditText){
            if (editText != null){

                if (isNulltestvalidation()){

                    String s = editText.getText() + "";
                    if (s.equals("")){
                        editText.setError(Errormassege);
                        setErrorfound(true);
                        setValue("");


                    }else {
                        if (s.length()==lengvalidation){ editText.setError(Errormassege);
                            setErrorfound(true);
                        }else { setErrorfound(false);}


                    }


                }


            }


        }

    }


    public boolean isErrorfound() {
        return Errorfound;
    }

    public void setErrorfound(boolean errorfound) {
        Errorfound = errorfound;
    }

    boolean Errorfound;

    View view ;


    public int getR() {
        return R;
    }

    public boolean isNumPositive() {
        return isNumPositive;
    }

    public void setNumPositive(boolean numPositive) {
        isNumPositive = numPositive;
    }

    public String getCaption() {
        if (Caption == ""){return  name; }else  {   return Caption;}

    }

    public void setCaption(String caption) {
        Caption = caption;
    }

    String Selects;
    ContentValues cv = new ContentValues();

    public ContentValues getContantvalue() {
        return cv;
    }

    public String getSelects() {
        if (Selects ==  null){return  name;} else { return Selects;}

    }

    public void setSelects(String selects) {
        Selects = selects;
    }
public String SelectAsStrftime_month(){

    String s = "strftime('%m',"+getName()+")";
    setSelects(s);
    return  s ;
}


    public String SelectAsStrftime_year(){

        String s = "strftime('%Y',"+getName()+")";
        setSelects(s);
        return  s ;
    }

    public String SelectAsStrftime(String datetype){

        String s = "strftime('%"+datetype + "',"+getName()+")";
        setSelects(s);
        return  s ;
    }

    public void setSelectAsSum(boolean selectAsSum) {
        isSelectAsSum = selectAsSum;
    }

    public void setBetween(boolean between) {
        isBetween = between;
    }

    public String selectsum(){

        return " sum("+name+")  As "+ name;
    }

    public String getHavingString() {
        return HavingString;
    }

    public boolean isSelectAsSum() {
        return isSelectAsSum;
    }

    public String getWhereValue() {
        return WhereValue;
    }

    boolean isBetween;

    public String getWherestring() {
        return Wherestring;
    }

    String Wherestring , HavingString  ;
    String AndOR;

    public String getAndOR() {
        return AndOR;
    }

    public ContentValues setvalue(boolean value){

    cv.put(name , value);return  cv;
}

    public ContentValues setvalue(byte value){

        cv.put(name , value);return  cv;
    }
    public ContentValues setvalue(int value){

        cv.put(name , value);return  cv;
    }
    public ContentValues setvalue(long value){

        cv.put(name , value);return  cv;
    }
    public ContentValues setvalue(short value){

        cv.put(name , value);return  cv;
    }
    public ContentValues setvalue(String value){


        cv.put(name , value);return  cv;
    }
    public ContentValues setvalue(double value){

        cv.put(name , value);return  cv;
    }
    public ContentValues setvalue(float value){

        cv.put(name,value);return cv;
    }
    public String getShowvalue() {
        return Showvalue;
    }

    public void setShowvalue(String showvalue) {
        Showvalue = showvalue;
    }

    public Cell(String name) {
        this.name = name;
    }
    public Cell(String name , String Caption) {
        this.name = name;
        this.Caption = Caption;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
void  getAndORstring (setAndor setAndor){

    switch(setAndor){
        case And :
            AndOR = "And";
            break;
        case or:
            AndOR="Or";
            break;
    }

}
boolean isinReport ;

    public boolean isIsinReport() {
        return isinReport;
    }

    public void setIsinReport(boolean isinReport) {
        this.isinReport = isinReport;
    }

    public boolean isBetween() {
        return isBetween;
    }

    public String WhereBetween(setAndor setAndor ,
                               String d1 , String d2 ){
    getAndORstring(setAndor);
isBetween = true ;
    String Where = " date ("+ getName()+") BETWEEN date('"+d1+ "') AND date('"+d2+"')";
return  Where ;


}

  String Have (setAndor setAndor , String opration , String val ){

    getAndORstring(setAndor);
    String w = getName()+" "+opration+" ?";
WhereValue =  val;
    return  w ;
}


   public String Where (setAndor setAndor , String opration , String val ){

        getAndORstring(setAndor);
        String w = getName()+" "+opration+" ?";
        WhereValue =  val;
       Wherestring =  w ;
        return  w ;
    }

    public String WhereDate(setAndor setAndor ,
                            String opration , String d2 ){
        getAndORstring(setAndor);
        isBetween = true ;
        String Where = " date("+getName()+")"+opration+"date('"+d2+"'); ";
        return  Where ;


    }






    EditText editText ;
    public EditText getEditText() {
        return editText;
    }

    public void setEditText(EditText Etxt) {
        this.editText = Etxt;
        setInputs(Inputs.EditText);
        setRinput(Etxt.getId());
        String s = Etxt.getText() + "";
        if (isNulltestvalidation()){
            if (s.equals("")){
                Etxt.setError(Errormassege);
                setErrorfound(true);
                setvalue("");
                return;

            }


        }
        if (editText.getInputType() == InputType.TYPE_CLASS_PHONE){
            setvalue(s);
        }
        else {
            if (isdouble(s)) {
                if (isint(s)) {

                    setvalue(Integer.parseInt(s));
                } else {

                    setvalue(Double.parseDouble(s));
                }


            } else {
                setvalue(s);
            }
        }
    }



    TextView textView ;



    public void setTextView(TextView textView) {
        setInputs(Inputs.Textview);
        setRinput(textView.getId());

        setValue(textView.getText());
    }

    public void setswich(Switch sw) {
        setInputs(Inputs.Switch);
        setRinput(sw.getId());
        setValue(sw.isChecked());
    }

    public void setRate(RatingBar Rate) {
        setInputs(Inputs.Rate);
        setRinput(Rate.getId());
        setValue(Rate.getRating());


    }

    Object [] SpennerArray ;
    boolean spennervaluisid;

    public boolean isSpennervaluisid() {
        return spennervaluisid;
    }

    public Object[] getSpennerArray() {
        return SpennerArray;
    }

    public void setSpinnerStringv(Spinner Rate , Object [] SpennerArrays  ) {
        setInputs(Inputs.Spinner);
        setRinput(Rate.getId());
        setValue(Rate.getSelectedItem());
        SpennerArray = SpennerArrays;
        spennervaluisid = false ;

    }


    public void setSpinnerPossitionv(Spinner Rate) {
        setInputs(Inputs.Spinner);
        setRinput(Rate.getId());
        setValue(Rate.getSelectedItemPosition());
        spennervaluisid = true ;

    }
    boolean isdouble(String s ){
        try{
            Double d = Double.parseDouble(s);
            return  true;}catch (Exception e ){return  false ; }
    }

    boolean isint(String s ){
        try{
            Integer d = Integer.parseInt(s);
            return  true;}catch (Exception e ){return  false ; }
    }

    public boolean isIsnullTest() {
        return isnullTest;
    }

    public void setIsnullTest(boolean isnullTest) {
        this.isnullTest = isnullTest;
    }







      Inputs inputs ;

    public Inputs getInputs() {
        return inputs;
    }

    public void setInputs(Inputs inputs) {
        this.inputs = inputs;
    }





}
