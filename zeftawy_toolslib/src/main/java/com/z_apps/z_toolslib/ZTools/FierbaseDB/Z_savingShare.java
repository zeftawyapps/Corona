package com.z_apps.z_toolslib.ZTools.FierbaseDB;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by VGA on 22/11/2017.
 */

public class Z_savingShare {
    Context context ;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor ;

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public SharedPreferences.Editor getEditor() {
        return editor;
    }

    public void setEditor(SharedPreferences.Editor editor) {
        this.editor = editor;
    }

    public Z_savingShare(Context context, String name) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(name , context.MODE_PRIVATE);
        editor =sharedPreferences.edit();
    }
}
