package com.yolo.michael.yolo.fragment.contact;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yolo.michael.yolo.R;
import com.yolo.michael.yolo.adapter.ClickableRecyclerViewAdapter;
import com.yolo.michael.yolo.adapter.Contact.ContactAdapter;
import com.yolo.michael.yolo.fragment.base.BaseFragment;
import com.yolo.michael.yolo.model.Contact;
import com.yolo.michael.yolo.model.ModelGenerator;
import com.yolo.michael.yolo.network.DataBaseManager;
import com.yolo.michael.yolo.network.NetworkApplication;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends BaseFragment implements DataBaseManager.ListenerContact {

    RecyclerView contactsRecyclerView;
    ArrayList<Contact> contactArrayList;
    private ContactAdapter contactAdapter;

    public ContactFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);

        Context context = getContext();

        setActionBarTitle(R.string.contact_fragment_navigation_title);

        contactArrayList = new ArrayList<>();
        contactsRecyclerView = (RecyclerView) rootView.findViewById(R.id.contacts_recycler_view_contact_fragment);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        contactsRecyclerView.setLayoutManager(linearLayoutManager);
        contactAdapter = new ContactAdapter(context);
        contactsRecyclerView.setAdapter(contactAdapter);
        ((NetworkApplication)getActivity().getApplication()).getDataBaseManager().getContacts(ContactFragment.this);
        contactAdapter.setListener(new ClickableRecyclerViewAdapter.ClickableRecyclerViewAdapterListener() {
            @Override
            public void onClick(int position) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contact_activity_main_container, ContactDetailFragment.newInstance(contactArrayList.get(position)))
                        .addToBackStack(null)
                        .commit();
            }
        });

        return rootView;
    }

    @Override
    public void getContacts(ArrayList<Contact> contacts) {
        this.contactArrayList.clear();
        this.contactArrayList.addAll(contacts);
        this.contactAdapter.refreshContact(contactArrayList);
    }
}
