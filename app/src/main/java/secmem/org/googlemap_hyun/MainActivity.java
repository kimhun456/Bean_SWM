package secmem.org.googlemap_hyun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button map_btn;
    Button address_btn;
    Button gallery_btn;
    TextView address_txt;
    EditText langitude_edit;
    EditText longitude_edit;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map_btn = (Button)findViewById(R.id.map_btn);
        gallery_btn = (Button)findViewById(R.id.gallery_view_btn);
        address_txt = (TextView)findViewById(R.id.address_txt);
        address_btn = (Button)findViewById(R.id.geo_btn);
        langitude_edit = (EditText)findViewById(R.id.lat_edit_txt);
        longitude_edit = (EditText)findViewById(R.id.long_edit_txt);
        linearLayout = (LinearLayout)findViewById(R.id.linear);



        address_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double latitude = Double.parseDouble(langitude_edit.getText().toString());
                double longitude = Double.parseDouble(longitude_edit.getText().toString());
                Address_Returner ad = Address_Returner.getInstance();
                String address = ad.get_all_address(getBaseContext(), latitude, longitude);
                String city = ad.get_city_name(getBaseContext(), latitude, longitude);
                String gu = ad.get_gu_name(getBaseContext(), latitude, longitude);
                boolean flag = ad.isKorea(getBaseContext(), latitude, longitude);
                address_txt.setText(address + "  " + city + "  " + gu + flag);
            }
        });


        map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = Take_Capture.getInstance().takeScreenshot(getBaseContext(), linearLayout);

            }
        });


        gallery_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Gallery Button push", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), TableActivity.class);
                startActivity(intent);

            }
        });

    }

}
