package com.yolo.michael.yolo.fragment.Event;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yolo.michael.yolo.R;
import com.yolo.michael.yolo.adapter.ClickableRecyclerViewAdapter;
import com.yolo.michael.yolo.adapter.Contact.ContactAdapter;
import com.yolo.michael.yolo.fragment.base.BaseFragment;
import com.yolo.michael.yolo.model.Contact;
import com.yolo.michael.yolo.model.Event;
import com.yolo.michael.yolo.model.ModelGenerator;
import com.yolo.michael.yolo.network.DataBaseManager;
import com.yolo.michael.yolo.network.NetworkApplication;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class WithWhomFragment extends BaseFragment implements DataBaseManager.ListenerContact {

    private static final String ARGUMENTS = "ARGUMENTS";
    private static final String TAG_NEXT = "TAG_NEXT";

    private Button nextButton;
    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;
    private Context context;
    private ArrayList<Contact> contacts;

    public WithWhomFragment() {
        // Required empty public constructor
    }

    public static WithWhomFragment newInstance(Event event) {
        WithWhomFragment withWhomFragment = new WithWhomFragment();
        Bundle arguments = new Bundle();
        arguments.putParcelable(ARGUMENTS, event);
        withWhomFragment.setArguments(arguments);
        return withWhomFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_with_whom, container, false);

        context = getContext();

        Bundle incomingArguments = getArguments();
        final Event event = incomingArguments.getParcelable(ARGUMENTS);

        setActionBarTitle(R.string.with_whom_title_with_whom_fragment);

        contacts = new ArrayList<>();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.contacts_recycler_view_with_whom_fragment);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        contactAdapter = new ContactAdapter(context);
        recyclerView.setAdapter(contactAdapter);

        ((NetworkApplication)getActivity().getApplication()).getDataBaseManager().getContacts(WithWhomFragment.this);

        contactAdapter.setListener(new ClickableRecyclerViewAdapter.ClickableRecyclerViewAdapterListener() {
            @Override
            public void onClick(int position) {
                Contact currentContact = contacts.get(position);
                if (event.getGuests().contains(currentContact)) {
                    event.removeGuest(currentContact);
                    currentContact.setIsAdded(false);
                } else {
                    event.addGuest(currentContact);
                    currentContact.setIsAdded(true);
                }
                contactAdapter.refreshContact(contacts);
            }
        });

        nextButton = (Button) rootView.findViewById(R.id.next_button_with_whom_fragment);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (event.getTypeEvent().getCode()) {
                    case 0:
                        if (event.getPlace().getTypePlace().getCode() == 1) {
                            getFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.percentage_layout_container_event_creation_activity, EventCreationConfirmationFragment.newInstance(event), TAG_NEXT)
                                    .addToBackStack(null)
                                    .commit();
                        } else {
                            getFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.percentage_layout_container_event_creation_activity, EventCreationConfirmationFragment.newInstance(event), TAG_NEXT)
                                    .addToBackStack(null)
                                    .commit();
                        }
                        break;
                    case 1:
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.percentage_layout_container_event_creation_activity, EventCreationConfirmationFragment.newInstance(event), TAG_NEXT)
                                .addToBackStack(null)
                                .commit();
                        break;
                }
            }
        });

        return rootView;
    }

    @Override
    public void getContacts(ArrayList<Contact> contacts) {
        this.contacts.clear();
        this.contacts.addAll(contacts);
        this.contactAdapter.refreshContact(contacts);
    }
}
