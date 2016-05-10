package com.yolo.michael.yolo.adapter.Recall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yolo.michael.yolo.R;
import com.yolo.michael.yolo.adapter.ClickableRecyclerViewAdapter;
import com.yolo.michael.yolo.model.Rappel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student5305 on 2/05/16.
 */
public class RecallAdapter extends ClickableRecyclerViewAdapter<RecallViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Rappel> rappelList;

    public RecallAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.rappelList = new ArrayList<>();
    }

    public void refreshRappel(ArrayList<Rappel> rappels) {
        this.rappelList.clear();
        this.rappelList.addAll(rappels);
        this.notifyDataSetChanged();
    }

    @Override
    public void bindCurrentViewHolder(RecallViewHolder holder, int position) {
        Rappel rappel = rappelList.get(position);
        holder.setRecall(rappel);
    }

    @Override
    public RecallViewHolder instantiateViewHolder(ViewGroup parent, int viewType) {
        View recallRow = layoutInflater.inflate(R.layout.row_recall, parent, false);

        RecallViewHolder recallViewHolder = new RecallViewHolder(recallRow);
        recallViewHolder.setListener(this);

        return recallViewHolder;
    }

    @Override
    public int getItemCount() {
        return rappelList.size();
    }
}
