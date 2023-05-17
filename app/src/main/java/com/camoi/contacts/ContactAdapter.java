package com.camoi.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.camoi.contacts.Contact;

import java.util.List;


public class ContactAdapter extends ArrayAdapter<Contact> {
    private Context context;
    private int resource;
    private List<Contact> arrContact;

    public ContactAdapter( Context context, int resource,  List<Contact> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource=resource;
        this.arrContact=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_itemlistview,parent,false);
            viewHolder.tvName= (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tvNumber= (TextView) convertView.findViewById(R.id.tv_number);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Contact contact = arrContact.get(position);
        viewHolder.tvName.setText(contact.getName());
        viewHolder.tvNumber.setText(contact.getNumber());
        return convertView;
    }
    public class ViewHolder{
        TextView tvName;
        TextView tvNumber;

    }
}

