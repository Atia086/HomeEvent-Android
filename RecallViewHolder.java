package com.yolo.michael.yolo.adapter.Recall;

import android.view.View;
import android.widget.TextView;

import com.yolo.michael.yolo.R;
import com.yolo.michael.yolo.adapter.ClickableViewHolder;
import com.yolo.michael.yolo.model.Rappel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by student5305 on 2/05/16.
 */
public class RecallViewHolder extends ClickableViewHolder {

    private TextView dateTextView, descriptionTextView;

    private SimpleDateFormat simpleDateFormat;

    public RecallViewHolder(View itemView) {
        super(itemView);

        simpleDateFormat = new SimpleDateFormat("EEEE dd MMMM HH:mm");    //"yyyy-MM-dd HH:mm:ss.SSSZ

        dateTextView = (TextView) itemView.findViewById(R.id.recall_date_text_view_in_container);
        descriptionTextView = (TextView) itemView.findViewById(R.id.recall_description_text_view_in_container);

        itemView.setOnClickListener(this);
    }

    public void setRecall(Rappel rappel) {
        dateTextView.setText(simpleDateFormat.format(rappel.getDate().getTime()));
        //dateTextView.setText(DateFormat.getDateInstance(DateFormat.FULL).format(rappel.getDate().getTime()));
        descriptionTextView.setText(rappel.getContent());
    }

}
