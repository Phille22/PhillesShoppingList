package com.example.phillesshoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


//Todo: Spara data (sharedpreferences)

public class MainActivity extends AppCompatActivity {
    private ArrayList<ShoppingListItem> arrayList;
    private RecyclerView mRecyclerView;
    private ShoppingListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Oncreate", "ONCREATE");
        if(savedInstanceState != null){
            Log.d("SAVEDINSTANCE", "DATA LOADED");
            arrayList = savedInstanceState.getParcelableArrayList("Array");
        }else{
            Log.d("ONSAVEDINSTANCESTATE", "NY ARRAY");
            arrayList = new ArrayList<>();
        }
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);
        recycleSetup();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddItemActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("SAVED", "ONSAVEDINSTANCE");
            outState.putParcelableArrayList("Array", arrayList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        recycleSetup();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("ONACTIVITYRESULT", "ONACTIVITYRESULT");
        String item = data.getStringExtra("Item");
        arrayList.add(new ShoppingListItem(item));
        recycleSetup();
        mRecyclerView.getAdapter().notifyItemInserted(arrayList.size());

    }

    public void recycleSetup(){
        mRecyclerView = findViewById(R.id.recyclerView);
        mAdapter = new ShoppingListAdapter(this, arrayList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(mAdapter));
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
