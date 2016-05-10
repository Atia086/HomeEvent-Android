package com.yolo.michael.yolo.fragment.historic;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yolo.michael.yolo.R;
import com.yolo.michael.yolo.adapter.Contact.ContactAdapter;
import com.yolo.michael.yolo.fragment.base.BaseFragment;
import com.yolo.michael.yolo.model.Contact;
import com.yolo.michael.yolo.model.Question;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionDetailFragment extends BaseFragment {

    private static final String ARGUMENTS = "ARGUMENTS";

    private TextView questionContentTextView;
    private RecyclerView questionedContactsRecyclerView;
    private Question question;
    private ArrayList<Contact> questionedContacts;

    public QuestionDetailFragment() {
        // Required empty public constructor
    }

    public static QuestionDetailFragment newInstance(Question question) {
        QuestionDetailFragment questionDetailFragment = new QuestionDetailFragment();
        Bundle arguments = new Bundle();
        arguments.putParcelable(ARGUMENTS, question);
        questionDetailFragment.setArguments(arguments);
        return  questionDetailFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_question_detail, container, false);
        setActionBarTitle(R.string.questio_detail_fragment_navigation_title);

        Bundle incomingArguments = getArguments();
        question = incomingArguments.getParcelable(ARGUMENTS);

        questionContentTextView = (TextView) rootView.findViewById(R.id.question_content_text_view_detail_question_fragment);
        questionContentTextView.setText(question.getContent());

        questionedContactsRecyclerView = (RecyclerView) rootView.findViewById(R.id.selected_contacts_recycler_view_detail_question_fragment);
        questionedContacts = question.getContactArrayList();
        Context context = getContext();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        questionedContactsRecyclerView.setLayoutManager(linearLayoutManager);
        ContactAdapter contactAdapter = new ContactAdapter(context);
        questionedContactsRecyclerView.setAdapter(contactAdapter);
        contactAdapter.refreshContact(questionedContacts);

        return rootView;
    }

}
