package com.asterisk.nam.democontentprovider.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asterisk.nam.democontentprovider.R;
import com.asterisk.nam.democontentprovider.model.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {

    private List<Contact> mListContact;
    private Context mContext;

    public ContactAdapter(Context mContext, List<Contact> mListContact) {
        this.mListContact = mListContact;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ContactAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.MyViewHolder viewHolder, int i) {
            viewHolder.bindData(i);
    }

    @Override
    public int getItemCount() {
        return mListContact.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewName, mTextViewPhone;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewName = itemView.findViewById(R.id.textview_name_user);
            mTextViewPhone = itemView.findViewById(R.id.textview_phone_user);
        }

        public void bindData(int i){
            mTextViewName.setText("" + mListContact.get(i).getmName());
            mTextViewPhone.setText("" + mListContact.get(i).getmPhone());
        }
    }
}
