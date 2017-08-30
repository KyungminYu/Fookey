package com.smu.cs.fookey;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smu.cs.fookey.Network.NetworkApi;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView resultImg;
    private TextView resultText;
    private Button yesBtn, noBtn;

    private NetworkApi networkApi;
    private void initNetworkApi(){networkApi=NetworkApi.getNetworkApi(); }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        resultImg = (ImageView)findViewById(R.id.Image_result);
        resultText = (TextView)findViewById(R.id.text_result);
        yesBtn = (Button)findViewById(R.id.btn_yes);
        noBtn = (Button)findViewById(R.id.btn_no);

        Intent intent = getIntent();
        final String imgPath = intent.getStringExtra("imgUri");
        setImage(imgPath);

        initNetworkApi();

        BlockingQueue<List<String>> blockingQueue = networkApi.sendImage(getApplicationContext(), imgPath);
        List<String> res = null;
        try {
            res = blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            resultText.setText(res.get(0));
        }catch (NullPointerException | IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        yesBtn.setOnClickListener(this);
        noBtn.setOnClickListener(this);
    }
    private void setImage(String Path){
        File imgFile = new  File(Path);
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            int len = myBitmap.getWidth();
            resultImg.setImageBitmap(myBitmap);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) resultImg.getLayoutParams();
            params.width = params.height = len;
            resultImg.setLayoutParams(params);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_yes:
                break;
            case R.id.btn_no:
                break;
            default:
                break;
        }
    }
}


//
//    /*
//     * for Network
//     */
//    private NetworkApi networkApi;
//    private void initNetworkApi(){networkApi=NetworkApi.getNetworkApi();}
//                                IntentHandler.SearchToResult(mContext, path);