package com.smu.cs.fookey;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by LG on 2017-08-18.
 */

public class FoodListAdapter extends RecyclerView.Adapter<FoodDataHolder> {
    private List<FoodData> dataList;
    private DataBaseHandler dbHandler;

    public FoodListAdapter(List<FoodData> dataList) {
        this.dataList = dataList;
    }

    @Override
    public FoodDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        dbHandler = DataBaseHandler.getInstance(view.getContext());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentHandler.historyToSpecific(v.getContext(), getItemCount() - 1);
            }
        });
        return new FoodDataHolder(view);
    }
    /*
    position 으로 DB 로딩
    */
    @Override
    public void onBindViewHolder(FoodDataHolder holder, int position) {
        holder.image_food.setImageResource(R.drawable.logo);
        holder.text_category.setText("RESULT " + position);
        holder.text_date.setText("DATE");
    }
    @Override
    public int getItemCount() {
        return (dataList.size() > 0) ? dataList.size() : 0;
    }
}
