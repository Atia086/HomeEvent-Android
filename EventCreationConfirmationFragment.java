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
import android.widget.TextView;

import com.yolo.michael.yolo.R;
import com.yolo.michael.yolo.adapter.ClickableRecyclerViewAdapter;
import com.yolo.michael.yolo.adapter.Contact.ContactAdapter;
import com.yolo.michael.yolo.fragment.base.BaseFragment;
import com.yolo.michael.yolo.model.Contact;
import com.yolo.michael.yolo.model.Event;
import com.yolo.michael.yolo.network.NetworkApplication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventCreationConfirmationFragment extends BaseFragment implements View.OnClickListener {

    private static final String ARGUMENTS = "ARGUMENTS";
    private TextView nameTextView;
    private TextView descriptionTextView;
    private TextView firstDateTextView;
    private TextView secondDateTextView;
    private TextView thirdDateTextView;
    private RecyclerView guestsRecyclerView;
    private ContactAdapter contactAdapter;
    private Button confirmationButton;
    private TextView placeNameTextView;
    private TextView placeAdressTextView;
    private TextView placeTypeTextView;
    private Context context;

    private ArrayList<Date> dates;
    private ArrayList<Contact> contacts;
    private Event event;

    public EventCreationConfirmationFragment() {
        // Required empty public constructor
    }

    public static EventCreationConfirmationFragment newInstance(Event event) {
        EventCreationConfirmationFragment eventCreationConfirmationFragment = new EventCreationConfirmationFragment();
        Bundle arguments = new Bundle();
        arguments.putParcelable(ARGUMENTS, event);
        eventCreationConfirmationFragment.setArguments(arguments);
        return eventCreationConfirmationFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_event_creation_confirmation, container, false);

        setActionBarTitle(R.string.event_creation_confirmation_fragment_title);

        Bundle incomingArguments = getArguments();
        event = incomingArguments.getParcelable(ARGUMENTS);

        nameTextView = (TextView) rootView.findViewById(R.id.name_text_view_event_creation_confirmation_fragment);
        descriptionTextView = (TextView) rootView.findViewById(R.id.description_text_view_event_creation_confirmation_fragment);

        nameTextView.setText(event.getName());
        descriptionTextView.setText(event.getDescription());

        dates = event.getDates();

        firstDateTextView = (TextView) rootView.findViewById(R.id.first_date_text_view_event_creation_confirmation_fragment);
        secondDateTextView = (TextView) rootView.findViewById(R.id.second_date_text_view_event_creation_confirmation_fragment);
        thirdDateTextView = (TextView) rootView.findViewById(R.id.third_date_text_view_event_creation_confirmation_fragment);

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (dates.size() == 1) {
            firstDateTextView.setText(format.format((event.getDates().get(0).getTime())));
            secondDateTextView.setVisibility(View.GONE);
            thirdDateTextView.setVisibility(View.GONE);
        } else if (dates.size() == 2) {
            firstDateTextView.setText(format.format((event.getDates().get(0).getTime())));
            secondDateTextView.setText(format.format((event.getDates().get(1).getTime())));
            thirdDateTextView.setVisibility(View.GONE);
        } else if (dates.size() == 3 ) {
            firstDateTextView.setText(format.format((event.getDates().get(0).getTime())));
            secondDateTextView.setText(format.format((event.getDates().get(1).getTime())));
            thirdDateTextView.setText(format.format((event.getDates().get(2).getTime())));
        }

        placeNameTextView = (TextView) rootView.findViewById(R.id.place_name_text_view_event_creation_confirmation_fragment);
        placeNameTextView.setText(event.getPlace().getName());

        placeAdressTextView = (TextView) rootView.findViewById(R.id.place_adress_text_view_event_creation_confirmation_fragment);
        placeAdressTextView.setText(event.getPlace().getLocation());

        placeTypeTextView = (TextView) rootView.findViewById(R.id.place_type_text_view_event_creation_confirmation_fragment);
        placeTypeTextView.setText(event.getPlace().getTypePlace().getTextId());

        context = getContext();

        contacts = event.getGuests();
        guestsRecyclerView = (RecyclerView) rootView.findViewById(R.id.guests_recycler_view_event_creation_confirmation_fragment);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        guestsRecyclerView.setLayoutManager(linearLayoutManager);

        contactAdapter = new ContactAdapter(context);
        guestsRecyclerView.setAdapter(contactAdapter);

        contactAdapter.refreshContact(contacts);

        contactAdapter.setListener(new ClickableRecyclerViewAdapter.ClickableRecyclerViewAdapterListener() {
            @Override
            public void onClick(int position) {
                event.removeGuest(event.getGuests().get(position));
                contactAdapter.refreshContact(contacts);
            }
        });

        confirmationButton = (Button) rootView.findViewById(R.id.confirmation_button_event_creation_confirmation_fragment);

        confirmationButton.setOnClickListener(this);

        return rootView;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        ((NetworkApplication)getActivity().getApplication()).getDataBaseManager().addEvent(event);
        getActivity().finish();

    }
}
