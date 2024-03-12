package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddView extends AppCompatActivity {
    private EditText txtid, txtname, txtphone;
    private Button btnAdd, btnAddImage;
    private ImageView avatar;
    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_view);

        txtid = findViewById(R.id.txtId);
        txtname = findViewById(R.id.txtName);
        txtphone = findViewById(R.id.txtPhone);
        btnAdd = findViewById(R.id.btnAdd);
        avatar =findViewById(R.id.avatar);

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContact();
            }
        });
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            avatar.setImageURI(selectedImageUri);
        }
    }

    private void addContact() {
        int id = Integer.parseInt(txtid.getText().toString());
        String name = txtname.getText().toString();
        String phone = txtphone.getText().toString();

        if (name.isEmpty() || phone.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedImageUri == null) {
            Toast.makeText(getApplicationContext(), "Vui lòng chọn ảnh", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra("Id", id);
        intent.putExtra("Name", name);
        intent.putExtra("Phone", phone);
        intent.putExtra("image", String.valueOf(selectedImageUri));
        setResult(RESULT_OK, intent);
        finish();
    }
}