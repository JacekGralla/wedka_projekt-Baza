package com.example.luptowski.wedka;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private String url = "http://wedkarze.cba.pl/data.xml/xml";


    MySQLite db=new MySQLite(this);
    Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //db.updateToXml(calendar);

    }


}