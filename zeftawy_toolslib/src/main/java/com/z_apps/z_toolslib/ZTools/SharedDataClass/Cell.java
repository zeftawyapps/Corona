package com.z_apps.z_toolslib.ZTools.SharedDataClass;

import android.content.ContentValues;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.z_apps.z_toolslib.ZTools.DB.Inputs;
import com.z_apps.z_toolslib.ZTools.DB.setAndor;

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
                setValue("");
                return;

            }


        }
        if (editText.getInputType() == InputType.TYPE_CLASS_PHONE){
            setValue(s);
        }
        else {
            if (isdouble(s)) {
                if (isint(s)) {

                    setValue(Integer.parseInt(s));
                } else {

                    setValue(Double.parseDouble(s));
                }


            } else {
                setValue(s);
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
