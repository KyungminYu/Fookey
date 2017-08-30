package com.smu.cs.fookey.Network;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.smu.cs.fookey.MainActivity;
import com.smu.cs.fookey.SearchActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Chan on 2017-08-23.
 */
public class NetworkApi extends AppCompatActivity {
    private NetworkService networkService;
    private static NetworkApi instance;
    private void initNetworkService(){
        // TODO: 13. ApplicationController 객체를 이용하여 NetworkService 가져오기
        networkService = ApplicationController.getInstance().getNetworkService();
    }

    NetworkApi(){
        NetworkApi.instance=this;
        initNetworkService();
    }

    public static NetworkApi getNetworkApi() {
        return instance;
    }

    public void sendMainAnswer(String ans){
        Call<Category> getMainAns=networkService.sendMainAnswer(ans);
        getMainAns.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                int statusCode = response.code();
                if(response.isSuccessful()){
                    Log.d("MyTag",""+statusCode);
                    Category category=(Category)response.body();
                    // 채워넣어야 함.
                }
                else{

                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                Log.d("MyTag" ,  t.getMessage());
            }
        });
    }

    public void sendSubAnswer(String ans){
        Call<Description> getMainAns=networkService.sendSubAnswer(ans);
        getMainAns.enqueue(new Callback<Description>() {
            @Override
            public void onResponse(Call<Description> call, Response<Description> response) {
                int statusCode = response.code();
                if(response.isSuccessful()){
                    Log.d("MyTag",""+statusCode);
                    Description description=(Description)response.body();
                    Log.d("description",description.description);
                    // 채워넣어야 함.
                }
                else{

                }
            }

            @Override
            public void onFailure(Call<Description> call, Throwable t) {
                Log.d("MyTag" ,  t.getMessage());
            }
        });
    }

    public void sendImage(Context context, String path){
        //getImageNameToUri(uri);
        MultipartBody.Part body = getCompressedImage(context, path);
        RequestBody description = getMultipartDescription("I send a photo image");
        Call<Category> call = networkService.sendImage(description, body);
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                int statusCode = response.code();
                if(response.isSuccessful()){
                    Log.d("MyTag",""+statusCode);
                    Category category=(Category)response.body();

                }
                else{

                }
            }
            @Override
            public void onFailure(Call<Category> call, Throwable t) {

            }
        });
    }
    private MultipartBody.Part getCompressedImage(Context context, String path){
        String imgUri = path;
        BitmapFactory.Options options = new BitmapFactory.Options();
//                        options.inSampleSize = 4; //얼마나 줄일지 설정하는 옵션 4--> 1/4로 줄이겠다
        //입력으로 들어오는 uri가 사실은 String이다. String to Uri로 바꿔주는 작업이 필요하다.
        Uri uri= Uri.fromFile(new File(imgUri));//Uri.parse((imgUri));
        Log.i("imgUri", uri.toString());
        InputStream in = null;
        try {
            in = context.getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(in == null)
            Log.w("INPUT_STREAM", "NULL");
        Bitmap bitmap = BitmapFactory.decodeStream(in, null, options); // InputStream 으로부터 Bitmap 을 만들어 준다.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if(bitmap == null)
            Log.w("BITMAP", "NULL");
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos); // 압축 옵션( JPEG, PNG ) , 품질 설정 ( 0 - 100까지의 int형 ),

        RequestBody photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray());
        File photo = new File(imgUri);
        MultipartBody.Part body = MultipartBody.Part.createFormData("picture", photo.getName(), photoBody);
        return body;
    }

    private RequestBody getMultipartDescription(String descriptionString){
        RequestBody description = RequestBody.create(okhttp3.MultipartBody.FORM, descriptionString);
        return description;
    }
}
