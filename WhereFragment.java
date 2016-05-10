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
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yolo.michael.yolo.R;
import com.yolo.michael.yolo.fragment.base.BaseFragment;
import com.yolo.michael.yolo.model.Event;
import com.yolo.michael.yolo.model.Place;
import com.yolo.michael.yolo.model.TypePlace;

/**
 * A simple {@link Fragment} subclass.
 */
public class WhereFragment extends BaseFragment {

    private static final String ARGUMENTS = "ARGUMENTS";

    private Place place;
    private Button nextButton;
    private EditText namePlaceEditText;
    private EditText adressPlaceEditText;
    private RadioGroup typePlaceRadioGroup;
    private boolean isTextSet = false;
    private boolean isRadioGroupChecked = false;
    private RadioButton restoRadioButton;

    public WhereFragment() {
        // Required empty public constructor
    }

    public static WhereFragment newInstance(Event event) {
        WhereFragment whereFragment = new WhereFragment();
        Bundle arguments = new Bundle();
        arguments.putParcelable(ARGUMENTS, event);
        whereFragment.setArguments(arguments);
        return whereFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_where, container, false);

        setActionBarTitle(R.string.where_title);

        place = new Place();

        Bundle incomingArguments = getArguments();
        final Event event = incomingArguments.getParcelable(ARGUMENTS);

        restoRadioButton = (RadioButton) rootView.findViewById(R.id.restaurant_radio_button_where_fragment);
        if (event.getTypeEvent().getCode() == 1) {
            restoRadioButton.setText(R.string.disco);
        }

        namePlaceEditText = (EditText) rootView.findViewById(R.id.encode_name_place_edit_text_where_fragment);
        namePlaceEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String sString = s.toString();
                if (!sString.isEmpty()) {
                    isTextSet = true;
                    place.setName(sString);
                } else {
                    isTextSet = false;
                }
                if (isNextAvailable(isTextSet, isRadioGroupChecked)) {
                    nextButton.setEnabled(true);
                } else {
                    nextButton.setEnabled(false);
                }
            }
        });

        adressPlaceEditText = (EditText) rootView.findViewById(R.id.encode_adress_place_edit_text_where_fragment);
        adressPlaceEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                place.setLocation(s.toString());
            }
        });

        typePlaceRadioGroup = (RadioGroup) rootView.findViewById(R.id.places_radio_group_where_fragment);
        typePlaceRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.house_radio_button_where_fragment:
                        place.setTypePlace(TypePlace.HOUSE);
                        break;
                    case R.id.restaurant_radio_button_where_fragment:
                        if (event.getTypeEvent().getCode() == 1) {
                            place.setTypePlace(TypePlace.DISCO);
                        } else {
                            place.setTypePlace(TypePlace.RESTAURANT);
                        }
                        break;
                    case R.id.friends_place_radio_button_where_fragment:
                        place.setTypePlace(TypePlace.FRIENDSPLACE);
                        break;
                }
                isRadioGroupChecked = true;
                if (isNextAvailable(isTextSet, isRadioGroupChecked)) {
                    nextButton.setEnabled(true);
                } else {
                    nextButton.setEnabled(false);
                }
            }
        });

        nextButton = (Button) rootView.findViewById(R.id.next_button_where_fragment);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event.setPlace(place);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.percentage_layout_container_event_creation_activity, WithWhomFragment.newInstance(event))
                        .addToBackStack(null)
                        .commit();
            }
        });

        return rootView;
    }

    private boolean isNextAvailable (boolean text, boolean radioGroup) {
        if (text && radioGroup) {
            return true;
        } else {
            return false;
        }
    }

}
