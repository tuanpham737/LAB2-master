package com.example.myapplication;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    private ArrayList<Contact> data;
    private Context context;

    public MyAdapter(ArrayList<Contact> data, Context context) {
        this.data = data;
        this.context = context;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = View.inflate(parent.getContext(), R.layout.activity_contact_view2, null);
        } else view = convertView;


        TextView txtTen = view.findViewById(R.id.txtTen);
        TextView txtPhone = view.findViewById(R.id.txtPhone);
        CheckBox checkBox = view.findViewById(R.id.checkbox);
        ImageView image = view.findViewById(R.id.pathImage);

        Contact itemValues = data.get(position);

        txtTen.setText(itemValues.getName());
        txtPhone.setText(itemValues.getPhoneNumber());
        checkBox.setChecked(itemValues.isStatus());

        String imageUri = itemValues.getImage();
        if (imageUri != null && !imageUri.isEmpty()) {
            try {
                Uri uri = Uri.parse(imageUri);
                image.setImageURI(uri);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("MyAdapter", "Error loading image: " + e.getMessage());
            }
        }

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.get(position).setStatus(checkBox.isChecked());
            }
        });

        return view;
    }
}
