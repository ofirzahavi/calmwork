package com.calm.android.activity;

import android.R;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.content.Intent;

public class Menu extends ListActivity{

    String classes[] = {"LoginActivity", "CalmActivity", "NewWorkActivity", "WorksListActivity"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(Menu.this, R.layout.simple_list_item_1, classes));

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        super.onListItemClick(l, v, position, id);
        String sChoice = classes[position];
        try{
        Class ourClass = Class.forName("com.calm.android.activity." + sChoice);
        Intent ourIntent = new Intent(Menu.this, ourClass);
        startActivity(ourIntent);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

}
