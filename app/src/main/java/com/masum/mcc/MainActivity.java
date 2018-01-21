package com.masum.mcc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText eName, eAge, eEmail, ePhone;
    DatabaseHelper dbHelper;
    ImageView imgView;
    Bitmap thumbnail=null;


    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_IMAGE_CHOOSE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DatabaseHelper(this);
        initializeAll();
    }

    private void initializeAll() {

        eName = (EditText) findViewById(R.id.editTextName);
        eAge = (EditText) findViewById(R.id.editTextAge);
        eEmail = (EditText) findViewById(R.id.editTextEmail);
        ePhone = (EditText) findViewById(R.id.editTextPhone);
        imgView = (ImageView) findViewById(R.id.imageMy);
        imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);


    }

    public void save(View view) {

        String name = eName.getText().toString();
        String age = eAge.getText().toString();
        String email = eEmail.getText().toString();
        String phone = ePhone.getText().toString();
        if (name.isEmpty() || age.isEmpty() || email.isEmpty() || phone.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please give your all information...",
                    Toast.LENGTH_LONG).show();
        }
        else {


            Employee employee = new Employee(name, age, email, phone,imagetoByte(imgView));

            long inserted = dbHelper.insertEmployee(employee);
            if (inserted >= 0) {
                Toast.makeText(getApplicationContext(), "Data inserted",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Data insertion failed...",
                        Toast.LENGTH_LONG).show();
            }
        }

        eName.setText("");
        eEmail.setText("");
        ePhone.setText("");
        eAge.setText("");

    }


    public void view(View view) {
        Intent i=new Intent(this,ViewActivity.class);
        startActivity(i);
    }


    public void capture(View view) {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicture.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePicture, REQUEST_IMAGE_CAPTURE);

        }
    }

    public void choose(View view) {
//        Intent chooseIntent=new Intent(Intent.ACTION_PICK);
//        chooseIntent.setType("image/*");
//        startActivityForResult(chooseIntent,REQUEST_IMAGE_CHOOSE);
        Toast.makeText(this, "There is a problem", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {



        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK){

                    thumbnail = (Bitmap) data.getExtras().get("data");
                    imgView.setImageBitmap(thumbnail);


        }
        else if (requestCode==REQUEST_IMAGE_CHOOSE  && resultCode==RESULT_OK){

            Uri uri=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(uri);
                thumbnail= BitmapFactory.decodeStream(inputStream);
                imgView.setImageBitmap(thumbnail);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }


    private byte[] imagetoByte( ImageView image){
        Bitmap bitmap=((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray=stream.toByteArray();
        return byteArray;
    }


}