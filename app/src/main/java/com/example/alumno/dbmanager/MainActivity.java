package com.example.alumno.dbmanager;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnInsert;
    Button btnDelete;
    Button btnSearch;
    Button btnSearAll;

    EditText etInsert;
    EditText etDelete;
    EditText etSearch;

    dataManager db;
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=new dataManager(this);
        lista=(ListView)findViewById(R.id.list1);
        btnInsert=(Button)findViewById(R.id.btn1);
        btnDelete=(Button)findViewById(R.id.btn2);
        btnSearch=(Button)findViewById(R.id.btn3);
        btnSearAll=(Button)findViewById(R.id.button);
        etInsert=(EditText)findViewById(R.id.etNombre);
        etDelete=(EditText)findViewById(R.id.et2);
        etSearch=(EditText)findViewById(R.id.et3);


      btnInsert.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        btnSearAll.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        ArrayList<String> data = new ArrayList<>();
        ArrayAdapter adaptador;
        Cursor c;

        switch (v.getId()){
            case R.id.btn1:
                db.insert(etInsert.getText().toString());
                break;

            case R.id.btn2:
                db.delete(etDelete.getText().toString());
                break;

            case R.id.btn3:
                c = db.search(etSearch.getText().toString());
                if (c.moveToFirst()){
                    do {
                      data.add(c.getString(0));
                    }while(c.moveToNext());
                    adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1, data);
                    lista.setAdapter(adaptador);
                }
                break;

            case R.id.button:
                c = db.selectAll();
                if (c.moveToFirst()){
                    do {
                        data.add(c.getString(0));
                        data.add(c.getString(1));
                    }while(c.moveToNext());
                    adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1, data);
                    lista.setAdapter(adaptador);

                }

                break;

            default:
                break;
        }
    }

}
