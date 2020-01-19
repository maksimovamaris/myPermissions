package com.example.mypermissions;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private static final String TAG ="tag!" ;
    private ListView mListView;
    private File rootDir;
    private ArrayList<String> file_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.file_list);
        if (isExternalStorageWritable()) {
            rootDir = Environment.getExternalStorageDirectory();
            file_list = new ArrayList<String>();
            get_files(rootDir.toString());
            ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, file_list);
            mListView.setAdapter(mAdapter);
        }
    }

    public void get_files(String dir_name) {
        File dir=new File(dir_name);
        File[] fList = dir.listFiles() ;
//        = getExternalFilesDirs(dir_name);
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

}
