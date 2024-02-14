package com.z_apps.z_toolslib.ZTools.FierbaseDB;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewAdabtor extends RecyclerView.Adapter<RecycleViewAdabtor.ViewHolder> {

   private Context mcontext;
     ArrayList<Row> Table;
   int Rlayout ;

   public RecycleViewAdabtor(Context mcontext, ArrayList<Row> commentsList , int Rlayhout) {
       this.mcontext = mcontext;
       this.Table = commentsList;

   Rlayout = Rlayhout ;
   }

   @Override
   public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(Rlayout, parent, false);
       return new RecycleViewAdabtor.ViewHolder(view);
   }

   @Override
   public void onBindViewHolder(ViewHolder holder, int position) {
       holder.bind(position);
   }

   @Override
   public int getItemCount() {
       return Table.size();
   }

   public class ViewHolder extends RecyclerView.ViewHolder {

View view ;
       public ViewHolder(View itemView) {
           super(itemView);
          view = itemView ;
             }

       public void bind(final int position) {
        View Genralview  = itemView  ;
           View conatnv = RoteanView(itemView ,  position , Table);
           if (addvew != null ){
               view =   addvew.Addview(conatnv , position , Table);
           } else {Genralview = conatnv ; }
           Genralview.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Cell [] cells = Table.get(position).getCell();
                   Log.i("pos" , position + "");
                   int i = 0 ; int c = cells.length;
                   while (i<c){
                       cells[i].setValue(Table.get(position).getvalue(cells[i]));
                       i ++ ;
                   }

                 getContainerclick().onclick(v ,   cells);
               }
           });

       }




           View RoteanView(View view , int position , ArrayList<Row> rows){

                View convertView = view;
Cell[] cells = rows.get(position).getCell();
           int i , c  ;
           i = 0 ;  c = cells.length ;
           while (i<c) {
               if (cells [i].getR() != 0) {
                   if (cells[i].isImg()) {
                       ImageView img = (ImageView) convertView.findViewById(cells[i].getR());
                       String imguri = rows.get(position).getvalue(cells[i]) + "";

                       Picasso.with(mcontext).load(imguri).fit().into(img);

                   } else {
                       TextView txt = convertView.findViewById(cells[i].getR());


                       String txts = rows.get(position).getvalue(cells[i]) + "";
if (txts.equals("null")){txts = "" ; }
                       txt.setText(txts);

                   }

               }
               i++;
           }

           return convertView;
       }


   }

    AddnewFeactuerAdaptor addvew ;

    public  void setaddview(AddnewFeactuerAdaptor addvew){
        this.    addvew = addvew;
    }

    Recyclecontainerclecked containerclick ;

    public  Recyclecontainerclecked getContainerclick() {
        return containerclick;
    }

    public void setContainerclick( Recyclecontainerclecked containerclick) {
        this.containerclick = containerclick;
    }
}
