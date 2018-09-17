package data.volive.mydata;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ImageScreen extends AppCompatActivity {
    RecyclerView single;
    List<DataModel> arraylist;
    DatabaseHandler handler;
ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        arraylist = new ArrayList<>();
        handler = new DatabaseHandler(getApplicationContext());
        arraylist = handler.getAllContacts();
        image = findViewById(R.id.image);
        DataModel model=new DataModel("first");
        arraylist.add(model);
        DataModel model1=new DataModel("second");
        arraylist.add(model1);
        DataModel model2=new DataModel("third");
        arraylist.add(model2);
        DataModel model3=new DataModel("four");
        arraylist.add(model3);

//        for (DataModel cn : arraylist) {
//            String log = " Name: " + cn.getName()
//                    + " ,Image: " + cn.getImg();
//                 // Writing Contacts to log
//            Log.d("Result: ", log);
//                //add contacts data in arrayList
//            arraylist.add(cn);
//        }
//        for (int i = 0; i < arraylist.size(); i++) {
//            String text = arraylist.get(i).getName();
//            byte[] img = arraylist.get(i).getImg();
//            Bitmap bmp = BitmapFactory.decodeByteArray(img, 0, img.length);
//
//
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
//            byte imageInByte[] = stream.toByteArray();
//            DataModel model = new DataModel();
//            model.setName(text);
//            model.setImg(imageInByte);
//            arraylist.add(model);
//        }
        single = (RecyclerView) findViewById(R.id.single);

        single.setHasFixedSize(false);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        single.setLayoutManager(mLayoutManager);
        single.setFocusable(false);
        DbAdapter adapter = new DbAdapter(getApplicationContext(), arraylist);
        single.setAdapter(adapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddData.class));
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


}
