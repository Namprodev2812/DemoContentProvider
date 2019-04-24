package com.asterisk.nam.democontentprovider.view;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.asterisk.nam.democontentprovider.R;
import com.asterisk.nam.democontentprovider.adapter.SmsAdapter;
import com.asterisk.nam.democontentprovider.model.Sms;

import java.util.ArrayList;
import java.util.List;

public class SmsActivity extends AppCompatActivity {

    private List<Sms> mListSms;
    private SmsAdapter mSmsAdapter;
    private RecyclerView mRecyclerView;
    private static final String SMS_ADDRESS = "address";
    private static final String SMS_URI = "content://sms/inbox";
    private static final String SMS_DATE = "date";
    private static final String SMS_BODY = "body";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        initViews();
        createArraylist();
        readAllMessage(getApplicationContext());
    }

    public void initViews() {
        mRecyclerView = findViewById(R.id.rv_sms_main);
    }

    public void createArraylist() {
        mListSms = new ArrayList<>();
        mSmsAdapter = new SmsAdapter(getApplicationContext(), mListSms);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mSmsAdapter);
    }

    public void readAllMessage(Context context) {
        mListSms.clear();
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(Uri.parse(SMS_URI), null, null, null, null);
        int indexPhoneNumber = cursor.getColumnIndex(SMS_ADDRESS);
        int indexTimeStamp = cursor.getColumnIndex(SMS_DATE);
        int indexBody = cursor.getColumnIndex(SMS_BODY);
        if (indexBody < 0 || !cursor.moveToFirst())
            return;
        do {
            String phoneNumber = cursor.getString(indexPhoneNumber);
            String timeStamp = cursor.getString(indexTimeStamp);
            String body = cursor.getString(indexBody);
            mListSms.add(new Sms(phoneNumber, timeStamp, body));
        }
        while (cursor.moveToNext());
        cursor.close();
        mSmsAdapter.notifyDataSetChanged();
    }
}
