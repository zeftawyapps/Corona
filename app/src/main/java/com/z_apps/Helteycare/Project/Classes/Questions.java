package com.z_apps.Helteycare.Project.Classes;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.z_apps.Helteycare.R;
import com.z_apps.z_toolslib.ZTools.DB.AddnewFeactuerAdaptor;
import com.z_apps.z_toolslib.ZTools.DB.Cell;
import com.z_apps.z_toolslib.ZTools.DB.Row;
import com.z_apps.z_toolslib.ZTools.DB.Z_DataTable;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class Questions extends Z_DataTable {
    Cell id = new Cell("id");
    Cell img = new Cell("img");
    Cell Question = new Cell("Qustjioi");
    Cell answer = new Cell("ansse");

    ArrayList<Row> rows  = new ArrayList<>();
    public  int Quez[]  = new int[6];

    public Questions(int Rlayout, Context cont) {
        super(Rlayout, cont);

    }

    void add(){
String [] qus = getContext().getResources().getStringArray(R.array.qustions);
        Row r = new Row(getCell());
r.setValues(new String[]{0 + "" , R.drawable.icon1 + "",  qus[0] , 0 + ""});

        Row r1 = new Row(getCell());
        r1.setValues(new String[]{1 + "" , R.drawable.icon2 + "",  qus[1] , 0 + ""});
        Row r2 = new Row(getCell());
        r2.setValues(new String[]{2 + "" , R.drawable.icon3 + "",  qus[2] , 0 + ""});
        Row r3 = new Row(getCell());
        r3.setValues(new String[]{3 + "" , R.drawable.icon4 + "",  qus[3] , 0 + ""});
        Row r4 = new Row(getCell());
        r4.setValues(new String[]{4 + "" , R.drawable.icon5 + "",  qus[4] , 0 + ""});
        Row r5 = new Row(getCell());
        r5.setValues(new String[]{5 + "" , R.drawable.icon6 + "",  qus[5] , 0 + ""});

        rows.add(r); rows.add(r1); rows.add(r2); rows.add(r3); rows.add(r4); rows.add(r5);

    }


    void add(int ans []){


        String [] qus = getContext().getResources().getStringArray(R.array.qustions);
        Row r = new Row(getCell());
        r.setValues(new String[]{0 + "" , R.drawable.icon1 + "",  qus[0] , ans[0] + ""});

        Row r1 = new Row(getCell());
        r1.setValues(new String[]{1 + "" , R.drawable.icon2 + "",  qus[1] , ans[1] + ""});
        Row r2 = new Row(getCell());
        r2.setValues(new String[]{2 + "" , R.drawable.icon3 + "",  qus[2] , ans[2] + ""});
        Row r3 = new Row(getCell());
        r3.setValues(new String[]{3 + "" , R.drawable.icon4 + "",  qus[3] , ans[3] + ""});
        Row r4 = new Row(getCell());
        r4.setValues(new String[]{4 + "" , R.drawable.icon5 + "",  qus[4] , ans[4] + ""});
        Row r5 = new Row(getCell());
        r5.setValues(new String[]{5 + "" , R.drawable.icon6 + "",  qus[5] , ans[5] + ""});

        rows.add(r); rows.add(r1); rows.add(r2); rows.add(r3); rows.add(r4); rows.add(r5);

    }

    int intans;
  public    void load (RecyclerView recyclerView ){

      setCell(new Cell[]{id , img , Question , answer});
      add();
setAddvew(new AddnewFeactuerAdaptor() {
    @Override
    public View Addview(View view, int poss, ArrayList<Row> Table) {
        TextView qtxt  = view.findViewById(R.id.qstiontxt);
        ImageView qimg = view.findViewById(R.id.qimg);
        final TextView andtx = view.findViewById(R.id.anstxt);
        TextView addbtn = view.findViewById(R.id.addbtn);
        TextView decbtn = view.findViewById(R.id.decbtn);

final String and[] =   getContext().getResources().getStringArray(R.array.ansers);

qtxt.setText(Table.get(poss).getvalue(Question));
final int ids = Integer.parseInt( Table.get(poss).getvalue(id));

qimg.setImageResource(Integer.parseInt(Table.get(poss).getvalue(img) + ""));
  intans= Integer.parseInt( Table.get(poss).getvalue(answer) + "");
String ans = and[intans];
        andtx.setText(ans);
Quez[ids] = intans ;

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intans ++ ;
                if (intans>1){intans= 0 ; }
                String ans = and[intans];
                andtx.setText(ans);
                Quez[ids] = intans;

            }
        });
        decbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intans -- ;
                if (intans<0){intans= 1 ; }

                String ans = and[intans];
                andtx.setText(ans);
                Quez[ids] = intans;


            }
        });
        return view;
    }
});

        recyclerView.setLayoutAnimation(GetRecyclerviewAnimationLayout(R.anim.layoutamaition));
        Load_DataAutoloadRecyclerveiw(rows, recyclerView , GetRecyclervewlayoutManagerGride(1) ,getLayoutR() );

    }

    public    void load (RecyclerView recyclerView ,int ans [] ){

        setCell(new Cell[]{id , img , Question , answer});
        add(ans);
        setAddvew(new AddnewFeactuerAdaptor() {
            @Override
            public View Addview(View view, int poss, ArrayList<Row> Table) {
                TextView qtxt  = view.findViewById(R.id.qstiontxt);
                ImageView qimg = view.findViewById(R.id.qimg);
                final TextView andtx = view.findViewById(R.id.anstxt);
                TextView addbtn = view.findViewById(R.id.addbtn);
                TextView decbtn = view.findViewById(R.id.decbtn);

                final String and[] =   getContext().getResources().getStringArray(R.array.ansers);

                qtxt.setText(Table.get(poss).getvalue(Question));
                final int ids = Integer.parseInt( Table.get(poss).getvalue(id));

                qimg.setImageResource(Integer.parseInt(Table.get(poss).getvalue(img) + ""));
                intans= Integer.parseInt( Table.get(poss).getvalue(answer) + "");
                String ans = and[intans];
                andtx.setText(ans);
                Quez[ids] = intans ;

                addbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intans ++ ;
                        if (intans>1){intans= 0 ; }
                        String ans = and[intans];
                        andtx.setText(ans);
                        Quez[ids] = intans;

                    }
                });
                decbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intans -- ;
                        if (intans<0){intans= 1 ; }

                        String ans = and[intans];
                        andtx.setText(ans);
                        Quez[ids] = intans;


                    }
                });
                return view;
            }
        });

        recyclerView.setLayoutAnimation(GetRecyclerviewAnimationLayout(R.anim.layoutamaition));
        Load_DataAutoloadRecyclerveiw(rows, recyclerView , GetRecyclervewlayoutManagerGride(1) ,getLayoutR() );

    }





}
