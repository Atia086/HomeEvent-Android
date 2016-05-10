package com.yolo.michael.yolo.adapter.Contact;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.materialize.color.Material;
import com.yolo.michael.yolo.R;
import com.yolo.michael.yolo.adapter.ClickableViewHolder;
import com.yolo.michael.yolo.model.Contact;

/**
 * Created by student5305 on 26/04/16.
 */
public class ContactViewHolder extends ClickableViewHolder {

    private TextView lastNameTextView, firstNameTextView;

    public ContactViewHolder(View itemView) {
        super(itemView);

        lastNameTextView = (TextView) itemView.findViewById(R.id.last_name_text_view_row_contact);
        firstNameTextView = (TextView) itemView.findViewById(R.id.first_name_text_view_row_contact);

        itemView.setOnClickListener(this);
    }

    public void setContact(Contact contact) {
        lastNameTextView.setText(contact.getNom());
        firstNameTextView.setText(contact.getPrenom());
    }
}
