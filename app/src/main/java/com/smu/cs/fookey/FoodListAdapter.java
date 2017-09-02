package com.smu.cs.fookey;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.smu.cs.fookey.Network.Description;
import com.smu.cs.fookey.Network.Nutrient;

import java.io.File;
import java.util.List;

/**
 * Created by LG on 2017-08-18.
 */

public class FoodListAdapter extends RecyclerView.Adapter<FoodDataHolder> {
    private List<FoodData> dataList;

    public FoodListAdapter(List<FoodData> dataList) {
        this.dataList = dataList;
    }

    @Override
    public FoodDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        final FoodDataHolder holder = new FoodDataHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 서버에서 받던지 내부 DB로 구성하든지 받긴 해야함
                final int position = holder.getAdapterPosition();
                String category = dataList.get(position).getFood_name();
                // -> 이거로 network api사용해서 정보 가져옴? 내장 db가 나을 듯 한데...
                String path = dataList.get(position).getPath();
                Description description = new Description(category,"한식 > 밥류","313kcal / 1공기 (210g)","안전식품", new Nutrient(91,8,1));
                Toast.makeText(v.getContext(), category,  Toast.LENGTH_LONG).show();
                IntentHandler.historyToSpecific(v.getContext(), description, path);
            }
        });
        return holder;
    }
    /*
    position 으로 DB 로딩
    */
    @Override
    public void onBindViewHolder(FoodDataHolder holder, int position) {

        FoodData foodData = dataList.get(position);
        holder.image_food.setImageResource(R.drawable.logo);
        File imgFile = new  File(foodData.getPath());
        if(imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            holder.image_food.setImageBitmap(myBitmap);
        }
        holder.text_category.setText(foodData.getFood_name());
        holder.text_date.setText(foodData.getDate());


    }
    @Override
    public int getItemCount() {
        return (dataList.size() > 0) ? dataList.size() : 0;
    }
}
