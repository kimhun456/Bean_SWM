package secmem.org.googlemap_hyun;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Geocoder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    Geocoder gc;
    Button map_btn;
    Button address_btn;
    TextView address_txt;
    EditText langitude_edit;
    EditText longitude_edit;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map_btn = (Button)findViewById(R.id.map_btn);
        address_txt = (TextView)findViewById(R.id.address_txt);
        address_btn = (Button)findViewById(R.id.geo_btn);
        langitude_edit = (EditText)findViewById(R.id.lat_edit_txt);
        longitude_edit = (EditText)findViewById(R.id.long_edit_txt);
        linearLayout = (LinearLayout)findViewById(R.id.linear);

        map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                View view = findViewById(R.id.linear);
//                try {
//                    screenshot(view);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                Take_Capture.getInstance().takeScreenshot(linearLayout);



//                Intent intent = new Intent(getBaseContext(),ScreenShotActivity.class);
//                startActivity(intent);
            }
        });

        address_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double latitude =  Double.parseDouble(langitude_edit.getText().toString());
                double longitude =  Double.parseDouble(longitude_edit.getText().toString());
                Address_Returner ad = Address_Returner.getInstance();
                String address = ad.get_all_address(getBaseContext(),latitude,longitude);
                String city =  ad.get_city_name(getBaseContext(), latitude, longitude);
                String gu = ad.get_gu_name(getBaseContext(), latitude, longitude);
                address_txt.setText( address + "  "  +  city+ "  " + gu);
            }
        });


    }


    public Bitmap takeScreenshot() {
        View rootView = (View) findViewById(R.id.linear);
        //View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    public void saveBitmap(Bitmap bitmap) {
        File imagePath = new File(Environment.getExternalStorageDirectory() + "/screenshot.png");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }



    public void screenshot(View view)throws Exception {

        view.setDrawingCacheEnabled(true);

        Bitmap screenshot = view.getDrawingCache();


        try {

            File f = new File(Environment.getExternalStorageDirectory() + "/screenshot.png");

            f.createNewFile();

            OutputStream outStream = new FileOutputStream(f);

            screenshot.compress(Bitmap.CompressFormat.PNG, 100, outStream);

            outStream.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

        view.setDrawingCacheEnabled(false);

    }




}
