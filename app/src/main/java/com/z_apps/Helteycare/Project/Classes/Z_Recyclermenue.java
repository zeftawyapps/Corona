package com.z_apps.Helteycare.Project.Classes;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.z_apps.Helteycare.R;
import com.z_apps.z_toolslib.ZTools.DB.AddnewFeactuerAdaptor;
import com.z_apps.z_toolslib.ZTools.DB.Cell;
import com.z_apps.z_toolslib.ZTools.DB.Row;
import com.z_apps.z_toolslib.ZTools.DB.Z_DataTable;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class Z_Recyclermenue extends Z_DataTable {
com.z_apps.z_toolslib.ZTools.DB.Cell id = new Cell("id") ;
   com.z_apps.z_toolslib.ZTools.DB.Cell Tilte = new Cell("titel");
   com.z_apps.z_toolslib.ZTools.DB.Cell img = new Cell("img" );

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public  void changemneu (int r  ){

        getActivity() .   getMenuInflater().inflate(r ,  menu);


    }


    public  boolean Actionparmenu(int r , Menu menu ){

  getActivity() .   getMenuInflater().inflate(r ,  menu);

    return  true;
}
    Menu menu ;
    public Z_Recyclermenue(int Rlayout, Context cont, RecyclerView recyclerView ) {
        super(Rlayout, cont);
setRecyclerView(recyclerView);

 }

public  boolean loadmenu(  ){


    Tilte.setR(R.id.textitel);
    setCell(new Cell[]{id , Tilte  });
    setAddvew(new AddnewFeactuerAdaptor() {
        @Override
        public View Addview(View view, int poss, ArrayList<Row> Table) {
            ImageView imageView  =  view.findViewById(R.id.imagicon);
             imageView.setImageDrawable(menu.getItem(poss).getIcon());
            return view;
        }
    });

    getRecyclerView().setLayoutAnimation(GetRecyclerviewAnimationLayout(com.z_apps.z_toolslib.R.anim.layoutamaition));
    onloadDataRecyclerview(load() , GetRecyclervewlayoutManagerLinearHorezental(),getLayoutR());

    return  false ;
}

    ArrayList<Row> load(){

int size = menu.size() ;


        ArrayList<Row>        recRowChoes = new ArrayList<Row>();

int i = 0 ;
while (i<size) {
    Row r = new Row(new Cell[]{id, Tilte, img});
    r.setValue(Tilte, menu.getItem(i).getTitle() + "");
    r.setValue(img, menu.getItem(i).getIcon()+ "" );
    r.setValue(id, menu.getItem(i).getItemId() + "");
    recRowChoes.add(r);

i ++ ;
}



        return recRowChoes ;

    }
}
