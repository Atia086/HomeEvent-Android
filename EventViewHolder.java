package com.yolo.michael.yolo.adapter.Event;

import android.view.View;
import android.widget.TextView;

import com.yolo.michael.yolo.R;
import com.yolo.michael.yolo.adapter.ClickableViewHolder;
import com.yolo.michael.yolo.model.Event;
import com.yolo.michael.yolo.model.TypeEvent;

import static com.yolo.michael.yolo.model.TypeEvent.*;

/**
 * Created by student5310 on 20/04/16.
 */
public class EventViewHolder extends ClickableViewHolder {

    private Event event;
    private TextView nameEventTextview,typeEventTextView;


    public EventViewHolder(View itemView) {
        super(itemView);

        nameEventTextview = (TextView) itemView.findViewById(R.id.name_event_textview_row_event_calendar_activity);
        typeEventTextView = (TextView) itemView.findViewById(R.id.type_event__textview_row_event_calendar_activity);

        itemView.setOnClickListener(this);


    }

    public void setEvent(Event event) {
        this.event = event;
        nameEventTextview.setText(event.getName());
        typeEventTextView.setText(event.getTypeEvent().getTextId());
    }
}
