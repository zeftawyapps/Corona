package com.z_apps.z_toolslib.ZTools.FierbaseDB;

import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.HashMap;

public class Cell {
    String Name ;
    String Errormassege;
    Object Value ;
     boolean PranchTable ;
     boolean  Pranchfield ;
     boolean isID  ;
     boolean isImg ;
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
int R ;
int Rinput ;

    public int getRinput() {
        return Rinput;
    }

    public void setRinput(int rinput) {
        Rinput = rinput;
    }

    public int getR() {
        return R;
    }

    public void setR(int r) {
        R = r;
    }

    public View getView() {
        return view;
    }

    public void setView(   View view ) {


        this.view = view;
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

    public boolean isImg() {
        return isImg;
    }

    public void setImg(boolean img) {
        isImg = img;
    }

    HashMap<String , Object > value ;

    public Cell(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Object getValue() {
        return Value;
    }

    public void setValue(Object value) {
        Value = value;
    }

    public boolean isPranchTable() {
        return PranchTable;
    }

    public void setPranchTable(boolean pranchTable) {

        Pranchfield = false;
        PranchTable = pranchTable;
    }

    public boolean isPranchfield() {
        return Pranchfield;
    }

    public void setPranchfield(boolean pranchfield) {


        PranchTable = false;

        Pranchfield = pranchfield;
    }

    public boolean isID() {
        return isID;
    }

    public void setID(boolean ID) {




        isID = ID;
    }
    Inputs inputs ;

    public Inputs getInputs() {
        return inputs;
    }

    public void setInputs(Inputs inputs) {
        this.inputs = inputs;
    }

}
