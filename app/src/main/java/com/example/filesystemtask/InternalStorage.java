package com.example.filesystemtask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InternalStorage extends AppCompatActivity {

    EditText editText;
    Button store, get;
    TextView response;

    private String filename = "SampleTextFile.txt";
    private String filepath = "Downloads";
    File myInternalFile;
    String myData = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.internal_storage);
        findIds();
//        clickListeners();
        myInternalFile = new File(getFilesDir(),filename);
    }

    private void findIds() {
        editText = findViewById(R.id.editText);
        store = findViewById(R.id.store);
        get = findViewById(R.id.get);
        response = findViewById(R.id.response);
    }

//    private void clickListeners() {
//
//        store.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    FileOutputStream fileOutputStream = new FileOutputStream(myInternalFile);
//                    fileOutputStream.write(editText.getText().toString().getBytes());
//                    fileOutputStream.close();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                editText.setText("");
//                response.setText("SampleTextFile.txt File is Stored in Internal Storage...");
//            }
//        });
//
//        get.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    FileInputStream fileInputStream = new FileInputStream(myInternalFile);
//                    DataInputStream dataInputStream = new DataInputStream(fileInputStream);
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
//                    String stringLine;
//                    while ((stringLine = bufferedReader.readLine()) != null) {
//                        myData = myData + stringLine;
//                    }
//                    dataInputStream.close();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                editText.setText(myData);x
//                response.setText("SampleTextFile.txt File is Retrived From Internal Storage...");
//            }
//        });
//    }

    public void store(View view) {

        LogFile.v("verbose","data stored");

        String text = editText.getText().toString();
        FileOutputStream fileOutputStream=null;

        try {
            fileOutputStream=openFileOutput(filename,MODE_PRIVATE);
            fileOutputStream.write(text.getBytes());
            editText.getText().clear();
            Toast.makeText(this,"Saved To" + getFilesDir()+ "/" + filename,Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fileOutputStream!= null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void get(View view) {
        FileInputStream fileInputStream=null;
        try {
            fileInputStream=openFileInput(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String text;
            while ((text=bufferedReader.readLine())!= null){
//                myData = myData + text;
                stringBuilder.append(text).append("\n");
            }
            editText.setText(stringBuilder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
          if ( fileInputStream!=null){
              try {
                  fileInputStream.close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
        }
    }
}
