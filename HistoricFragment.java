package com.yolo.michael.yolo.fragment.historic;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yolo.michael.yolo.R;
import com.yolo.michael.yolo.adapter.ClickableRecyclerViewAdapter;
import com.yolo.michael.yolo.adapter.question.QuestionAdapter;
import com.yolo.michael.yolo.fragment.base.BaseFragment;
import com.yolo.michael.yolo.model.Question;
import com.yolo.michael.yolo.network.DataBaseManager;
import com.yolo.michael.yolo.network.NetworkApplication;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoricFragment extends BaseFragment implements DataBaseManager.ListenerQuestion {

    private RecyclerView questionsRecyclerView;
    private ArrayList<Question> questions;
    private QuestionAdapter questionAdapter;

    public HistoricFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_historic, container, false);
        setActionBarTitle(R.string.historic_fragment_navigation_title);
        questions = new ArrayList<>();
        questionsRecyclerView = (RecyclerView) rootView.findViewById(R.id.questions_recycler_view_historic_fragment);
        ((NetworkApplication)getActivity().getApplication())
                .getDataBaseManager()
                .getQuestions(this);
        Context context = getContext();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        questionsRecyclerView.setLayoutManager(linearLayoutManager);
        questionAdapter = new QuestionAdapter(context);
        questionsRecyclerView.setAdapter(questionAdapter);
        questionAdapter.refreshQuestions(questions);
        questionAdapter.setListener(new ClickableRecyclerViewAdapter.ClickableRecyclerViewAdapterListener() {
            @Override
            public void onClick(int position) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.historic_activity_main_container, QuestionDetailFragment.newInstance(questions.get(position)))
                        .addToBackStack(null)
                        .commit();
            }
        });
        return rootView;
    }

    @Override
    public void getQuestions(final ArrayList<Question> questions) {
        this.questions.clear();
        this.questions.addAll(questions);
        this.questionAdapter.refreshQuestions(questions);
    }
}
