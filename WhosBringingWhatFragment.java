package com.yolo.michael.yolo.fragment.Event;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.yolo.michael.yolo.R;
import com.yolo.michael.yolo.fragment.base.BaseFragment;
import com.yolo.michael.yolo.model.Event;
import com.yolo.michael.yolo.model.ShoppingArticle;
import com.yolo.michael.yolo.model.ShoppingArticleItem;
import com.yolo.michael.yolo.model.ShoppingCategorie;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class WhosBringingWhatFragment extends BaseFragment {

    private static final String ARGUMENTS = "ARGUMENTS";

    private Button nextButton;
    private Switch participatingSwitch;
    private EditText itemNameEditText;
    private EditText itemNumberEditText;
    private Spinner itemTypeSpinner;
    private Button addButton;
    private RecyclerView itemRecyclerView;

    private ArrayList<String> itemTypes;

    public WhosBringingWhatFragment() {
        // Required empty public constructor
    }

    public static WhosBringingWhatFragment newInstance(Event event) {
        WhosBringingWhatFragment whosBringingWhatFragment = new WhosBringingWhatFragment();
        Bundle arguments = new Bundle();
        arguments.putParcelable(ARGUMENTS, event);
        whosBringingWhatFragment.setArguments(arguments);
        return  whosBringingWhatFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_whos_bringing_what, container, false);

        itemTypes = new ArrayList<>();
        itemTypes.add("Boisson");
        itemTypes.add("Biscuit");
        itemTypes.add("Soin");
        itemTypes.add("Surgelé");

        setActionBarTitle(R.string.whos_bringing_what);

        Bundle incomingArguments = getArguments();
        final Event event = incomingArguments.getParcelable(ARGUMENTS);

        itemNameEditText = (EditText) rootView.findViewById(R.id.item_name_edt_text_whos_bringing_what_fragment);
        itemNumberEditText = (EditText) rootView.findViewById(R.id.item_number_edit_text_whos_bringing_what_fragment);
        itemTypeSpinner = (Spinner) rootView.findViewById(R.id.item_type_spinner_whos_bringing_what_fragment);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, itemTypes);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        itemTypeSpinner.setAdapter(adapter);
        addButton = (Button) rootView.findViewById(R.id.item_add_button_whos_bringing_what_fragment);
        itemRecyclerView = (RecyclerView) rootView.findViewById(R.id.items_recycler_view_whos_bringing_what_fragment);

        participatingSwitch = (Switch) rootView.findViewById(R.id.participative_switch_whos_bringing_what_fragment);
        participatingSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    itemNameEditText.setVisibility(View.VISIBLE);
                    itemNumberEditText.setVisibility(View.VISIBLE);
                    itemTypeSpinner.setVisibility(View.VISIBLE);
                    addButton.setVisibility(View.VISIBLE);
                    itemRecyclerView.setVisibility(View.VISIBLE);
                } else {
                    itemNameEditText.setVisibility(View.INVISIBLE);
                    itemNumberEditText.setVisibility(View.INVISIBLE);
                    itemTypeSpinner.setVisibility(View.INVISIBLE);
                    addButton.setVisibility(View.INVISIBLE);
                    itemRecyclerView.setVisibility(View.INVISIBLE);
                }
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShoppingArticle shoppingArticle = new ShoppingArticle();
                shoppingArticle.setArticle_nom(itemNameEditText.getText().toString());
                shoppingArticle.setArticle_quantite(Integer.parseInt(itemNumberEditText.getText().toString()));
                shoppingArticle.setShoppingCategorie(ShoppingCategorie.fromCode(itemTypeSpinner.getSelectedItemPosition()));
            }
        });



        nextButton = (Button) rootView.findViewById(R.id.next_button_whos_bringing_what_fragment);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.percentage_layout_container_event_creation_activity, EventCreationConfirmationFragment.newInstance(event))
                        .addToBackStack(null)
                        .commit();
            }
        });

        return rootView;
    }

}
