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
import android.widget.Toast;

import com.smu.cs.fookey.Network.Description;
import com.smu.cs.fookey.Network.NetworkApi;
import com.smu.cs.fookey.Network.Nutrient;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView resultImg;
    private TextView resultText;
    private Button yesBtn, noBtn;
    private NetworkApi networkApi;
    private int flag;
    private String imgPath;
    private Context mContext;
    private List<String> res;
    private void initNetworkApi(){networkApi=NetworkApi.getNetworkApi(); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        flag = 0;
        res = null;
        mContext = getApplicationContext();
        imgPath = getIntent().getStringExtra("imgUri");
        resultImg = (ImageView)findViewById(R.id.Image_result);
        resultText = (TextView)findViewById(R.id.text_result);
        yesBtn = (Button)findViewById(R.id.btn_yes);
        noBtn = (Button)findViewById(R.id.btn_no);

        initNetworkApi();
        setImage(imgPath);
        setText(flag, mContext,imgPath);

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
    private void setText(int flag, Context context, String path){
        if(flag == 0){
            //for test
            res = new ArrayList<>();
            res.add("dish");
            res.add("side");
            res.add("soup");
            res.add("rice");
//            try {
//                res  = networkApi.sendImage(context, path);
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        else{
            //for test
            res = new ArrayList<>();
            res.add("kimch");
            res.add("fiedEgg");
            res.add("soup");
            res.add("rice");
            //res = networkApi.sendMainAnswer("yes");
        }
        resultText.setText(res.get(0));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_yes:
                if(flag == 0){
                    flag = 1;
                    setText(flag, mContext, imgPath);
                }
                else{
                    //networkApi.sendSubAnswer("yes");
                    // 여기
                    Toast.makeText(mContext, "change Activity At the here", Toast.LENGTH_LONG).show();
                    Description description = new Description("","","","", new Nutrient(0,0,0));
                    finish();
                    IntentHandler.ResultToSpecific(mContext, description);
                }
                break;
            case R.id.btn_no:
                res.remove(0);
                resultText.setText(res.get(0));
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