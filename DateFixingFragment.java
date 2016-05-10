package com.yolo.michael.yolo.fragment.Event;


import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.yolo.michael.yolo.R;
import com.yolo.michael.yolo.fragment.base.BaseFragment;
import com.yolo.michael.yolo.model.Event;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DateFixingFragment extends BaseFragment implements View.OnClickListener {

    private static final String ARGUMENTS = "ARGUMENTS";
    private static final String TAG_NEXT = "TAG_NEXT";

    private Calendar calendar = Calendar.getInstance();
    private TextView firstAddDateTextView;
    private TextView secondAddDateTextView;
    private TextView thirdAddDateTextView;
    private Button firstAddDateButton;
    private Button secondAddDateButton;
    private Button thirdAddDateButton;
    private GregorianCalendar actualTime;
    private String fromActualTime;
    private boolean isFirstDateFixed = false;
    private boolean isSecondDateFixed = false;
    private boolean isThirdDateFixed = false;
    private Button nextButton;
    private GregorianCalendar firstDate;
    private GregorianCalendar secondDate;
    private GregorianCalendar thirdDate;
    private Event event;

    public DateFixingFragment() {
        // Required empty public constructor
    }

    public static DateFixingFragment newInstance(Event event) {
        DateFixingFragment datefixingFragment = new DateFixingFragment();
        Bundle arguments = new Bundle();

        arguments.putParcelable(ARGUMENTS, event);

        datefixingFragment.setArguments(arguments);
        return datefixingFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_date_fixing, container, false);

        Switch fixedDateSwitch = (Switch) rootView.findViewById(R.id.fixed_date_switch_when_fragment);

        firstAddDateTextView = (TextView) rootView.findViewById(R.id.add_date_text_view_1_when_fragment);
        firstAddDateButton = (Button) rootView.findViewById(R.id.add_date_button_1_when_fragment);
        secondAddDateTextView = (TextView) rootView.findViewById(R.id.add_date_text_view_2_when_fragment);
        secondAddDateButton = (Button) rootView.findViewById(R.id.add_date_button_2_when_fragment);
        thirdAddDateTextView = (TextView) rootView.findViewById(R.id.add_date_text_view_3_when_fragment);
        thirdAddDateButton = (Button) rootView.findViewById(R.id.add_date_button_3_when_fragment);

        firstAddDateButton.setOnClickListener(this);
        secondAddDateButton.setOnClickListener(this);
        thirdAddDateButton.setOnClickListener(this);

        fixedDateSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    secondAddDateTextView.setVisibility(View.INVISIBLE);
                    secondAddDateTextView.setText(R.string.add_date);
                    switchIcon(secondAddDateButton, isChecked);
                    secondAddDateButton.setVisibility(View.INVISIBLE);
                    secondDate = null;
                    thirdAddDateTextView.setVisibility(View.INVISIBLE);
                    thirdAddDateTextView.setText(R.string.add_date);
                    switchIcon(thirdAddDateButton, isChecked);
                    thirdAddDateButton.setVisibility(View.INVISIBLE);
                    thirdDate = null;
                } else {
                    secondAddDateTextView.setVisibility(View.VISIBLE);
                    secondAddDateButton.setVisibility(View.VISIBLE);
                    thirdAddDateTextView.setVisibility(View.VISIBLE);
                    thirdAddDateButton.setVisibility(View.VISIBLE);
                }
            }
        });

        setActionBarTitle(R.string.date_fixing_navigation_title);

        Bundle incomingArguments = getArguments();
        event = incomingArguments.getParcelable(ARGUMENTS);

        nextButton = (Button) rootView.findViewById(R.id.next_button_when_fragment);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event.addDate(firstDate.getTime());
                if (null!=secondDate) {
                    event.addDate(secondDate.getTime());
                }
                if (null!=thirdDate) {
                    event.addDate(thirdDate.getTime());
                }
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.percentage_layout_container_event_creation_activity, WhereFragment.newInstance(event), TAG_NEXT)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_date_button_1_when_fragment:
                displayingAndRemovingDates(isFirstDateFixed, firstAddDateButton, firstAddDateTextView);
                isFirstDateFixed = !isFirstDateFixed;
                if (isFirstDateFixed == false) {
                    nextButton.setEnabled(false);
                }
                break;
            case R.id.add_date_button_2_when_fragment:
                displayingAndRemovingDates(isSecondDateFixed, secondAddDateButton, secondAddDateTextView);
                isSecondDateFixed = !isSecondDateFixed;
                break;
            case R.id.add_date_button_3_when_fragment:
                displayingAndRemovingDates(isThirdDateFixed, thirdAddDateButton, thirdAddDateTextView);
                isThirdDateFixed = !isThirdDateFixed;
                break;
        }
    }

    private void displayADateAndTimeDialog(final TextView textView, final Button button, final boolean isCrossed) {
        new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {
                actualTime = new GregorianCalendar(year, monthOfYear, dayOfMonth, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE));
                fromActualTime = DateFormat.getDateInstance(DateFormat.FULL).format(actualTime.getTime());
                new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        fromActualTime = fromActualTime + " - " +  String.format("%02d", hourOfDay) + ":" +  String.format("%02d", minute);
                        textView.setText(fromActualTime);
                        switchIcon(button, isCrossed);
                        switch (button.getId()) {
                            case R.id.add_date_button_1_when_fragment:
                                firstDate = new GregorianCalendar(year, monthOfYear, dayOfMonth, hourOfDay, minute);
                                nextButton.setEnabled(true);
                                break;
                            case R.id.add_date_button_2_when_fragment:
                                secondDate = new GregorianCalendar(year, monthOfYear, dayOfMonth, hourOfDay, minute);
                                break;
                            case R.id.add_date_button_3_when_fragment:
                                thirdDate = new GregorianCalendar(year, monthOfYear, dayOfMonth, hourOfDay, minute);
                                break;
                        }
                    }
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)).show();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void switchIcon (Button button, boolean isCrossed) {
        if (!isCrossed){
            button.setBackgroundResource(android.R.drawable.ic_delete);
        } else {
            button.setBackgroundResource(android.R.drawable.ic_input_add);
        }
    }

    private void displayingAndRemovingDates (boolean bool, Button button, TextView textView) {
        if (bool) {
            switchIcon(button, bool);
            textView.setText(R.string.add_date);
            switch (button.getId()) {
                case R.id.add_date_button_1_when_fragment:
                    firstDate = null;
                    break;
                case R.id.add_date_button_2_when_fragment:
                    secondDate = null;
                    break;
                case R.id.add_date_button_3_when_fragment:
                    thirdDate = null;
                    break;
            }
        } else {
            displayADateAndTimeDialog(textView, button, bool);
        }
    }

}
