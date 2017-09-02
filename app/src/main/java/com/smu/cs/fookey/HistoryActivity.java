package com.smu.cs.fookey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private List<FoodData> foodDataList = new ArrayList<>();
    private RecyclerView foodDataListView;
    private RecyclerView.LayoutManager foodListLayoutManager;
    private FoodListAdapter foodListAdapter;
    private DataBaseHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        dbHandler = DataBaseHandler.getInstance(this);
        foodDataListView = (RecyclerView)findViewById(R.id.list_result);

        initializationData();
        initializationListView();
    }

    private void initializationData() {

        List<FoodData> foodDatas = dbHandler.getDataList();
        this.foodDataList = foodDatas;
        FoodData tmp1 = new FoodData("밥", "1-1", "");
        FoodData tmp2 = new FoodData("밥dfsdf", "sdfds1-1", "sdfds");
        this.foodDataList.add(tmp1);
        this.foodDataList.add(tmp2);
    }
    private void initializationListView() {
        foodListLayoutManager = new LinearLayoutManager(this);
        foodDataListView.setLayoutManager(foodListLayoutManager);
        if(foodDataList.size() != 0){
            foodListAdapter = new FoodListAdapter(foodDataList);
            foodDataListView.setAdapter(foodListAdapter);
        }
    }
}
