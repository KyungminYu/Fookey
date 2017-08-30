package com.smu.cs.fookey.Network;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Chan on 2017-08-23.
 */
public interface NetworkService {
    @Multipart
    @POST("/img_upload")     //request: 이미지 전송, response: 메인카테고리 목록들을 보여줌.
    Call<Category> sendImage(@Part("description") RequestBody description,
                             @Part MultipartBody.Part file);

    @GET("/select_main/{cate_main}")
    Call<Category> sendMainAnswer(@Path("cate_main") String ans);

    @GET("/select_sub/{cate_sub}")
    Call<Description> sendSubAnswer(@Path("cate_sub") String ans);
}
