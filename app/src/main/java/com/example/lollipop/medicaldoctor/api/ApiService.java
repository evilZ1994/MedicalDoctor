package com.example.lollipop.medicaldoctor.api;

import com.example.lollipop.medicaldoctor.data.response.DataDetailResponse;
import com.example.lollipop.medicaldoctor.data.response.DataDownloadResponse;
import com.example.lollipop.medicaldoctor.data.response.DoctorUserInfoResponse;
import com.example.lollipop.medicaldoctor.data.response.LoginResponse;
import com.example.lollipop.medicaldoctor.data.response.PatientListResponse;
import com.example.lollipop.medicaldoctor.data.response.PatientUserInfoResponse;
import com.example.lollipop.medicaldoctor.data.response.RegisterResponse;
import com.example.lollipop.medicaldoctor.data.response.UpdateInfoResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Lollipop on 2017/4/28.
 */

public interface ApiService {
    @FormUrlEncoded
    @POST("LoginServlet")
    Observable<LoginResponse> login(@Field("type") String type,
                                    @Field("username") String username,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("RegisterServlet")
    Observable<RegisterResponse> register(@Field("type") String type,
                                          @Field("user") String userString);

    @GET("GetPatientListServlet")
    Observable<PatientListResponse> getPatientList(@Query("id") int id);

    @GET("DataDownloadServlet")
    Observable<DataDownloadResponse> getDataList(@Query("id") int id);

    @GET("GetDataDetailServlet")
    Observable<DataDetailResponse> getDataDetail(@Query("id") int id,
                                                 @Query("tag") String tag);

    @GET("GetUserInfoServlet")
    Observable<PatientUserInfoResponse> getUserInfo(@Query("type") String type,
                                                    @Query("id") int id);

    @GET("GetUserInfoServlet")
    Observable<DoctorUserInfoResponse> getDoctorInfo(@Query("type") String type,
                                                     @Query("id") int id);

    @FormUrlEncoded
    @POST("InfoChangeServlet")
    Observable<UpdateInfoResponse> updateInfo(@Field("type") String type,
                                              @Field("id") int id,
                                              @Field("tag") String tag,
                                              @Field("content") String content);
}
