package com.yolo.michael.yolo.adapter.question;

import android.view.View;
import android.widget.TextView;

import com.yolo.michael.yolo.R;
import com.yolo.michael.yolo.adapter.ClickableViewHolder;
import com.yolo.michael.yolo.model.Question;

/**
 * Created by student5305 on 4/05/16.
 */
public class QuestionViewHolder extends ClickableViewHolder {

    private TextView contentTextView;

    public QuestionViewHolder(View itemView) {
        super(itemView);
        contentTextView = (TextView) itemView.findViewById(R.id.question_content_text_view_in_own_container);
        itemView.setOnClickListener(this);
    }

    public void setQuestion(Question question) {
        contentTextView.setText(question.getContent());
    }
}
