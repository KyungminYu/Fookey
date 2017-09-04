package com.smu.cs.fookey;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.smu.cs.fookey.Network.Description;
import com.smu.cs.fookey.Network.Nutrient;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SpecificActivity extends AppCompatActivity {
    private ImageView image_food;
    private TextView text_foodName,text_foodCategory, text_foodCalorie, text_foodSafety;
    private PieChart chart_nutrient;
    private String imgPath;
    private Description description;
    private Nutrient nutrient;

    private int width, height;

    private DataBaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific);

        initData();

        setImage(imgPath);
        setText(description);
        setChart(nutrient);


        //History 를 통한 경우에는 데이터 추가 x Search 를 통한 경우에는 데이터 추가 o
        SimpleDateFormat mformat = new SimpleDateFormat( "yyyy-MM-dd", Locale.KOREA);
        Date date = new Date();
        dbHandler = DataBaseHandler.getInstance(this);
        //dbHandler.insertData(new FoodData(description.getFood_name(), imgPath, mformat.format(date)));
        Toast.makeText(this, mformat.format(date), Toast.LENGTH_SHORT).show();
    }
    private void initData(){
        image_food = (ImageView)findViewById(R.id.image_food);
        text_foodName = (TextView)findViewById(R.id.text_foodName);
        text_foodCategory = (TextView)findViewById(R.id.text_foodCategory);
        text_foodCalorie = (TextView)findViewById(R.id.text_foodCalorie);
        text_foodSafety = (TextView)findViewById(R.id.text_foodSafety);
        chart_nutrient = (PieChart)findViewById(R.id.chart_nutrient);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        description= ((List<Description>)bundle.getSerializable("description")).get(0);
        nutrient = description.getNutrient();
        imgPath = intent.getStringExtra("path");


        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
        width = dm.widthPixels;
        height = dm.heightPixels;
    }
    private void setImage(String Path){
        Log.i("PATH", Path);
        File imgFile = new  File(Path);
        if(imgFile.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            //int len = bitmap.getWidth() < bitmap.getHeight() ? bitmap.getWidth() : bitmap.getHeight();
            image_food.setImageBitmap(bitmap);
            image_food.getLayoutParams().height = width;
            //image_food.getLayoutParams().width = width;
            image_food.setRotation(90);
            image_food.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }
    private void setText(Description textData){
        text_foodName.setText(textData.getFood_name());
        text_foodCategory.setText(textData.getFood_category());
        text_foodCalorie.setText(textData.getCalorie());
        text_foodSafety.setText("영양 안전도: " + textData.getSafety());
    }
    private void setChart(Nutrient chartData){
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(chartData.getCarbohydrate(), "탄수화물"));
        entries.add(new PieEntry(chartData.getProtein(), "단백질"));
        entries.add(new PieEntry(chartData.getFat(), "지방"));


        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(1f);
        dataSet.setValueTextSize(10f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        PieData data = new PieData(dataSet);

        chart_nutrient.setData(data);
        chart_nutrient.setDrawSliceText(false);
        chart_nutrient.animateY(1000);
        chart_nutrient.invalidate();

        chart_nutrient.getLayoutParams().height = width * 3 / 4;
        chart_nutrient.getLayoutParams().width = width * 3 / 4;

    }
}
