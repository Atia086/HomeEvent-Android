package com.yolo.michael.yolo.fragment.rappel;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.yolo.michael.yolo.R;
import com.yolo.michael.yolo.fragment.base.BaseFragment;
import com.yolo.michael.yolo.model.Rappel;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddRappelFragment extends BaseFragment {

    private Rappel rappel;
    private Button cancelButton;
    private Button fixDateButton;
    private EditText descriptionEditText;
    private AddRappelListener listener;
    private Calendar calendar = Calendar.getInstance();

    public AddRappelFragment() {
        // Required empty public constructor
    }
    public interface AddRappelListener {
        void onSucceed(Rappel rappel);
        void onCancel();
    }

    public static AddRappelFragment newInstance(AddRappelListener listener) {
        AddRappelFragment addRappelFragment = new AddRappelFragment();
        addRappelFragment.listener = listener;
        return  addRappelFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_rappel, container, false);

        setActionBarTitle(R.string.add_rappel_fragment_navigation_title);

        rappel = new Rappel();

        cancelButton = (Button) rootView.findViewById(R.id.cancel_button_add_rappel_fragment);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCancel();
            }
        });

        fixDateButton = (Button) rootView.findViewById(R.id.add_button_add_rappel_fragment);

        descriptionEditText = (EditText) rootView.findViewById(R.id.recall_description_edit_text_add_rappel_fragment);
        descriptionEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null) {
                    fixDateButton.setEnabled(true);
                } else {
                    fixDateButton.setEnabled(false);
                }
            }
        });

        fixDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rappel.setContent(descriptionEditText.getText().toString());
                new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {
                        new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                rappel.setDate(new GregorianCalendar(year, monthOfYear, dayOfMonth, hourOfDay, minute));
                                listener.onSucceed(rappel);
                            }
                        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)).show();
            }
        });
        return rootView;
    }

}
