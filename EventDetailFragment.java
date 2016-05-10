package com.yolo.michael.yolo.fragment.Event;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yolo.michael.yolo.R;
import com.yolo.michael.yolo.adapter.Contact.ContactAdapter;
import com.yolo.michael.yolo.fragment.base.BaseFragment;
import com.yolo.michael.yolo.model.Contact;
import com.yolo.michael.yolo.model.Event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventDetailFragment extends BaseFragment {


    private static final String ARGUMENTS = "arguments";
    private Event event;
    private TextView nameTextView;
    private TextView descriptionTextView;
    private ArrayList<Date> dates;
    private TextView firstDateTextView;
    private TextView secondDateTextView;
    private TextView thirdDateTextView;
    private TextView placeNameTextView;
    private TextView placeAdressTextView;
    private TextView placeTypeTextView;
    private ArrayList<Contact> contacts;
    private Context context;
    private RecyclerView guestsRecyclerView;
    private ContactAdapter contactAdapter;

    public EventDetailFragment() {
        // Required empty public constructor
    }
    public static EventDetailFragment newInstance(Event event) {
        EventDetailFragment eventDetailFragment = new EventDetailFragment();
        Bundle arguments = new Bundle();
        arguments.putParcelable(ARGUMENTS, event);
        eventDetailFragment.setArguments(arguments);
        return eventDetailFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_event_detail, container, false);

        setActionBarTitle(R.string.event_detail);

        Bundle incomingArguments = getArguments();
        event = incomingArguments.getParcelable(ARGUMENTS);

        nameTextView = (TextView) rootView.findViewById(R.id.name_text_view_event_detail_fragment);
        descriptionTextView = (TextView) rootView.findViewById(R.id.description_text_view_event_detail_fragment);

        nameTextView.setText(event.getName());
        descriptionTextView.setText(event.getDescription());

        dates = event.getDates();

        firstDateTextView = (TextView) rootView.findViewById(R.id.first_date_text_view_event_detail_fragment);
        secondDateTextView = (TextView) rootView.findViewById(R.id.second_date_text_view_event_detail_fragment);
        thirdDateTextView = (TextView) rootView.findViewById(R.id.third_date_text_view_event_detail_fragment);

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (dates.size() == 1) {
            firstDateTextView.setText(format.format((event.getDates().get(0).getTime())));
            secondDateTextView.setVisibility(View.GONE);
            thirdDateTextView.setVisibility(View.GONE);
        } else {
            firstDateTextView.setText(format.format((event.getDates().get(0).getTime())));
            secondDateTextView.setText(format.format((event.getDates().get(1).getTime())));
            thirdDateTextView.setText(format.format((event.getDates().get(2).getTime())));
        }

        placeNameTextView = (TextView) rootView.findViewById(R.id.place_name_text_view_event_detail_fragment);
        placeNameTextView.setText(event.getPlace().getName());

        placeAdressTextView = (TextView) rootView.findViewById(R.id.place_adress_text_view_event_detail_fragment);
        placeAdressTextView.setText(event.getPlace().getLocation());

        placeTypeTextView = (TextView) rootView.findViewById(R.id.place_type_text_view_event_detail_fragment);
        placeTypeTextView.setText(event.getPlace().getTypePlace().getTextId());

        context = getContext();

        contacts = event.getGuests();
        guestsRecyclerView = (RecyclerView) rootView.findViewById(R.id.guests_recycler_view_event_detail_fragment);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        guestsRecyclerView.setLayoutManager(linearLayoutManager);

        contactAdapter = new ContactAdapter(context);
        guestsRecyclerView.setAdapter(contactAdapter);

        contactAdapter.refreshContact(contacts);




        return rootView;
    }

}
