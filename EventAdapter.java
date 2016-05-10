package com.yolo.michael.yolo.adapter.Event;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yolo.michael.yolo.R;
import com.yolo.michael.yolo.adapter.ClickableRecyclerViewAdapter;
import com.yolo.michael.yolo.model.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student5310 on 20/04/16.
 */
public class EventAdapter extends ClickableRecyclerViewAdapter<EventViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Event> eventList;

    public EventAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.eventList = new ArrayList<>();
    }

    public void refreshEvent(ArrayList<Event> events){
        this.eventList.clear();
        this.eventList.addAll(events);
        this.notifyDataSetChanged();
    }
    public void clear(){
        this.eventList.clear();
        this.notifyDataSetChanged();
    }

    @Override
    public void bindCurrentViewHolder(EventViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.setEvent(event);
    }

    @Override
    public EventViewHolder instantiateViewHolder(ViewGroup parent, int viewType) {
        View rowEvent = layoutInflater.inflate(R.layout.row_event, parent, false);

        EventViewHolder eventViewHolder = new EventViewHolder(rowEvent);
        eventViewHolder.setListener(this);

        return eventViewHolder;
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
