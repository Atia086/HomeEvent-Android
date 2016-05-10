package com.yolo.michael.yolo.fragment.contact;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.yolo.michael.yolo.R;
import com.yolo.michael.yolo.fragment.base.BaseFragment;
import com.yolo.michael.yolo.model.Contact;
import com.yolo.michael.yolo.network.NetworkApplication;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactDetailFragment extends BaseFragment {


    private static final String ARGUMENTS = "ARGUMENTS";

    private NetworkImageView profileImage;
    private TextView lastName;
    private TextView firstName;

    private Contact selectedContact;

    public ContactDetailFragment() {
        // Required empty public constructor
    }

    public static ContactDetailFragment newInstance(Contact contact) {
        ContactDetailFragment contactDetailFragment = new ContactDetailFragment();
        Bundle arguments = new Bundle();
        arguments.putParcelable(ARGUMENTS, contact);
        contactDetailFragment.setArguments(arguments);
        return contactDetailFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_contact_detail, container, false);

        setActionBarTitle(R.string.contact_detail_fragment_navigation_title);

        Bundle incomingArguments = getArguments();
        selectedContact = incomingArguments.getParcelable(ARGUMENTS);

        profileImage = (NetworkImageView) rootView.findViewById(R.id.profile_network_image_view_contact_detail_fragment);
        ImageLoader imageLoader = NetworkApplication.getSharedInstance().getVolleyImageLoader();

        if (selectedContact.getImageUrl() != null) {
            profileImage.setBackgroundColor(getResources().getColor(R.color.white));
            profileImage.setImageUrl(selectedContact.getImageUrl(), imageLoader);
        }

        lastName = (TextView) rootView.findViewById(R.id.contact_last_name_text_view_contact_detail_fragment);
        lastName.setText(selectedContact.getNom());

        firstName = (TextView) rootView.findViewById(R.id.contact_first_name_text_view_contact_detail_fragment);
        firstName.setText(selectedContact.getPrenom());

        return rootView;
    }

}
