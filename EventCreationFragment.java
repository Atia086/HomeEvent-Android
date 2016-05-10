package com.yolo.michael.yolo.fragment.Event;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yolo.michael.yolo.R;
import com.yolo.michael.yolo.fragment.base.BaseFragment;
import com.yolo.michael.yolo.model.TypeEvent;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventCreationFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG_MEAL = "TAG_MEAL";
    private static final String TAG_HANGOUT = "TAG_HANGOUT";

    public EventCreationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_event_creation, container, false);

        Button eventButton = (Button) rootView.findViewById(R.id.meal_button_event_creation_activity);
        Button hangoutButton = (Button) rootView.findViewById(R.id.hangout_button_event_creation_activity);

        eventButton.setOnClickListener(this);
        hangoutButton.setOnClickListener(this);

        setActionBarTitle(R.string.event_choice_navigation_title);



        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.meal_button_event_creation_activity:
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.percentage_layout_container_event_creation_activity, DescriptionEventFragment.newInstance(TypeEvent.REPAS), TAG_MEAL)
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.hangout_button_event_creation_activity:
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.percentage_layout_container_event_creation_activity, DescriptionEventFragment.newInstance(TypeEvent.SOIREE), TAG_HANGOUT)
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }
}
