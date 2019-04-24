package com.asterisk.nam.democontentprovider.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asterisk.nam.democontentprovider.R;
import com.asterisk.nam.democontentprovider.model.Sms;

import java.util.List;

public class SmsAdapter extends RecyclerView.Adapter<SmsAdapter.MyViewHolderSms> {

    private List<Sms> mListContact;
    private Context mContext;

    public SmsAdapter(Context mContext, List<Sms> mListContact) {
        this.mListContact = mListContact;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public SmsAdapter.MyViewHolderSms onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_sms, viewGroup, false);
        SmsAdapter.MyViewHolderSms myViewHolder = new SmsAdapter.MyViewHolderSms(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SmsAdapter.MyViewHolderSms viewHolder, int i) {
        viewHolder.bindData(i);
    }

    @Override
    public int getItemCount() {
        return mListContact.size();
    }

    public class MyViewHolderSms extends RecyclerView.ViewHolder {

        private TextView txt_phone;
        private TextView txt_time;
        private TextView txt_body;

        public MyViewHolderSms(@NonNull View itemView) {
            super(itemView);
            txt_phone = itemView.findViewById(R.id.textview_phone_sms_user);
            txt_time = itemView.findViewById(R.id.textview_time_sms_user);
            txt_body = itemView.findViewById(R.id.textview_body_sms_user);
        }

        public void bindData(int i) {
            txt_phone.setText("" + mListContact.get(i).getmPhoneNumber());
            txt_time.setText("" + mListContact.get(i).getmTime());
            txt_body.setText("" + mListContact.get(i).getmBody());
        }
    }
}
