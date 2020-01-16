package com.example.mypermissions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
private EditText text_info;
private Button save_btn;
private Button read_btn;
private String fileName;
private String info;
private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_info=findViewById(R.id.text_info);
        save_btn=findViewById(R.id.save_btn);
        read_btn=findViewById(R.id.read_btn);
        fileName="myFile";
        context=getApplicationContext();
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info=text_info.getText().toString();
                text_info.setText("");
try (FileOutputStream fos= context.openFileOutput(fileName,Context.MODE_PRIVATE))
{
    fos.write(info.getBytes());
} catch (IOException e)
{
   e.printStackTrace();
}
            }
        });

read_btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        FileInputStream fis= null;
        try {
            fis = context.openFileInput(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader inputStreamReader =new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader=new BufferedReader(inputStreamReader))
        {String line = reader.readLine();
        while (line!=null)
        {stringBuilder.append(line).append('\n');
        line=reader.readLine();
           }
            text_info.setText(stringBuilder);

    } catch (IOException e) {
            e.printStackTrace();
        }
    }});


}
}
