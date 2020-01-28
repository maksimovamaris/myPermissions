package com.example.mypermissions;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private static final String TAG ="tag!" ;
    private static final int REQUEST_PERMISSION_RESULT = 1;
    private ListView mListView;
    private File rootDir;
    private ArrayList<String> file_list;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        for(int i=0;i<1&&!check_perm(Manifest.permission.WRITE_EXTERNAL_STORAGE);i++)
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, REQUEST_PERMISSION_RESULT);
if (check_perm(Manifest.permission.WRITE_EXTERNAL_STORAGE))
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.file_list);
        if (isExternalStorageWritable()) {
            rootDir = Environment.getExternalStorageDirectory();
            file_list = new ArrayList<String>();

            get_files(rootDir.toString());
            ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, file_list);
            mListView.setAdapter(mAdapter);

    }}
    else
{
        finish();
    System.exit(0);}
    }

    public void get_files(String dir_name) {
        File dir=new File(dir_name);
        File[] fList = dir.listFiles() ;
//        = getExternalFilesDirs(dir_name);пше
//                ContextCompat.getExternalFilesDirs(this, null);
//        File[] fList=dir.listFiles();
//        File fileNew = Environment.getExternalStorageDirectory();
        if (fList != null) {
            for (File file : fList)
            {
                if (file.isFile())
                {
                    file_list.add(file.getName().toString());
                } else if (file.isDirectory())
                {
                    get_files(file.getAbsolutePath());
                }
            }
        } else {
            file_list.add("no files");
        }
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
    private boolean check_perm(String perm) {

       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, perm) ==
                    PackageManager.PERMISSION_GRANTED) {
                return  true;
            } else
            {
                return  false;
            }
        }
        else
        {
            return true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
