package com.yolo.michael.yolo.fragment.rappel;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yolo.michael.yolo.R;
import com.yolo.michael.yolo.adapter.Recall.RecallAdapter;
import com.yolo.michael.yolo.fragment.base.BaseFragment;
import com.yolo.michael.yolo.model.Rappel;
import com.yolo.michael.yolo.network.DataBaseManager;
import com.yolo.michael.yolo.network.NetworkApplication;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RappelFragment extends BaseFragment implements AddRappelFragment.AddRappelListener, DataBaseManager.ListenerRappel {

    private Button addNewButton;
    private ArrayList<Rappel> rappelList;
    private RecyclerView rappelsRecyclerView;
    private RecallAdapter recallAdapter;
    private Context context;

    public RappelFragment() {
        // Required empty public constructor
    }

    public static RappelFragment newInstance() {
        RappelFragment rappelFragment = new RappelFragment();
        return rappelFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_rappel, container, false);

        context = getActivity();
        rappelList = new ArrayList<>();

        setActionBarTitle(R.string.rappel_fragment_navigation_title);

        addNewButton = (Button) rootView.findViewById(R.id.add_new_recall_button_fragment_rappel);
        addNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.home_event_fragment_container, AddRappelFragment.newInstance(RappelFragment.this))
                        .addToBackStack(null)
                        .commit();
            }
        });

        rappelsRecyclerView = (RecyclerView) rootView.findViewById(R.id.recall_recycler_view_fragment_rappel);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rappelsRecyclerView.setLayoutManager(linearLayoutManager);
        recallAdapter = new RecallAdapter(context);
        rappelsRecyclerView.setAdapter(recallAdapter);
        recallAdapter.refreshRappel(rappelList);

        return rootView;
    }

    @Override
    public void onSucceed(Rappel rappel) {
        ((NetworkApplication)getActivity().getApplication()).getDataBaseManager().addRappel(rappel);
        ((NetworkApplication)getActivity().getApplication()).getDataBaseManager().getRappels(RappelFragment.this);
        getFragmentManager().popBackStack();


    }

    @Override
    public void onCancel() {
        getFragmentManager().popBackStack();
    }

    @Override
    public void onResume() {
        super.onResume();
        setActionBarTitle(R.string.rappel_fragment_navigation_title);

    }

    @Override
    public void getRappels(ArrayList<Rappel> rappels) {
        rappelList.clear();
        rappelList.addAll(rappels);
        recallAdapter.refreshRappel(rappelList);
    }
}
