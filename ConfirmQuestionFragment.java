package com.yolo.michael.yolo.fragment.question;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.yolo.michael.yolo.R;
import com.yolo.michael.yolo.adapter.ClickableRecyclerViewAdapter;
import com.yolo.michael.yolo.adapter.Contact.ContactAdapter;
import com.yolo.michael.yolo.fragment.base.BaseFragment;
import com.yolo.michael.yolo.model.Question;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmQuestionFragment extends BaseFragment implements View.OnClickListener{

    private static final String ARGUMENTS = "ARGUMENTS";

    private TextView questionContent;
    private RecyclerView selectedContactsRecyclerView;
    private Button confirmationButton;
    private Button cancelButton;
    private ContactAdapter contactAdapter;
    private Question question;
    private ConfirmQuestionListener listener;

    public ConfirmQuestionFragment() {
        // Required empty public constructor
    }
    public interface ConfirmQuestionListener {
        void onSucceed(Question question);
        void onCancel(Question question);
    }

    public static ConfirmQuestionFragment newInstance(Question question, ConfirmQuestionListener listener) {
        ConfirmQuestionFragment confirmQuestionFragment = new ConfirmQuestionFragment();
        confirmQuestionFragment.listener = listener;
        Bundle arguments = new Bundle();
        arguments.putParcelable(ARGUMENTS, question);
        confirmQuestionFragment.setArguments(arguments);
        return confirmQuestionFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_confirm_question, container, false);

        setActionBarTitle(R.string.confirm_question_fragment_navigation_title);

        Context context = getContext();

        Bundle incomingArguments = getArguments();
        question = incomingArguments.getParcelable(ARGUMENTS);

        questionContent = (TextView) rootView.findViewById(R.id.question_content_text_view_confirm_question_fragment);
        questionContent.setText(question.getContent());

        selectedContactsRecyclerView = (RecyclerView) rootView.findViewById(R.id.selected_contacts_recycler_view_confirm_question_fragment);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        selectedContactsRecyclerView.setLayoutManager(linearLayoutManager);
        contactAdapter = new ContactAdapter(context);
        selectedContactsRecyclerView.setAdapter(contactAdapter);
        contactAdapter.refreshContact(question.getContactArrayList());
        contactAdapter.setListener(new ClickableRecyclerViewAdapter.ClickableRecyclerViewAdapterListener() {
            @Override
            public void onClick(int position) {
                question.removeContact(question.getContactArrayList().get(position));
                contactAdapter.refreshContact(question.getContactArrayList());
            }
        });

        cancelButton = (Button) rootView.findViewById(R.id.cancel_button_confirm_question_fragment);
        cancelButton.setOnClickListener(this);
        confirmationButton = (Button) rootView.findViewById(R.id.confirmation_button_confirm_question_fragment);
        confirmationButton.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_button_confirm_question_fragment:
                listener.onCancel(question);
                break;
            case R.id.confirmation_button_confirm_question_fragment:
                listener.onSucceed(question);
                break;
        }
    }

}
