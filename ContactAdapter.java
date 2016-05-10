package com.yolo.michael.yolo.adapter.Contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yolo.michael.yolo.R;
import com.yolo.michael.yolo.adapter.ClickableRecyclerViewAdapter;
import com.yolo.michael.yolo.model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student5305 on 26/04/16.
 */
public class ContactAdapter extends ClickableRecyclerViewAdapter<ContactViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Contact> contactList;
    private Context context;

    public ContactAdapter(Context context) {
        this.layoutInflater = layoutInflater.from(context);
        this.contactList = new ArrayList<>();
        this.context = context;
    }

    public void refreshContact(ArrayList<Contact> contacts) {
        this.contactList.clear();
        this.contactList.addAll(contacts);
        this.notifyDataSetChanged();
    }

    @Override
    public void bindCurrentViewHolder(ContactViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        if (contact.isAdded()) {
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.lightGrey));
        } else {
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.white));
        }
        holder.setContact(contact);
    }

    @Override
    public ContactViewHolder instantiateViewHolder(ViewGroup parent, int viewType) {
        View rowContact = layoutInflater.inflate(R.layout.row_contact, parent, false);

        ContactViewHolder contactViewHolder = new ContactViewHolder(rowContact);
        contactViewHolder.setListener(this);

        return contactViewHolder;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
}
