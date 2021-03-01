package com.example.myapplication;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_READ_CONTACTS = 1;
    private static boolean READ_CONTACTS_GRANTED = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int hasReadContactPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        Log.e("MSG",String.valueOf(hasReadContactPermission));
        Log.e("MSG",String.valueOf(PackageManager.PERMISSION_GRANTED));
        if(hasReadContactPermission == PackageManager.PERMISSION_GRANTED){
            READ_CONTACTS_GRANTED = true;

        }
        else{
            Log.e("MSG","BEFORE REQ");
            try {
              
            }catch (Exception ex){
                Log.e(ex.getClass().getName(),ex.getMessage());
            }

            Log.e("MSG","AFTER REQ");
        }

        if(READ_CONTACTS_GRANTED){
            readContacts();
        }
    }
//    @Override
//    public void onRequestPermissionResult(int requestCode, String[] permission, int[] grantResults){
//        if(requestCode == REQUEST_CODE_READ_CONTACTS){
//            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                READ_CONTACTS_GRANTED = true;
//            }
//        }
//        if(READ_CONTACTS_GRANTED){
//            readContacts();
//        }
//        else
//        {
//            Toast.makeText(this, "Требует разрешения на чтение контактов", Toast.LENGTH_SHORT).show();
//        }
//    }

    private void readContacts() {
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null,null,null, null);
        ArrayList<String> contacts = new ArrayList<>();

        if(cursor != null){
            while (cursor.moveToNext()){
                String contact = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
                contacts.add(contact);
            }
            cursor.close();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, contacts);
        ListView contactList =  (ListView)findViewById(R.id.viiiiiew);
        contactList.setAdapter(adapter);
    }
}