package com.asterisk.nam.democontentprovider.view;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.asterisk.nam.democontentprovider.adapter.ContactAdapter;
import com.asterisk.nam.democontentprovider.R;
import com.asterisk.nam.democontentprovider.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    private List<Contact> mListContact;
    private ContactAdapter adapContact;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        createArraylist();
        readAllContactsFromPhone();
    }

    public void initViews() {
        recyclerView = findViewById(R.id.rv_main);
    }

    public void createArraylist() {
        mListContact = new ArrayList<>();
        adapContact = new ContactAdapter(getApplicationContext(), mListContact);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapContact);
    }

    public void readAllContactsFromPhone() {
        mListContact.clear();
        mListContact.addAll(getContactInYourPhone());
        adapContact.notifyDataSetChanged();
    }

    public List getContactInYourPhone() {
        List<Contact> mListContact = new ArrayList<>();
        Uri uriContact = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uriContact, null, null, null, null);
        mListContact.clear();
        while (cursor.moveToNext()) {
            String idName = ContactsContract.Contacts.DISPLAY_NAME;
            int colNameIndex = cursor.getColumnIndex(idName);
            String name = cursor.getString(colNameIndex);
            String idPhone = ContactsContract.CommonDataKinds.Phone.NUMBER;
            int colPhoneIndex = cursor.getColumnIndex(idPhone);
            String phone = cursor.getString(colPhoneIndex);
            Contact contact = new Contact(name, phone);
            mListContact.add(contact);
        }
        cursor.close();
        return mListContact;
    }

    public static void goToMain(Context mContext) {
        Intent intent = new Intent(mContext, ContactActivity.class);
        mContext.startActivity(intent);
    }
}
