package com.example.student.AdapterAndListView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> ar=new ArrayList<String>() ;
    private ArrayAdapter adp;
    final int RequestCode=1;
    private ListView lv;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        adp = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, ar);
        registerForContextMenu(lv);
        lv.setAdapter(adp);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                index=i;
                //如果定return true就不會跳出contextmenu
                //true if the callback consumed the long click, false otherwise
                return false;
            }
        });
    }

    //抓取Contextmenu的layout
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.contextmenu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    //點contextmenu的item時，就刪除listveiw的item
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        ar.remove(index);
        adp.notifyDataSetChanged();
        return super.onContextItemSelected(item);
    }

    //抓取menu的layout
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //option menu item 點擊處理
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.addlist){
            Intent in=new Intent();
            in.setClass(MainActivity.this,Sec.class);
            startActivityForResult(in,RequestCode);
        }
        return super.onOptionsItemSelected(item);

    }
    @Override //取得使用者在Sec.java輸入的資料
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RequestCode){
            if(resultCode==RESULT_OK){
                String getInput=data.getStringExtra("userinput");
                ar.add(getInput);
                adp.notifyDataSetChanged();
            }
        }
    }
}

