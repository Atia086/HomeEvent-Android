package com.yolo.michael.yolo.adapter.question;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yolo.michael.yolo.R;
import com.yolo.michael.yolo.adapter.ClickableRecyclerViewAdapter;
import com.yolo.michael.yolo.model.Question;

import java.util.ArrayList;

/**
 * Created by student5305 on 4/05/16.
 */
public class QuestionAdapter extends ClickableRecyclerViewAdapter<QuestionViewHolder> {

    private LayoutInflater layoutInflater;
    private ArrayList<Question> questionArrayList;

    public QuestionAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.questionArrayList = new ArrayList<>();
    }

    public void refreshQuestions(ArrayList<Question> questions) {
        this.questionArrayList.clear();
        this.questionArrayList.addAll(questions);
        notifyDataSetChanged();
    }

    @Override
    public void bindCurrentViewHolder(QuestionViewHolder holder, int position) {
        Question question = questionArrayList.get(position);
        holder.setQuestion(question);
    }

    @Override
    public QuestionViewHolder instantiateViewHolder(ViewGroup parent, int viewType) {
        View questionRow = layoutInflater.inflate(R.layout.row_question, parent, false);

        QuestionViewHolder questionViewHolder = new QuestionViewHolder(questionRow);
        questionViewHolder.setListener(this);

        return questionViewHolder;
    }

    @Override
    public int getItemCount() {
        return questionArrayList.size();
    }
}
