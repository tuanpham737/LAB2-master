package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<Contact> listContact;
    MyAdapter contactAdapter;
    ListView listViewContact;

    Button btnXoa,btnThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listContact = new ArrayList<>();
        listContact.add(new Contact(1,"Mot","34567","@drawable/hinhnen",false));
        listContact.add(new Contact(2,"Hai","0987","@drawable/hinhnen",true));
        listContact.add(new Contact(3,"Ba","56789","@drawable/hinhnen",true));

        contactAdapter = new MyAdapter(listContact,this);

        listViewContact = findViewById(R.id.listview);
        listViewContact.setAdapter(contactAdapter);

        //Xoá khi tích vào checkbox
        btnXoa = findViewById(R.id.btnXoa);
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Xác nhận xoá");
                builder.setMessage("Bạn có chắc muốn xoá không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for(int i=0;i<listContact.size();){
                            if(listContact.get(i).isStatus()==true){
                                listContact.remove(i);
                            }
                            else i++;
                        }
                        contactAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Không", null);
                builder.create().show();
            }
        });

        btnThem = findViewById(R.id.btnThem);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddView.class);
                startActivityForResult(intent,100);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {

            int id = data.getIntExtra("Id",0);
            String name = data.getStringExtra("Name");
            String phone = data.getStringExtra("Phone");
            String imageUri = data.getStringExtra("image");
            Contact newcontact = new Contact(id, name, phone, imageUri, false);
            listContact.add(newcontact);
            contactAdapter.notifyDataSetChanged();
        }

    }

}