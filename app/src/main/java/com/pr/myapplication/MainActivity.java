package com.pr.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button button,button2;
    String[] items={"English","Русский","Uzbek"};
    boolean[] checkedItems={true,true,false};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        load();

        button=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button.setOnClickListener(view -> {
            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Change App Language");
            builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i==0){
                        setLocale("en");
                        recreate();
                    }
                    if (i==1){
                        setLocale("ru");
                        recreate();
                    }
                    if (i==2){
                        setLocale("uz");
                        recreate();
                    }
                }
            });

         builder.create();
         builder.show();

        });


        button2.setOnClickListener(view -> {
            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Change App Language");
            builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                    Toast.makeText(MainActivity.this, items[i], Toast.LENGTH_SHORT).show();
                }
            });

            builder.create();
            builder.show();

        });


    }

    public void setLocale(String language){
        Locale locale=new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration=new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
        save(language);

    }

    public void save(String text){
        SharedPreferences.Editor editor= (SharedPreferences.Editor) getSharedPreferences("Pr",MODE_PRIVATE).edit();
        editor.putString("pr",text);
        editor.commit();

    }
    public void load(){
        SharedPreferences sharedPreferences=getSharedPreferences("Pr",MODE_PRIVATE);
        String savedtext=sharedPreferences.getString("pr","en");
        setLocale(savedtext);


    }
}