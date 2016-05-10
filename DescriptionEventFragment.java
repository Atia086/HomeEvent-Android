package com.yolo.michael.yolo.fragment.Event;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yolo.michael.yolo.R;
import com.yolo.michael.yolo.fragment.base.BaseFragment;
import com.yolo.michael.yolo.model.Event;
import com.yolo.michael.yolo.model.TypeEvent;

/**
 * A simple {@link Fragment} subclass.
 */
public class DescriptionEventFragment extends BaseFragment {

    private static final String ARGUMENTS = "ARGUMENTS";
    private static final String TAG_NEXT = "TAG_NEXT";
    private EditText eventName;
    private TextView eventDescription;
    private TypeEvent typeEvent;
    private Event event;

    public DescriptionEventFragment() {
        // Required empty public constructor
    }

    public static DescriptionEventFragment newInstance (TypeEvent typeEvent) {
        DescriptionEventFragment descriptionEventFragment = new DescriptionEventFragment();
        Bundle arguments = new Bundle();
        arguments.putParcelable(ARGUMENTS, typeEvent);

        descriptionEventFragment.setArguments(arguments);
        return descriptionEventFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_description_event, container, false);

        Bundle incomingArguments = getArguments();
        typeEvent = incomingArguments.getParcelable(ARGUMENTS);
        event = new Event(typeEvent);

        final Button nextButton = (Button) rootView.findViewById(R.id.event_description_button_next);
        eventDescription = (TextView) rootView.findViewById(R.id.event_description_edit_text);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                event.setName(eventName.getText().toString());
                event.setDescription(eventDescription.getText().toString());

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.percentage_layout_container_event_creation_activity, DateFixingFragment.newInstance(event), TAG_NEXT)
                        .addToBackStack(null)
                        .commit();
            }
        });

        nextButton.setEnabled(false);

        eventName = (EditText) rootView.findViewById(R.id.event_name_edit_text);

        eventName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    nextButton.setEnabled(true);
                } else {
                    nextButton.setEnabled(false);
                }
            }
        });

        setActionBarTitle(R.string.description_navigation_title);

        return rootView;
    }

}
