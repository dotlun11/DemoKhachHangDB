package com.example.dotlun.demokhachhangdb;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Karaoke extends AppCompatActivity {
    final String DATABASE_NAME = "RestaurantDB.sqlite";
    EditText edtten,edtphone,edtemail,edtnoidung;
    Button btnAdd;
    ViewFlipper viewFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karaoke);
        if (getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        Anhxa();
        ActionViewFliper();
        addControls();
        addEvents();
    }
    private void addControls() {

        btnAdd = (Button) findViewById(R.id.btnAdd);
        // btnHuy = (Button) findViewById(R.id.btnHuy);
        edtten = (EditText) findViewById(R.id.edtTen);
        edtphone = (EditText) findViewById(R.id.edtPhone);
        edtemail = (EditText) findViewById(R.id.edtEmail);
        edtnoidung = (EditText) findViewById(R.id.edtNoidung);

    }

    private void addEvents(){


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
      /*  btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });*/
    }

    private void insert(){
        String ten = edtten.getText().toString();
        String sdt = edtphone.getText().toString();
        String email = edtemail.getText().toString();
        String noidung = edtnoidung.getText().toString();

        ContentValues contentValues = new ContentValues();
        contentValues.put("Ten", ten);
        contentValues.put("SDT", sdt);
        contentValues.put("Email", email);
        contentValues.put("Noidung", noidung);



        SQLiteDatabase database = Database.initDatabase(this, "RestaurantDB.sqlite");
        database.insert("KhachHang",null,contentValues);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    private void cancel(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private byte[] getByteArrayFromImageView(ImageView imgv){

        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    private void ActionViewFliper(){
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("http://nks.vncloud.webstarterz.com/slide/bg-slide-1.jpg");
        mangquangcao.add("http://nks.vncloud.webstarterz.com/slide/bg-slide-2.jpg");
        mangquangcao.add("http://nks.vncloud.webstarterz.com/slide/bg-slide-3.jpg");
        for (int i = 0; i< mangquangcao.size(); i ++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView );
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }
    public void Anhxa(){
        // toolbar = (Toolbar)findViewById(R.id.toolbarMain);
        viewFlipper = (ViewFlipper) findViewById(R.id.Viewfliper);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
