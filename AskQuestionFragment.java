package com.yolo.michael.yolo.fragment.question;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.yolo.michael.yolo.R;
import com.yolo.michael.yolo.adapter.ClickableRecyclerViewAdapter;
import com.yolo.michael.yolo.adapter.Contact.ContactAdapter;
import com.yolo.michael.yolo.fragment.base.BaseFragment;
import com.yolo.michael.yolo.model.Contact;
import com.yolo.michael.yolo.model.ModelGenerator;
import com.yolo.michael.yolo.model.Question;
import com.yolo.michael.yolo.network.DataBaseManager;
import com.yolo.michael.yolo.network.NetworkApplication;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AskQuestionFragment extends BaseFragment implements ConfirmQuestionFragment.ConfirmQuestionListener, DataBaseManager.ListenerContact {

    private static final String ARGUMENTS = "ARGUMENTS";
    private static final String TAG_NEXT = "TAG_NEXT";

    private EditText questionEditText;
    private RecyclerView contactsRecyclerView;
    private Button nextButton;
    private boolean questionContentFilled = false;
    private boolean atLeastOneContact = false;
    private ArrayList<Contact> allContacts;
    private Question question;
    
    private ContactAdapter contactAdapter;
    private ArrayList<Question> questions;

    public AskQuestionFragment() {
        // Required empty public constructor
    }

    public static AskQuestionFragment newInstance() {
        AskQuestionFragment askQuestionFragment = new AskQuestionFragment();
        return askQuestionFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ask_question, container, false);

        question = new Question();
        questions = new ArrayList<>();

        setActionBarTitle(R.string.ask_question_fragment_navigation_title);

        Context context = getContext();

        questionEditText = (EditText) rootView.findViewById(R.id.ask_question_edit_text_ask_question_fragment);
        questionEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String sString = s.toString();
                if (sString.length() > 0) {
                    question.setContent(sString);
                    questionContentFilled = true;
                } else {
                    questionContentFilled = false;
                }

                if (questionContentFilled && atLeastOneContact) {
                    nextButton.setEnabled(true);
                } else {
                    nextButton.setEnabled(false);
                }
            }
        });

        contactsRecyclerView = (RecyclerView) rootView.findViewById(R.id.contacts_recycler_view_ask_question_fragment);
        allContacts = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        contactsRecyclerView.setLayoutManager(linearLayoutManager);
        contactAdapter = new ContactAdapter(context);
        contactsRecyclerView.setAdapter(contactAdapter);
        ((NetworkApplication)getActivity().getApplication()).getDataBaseManager().getContacts(AskQuestionFragment.this);
        contactAdapter.setListener(new ClickableRecyclerViewAdapter.ClickableRecyclerViewAdapterListener() {
            @Override
            public void onClick(int position) {
                Contact currentContact = allContacts.get(position);
                if (question.getContactArrayList().contains(currentContact)) {
                    question.getContactArrayList().remove(currentContact);
                    currentContact.setIsAdded(false);
                } else {
                    question.getContactArrayList().add(currentContact);
                    currentContact.setIsAdded(true);
                }

                if ( (question.getContactArrayList()).size() > 0) {
                    atLeastOneContact = true;
                } else {
                    atLeastOneContact = false;
                }

                if (atLeastOneContact && questionContentFilled) {
                    nextButton.setEnabled(true);
                } else {
                    nextButton.setEnabled(false);
                }

                contactAdapter.refreshContact(allContacts);
            }
        });

        nextButton = (Button) rootView.findViewById(R.id.next_button_ask_question_fragment);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.activity_ask_question_main_container, ConfirmQuestionFragment.newInstance(question, AskQuestionFragment.this))
                        .addToBackStack(null)
                        .commit();
            }
        });

        return rootView;
    }

    @Override
    public void onSucceed(Question question) {
        ((NetworkApplication)getActivity().getApplication()).getDataBaseManager().addQuestion(question);
        getActivity().finish();
    }

    @Override
    public void onCancel(Question question) {
        getFragmentManager().popBackStack();
        for (int i = 0 ; i < question.getContactArrayList().size() ; i++) {
            question.getContactArrayList().get(i).setIsAdded(true);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setActionBarTitle(R.string.ask_question_fragment_navigation_title);
    }

    @Override
    public void getContacts(ArrayList<Contact> contacts) {
        this.allContacts.clear();
        this.allContacts.addAll(contacts);
        this.contactAdapter.refreshContact(allContacts);
    }
}
