package com.shuangtu.prison.common.net;

import com.shuangtu.prison.common.model.ModelApplyOrder;
import com.shuangtu.prison.common.model.ModelApplySearch;
import com.shuangtu.prison.common.model.ModelAreaBed;
import com.shuangtu.prison.common.model.ModelAreaDuty;
import com.shuangtu.prison.common.model.ModelAreaRoster;
import com.shuangtu.prison.common.model.ModelCaseNoticeDetails;
import com.shuangtu.prison.common.model.ModelHospitalOrder;
import com.shuangtu.prison.common.model.ModelHospitalOrderClassify;
import com.shuangtu.prison.common.model.ModelHospitalOrderList;
import com.shuangtu.prison.common.model.ModelHospitalRecord;
import com.shuangtu.prison.common.model.ModelHospitalSearch;
import com.shuangtu.prison.common.model.ModelMakeOrder;
import com.shuangtu.prison.common.model.ModelMakeRecipesSearch;
import com.shuangtu.prison.common.model.ModelCaseNotice;
import com.shuangtu.prison.common.model.ModelMealGenerate;
import com.shuangtu.prison.common.model.ModelMealOrder;
import com.shuangtu.prison.common.model.ModelMealOrderList;
import com.shuangtu.prison.common.model.ModelMeeting;
import com.shuangtu.prison.common.model.ModelMeetingDetails;
import com.shuangtu.prison.common.model.ModelDeviceInfo;
import com.shuangtu.prison.common.model.ModelPolice;
import com.shuangtu.prison.common.model.ModelUserInfo;
import com.shuangtu.prison.common.model.QModel;
import com.shuangtu.prison.common.model.LoginModel;
import com.shuangtu.prison.common.model.ModelGoodsClassify;
import com.shuangtu.prison.common.model.ModelGoodsOrder;
import com.shuangtu.prison.common.model.ModelGoodsOrderShop;
import com.shuangtu.prison.common.model.ModelGoodsShop;
import com.shuangtu.prison.common.model.ModelNoticeMessage;
import com.shuangtu.prison.common.model.ModelWeb;


import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiService {


    @POST("/api/v1/auth/login")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Observable<QModel<LoginModel>> login(@Body RequestBody requestBody);

    @GET("/api/v1/info/news/list")
    Observable<QModel<ModelNoticeMessage>> newsList(@Query("pageNo") int pageNo, @Query("isPicNew") String isPicNew, @Query("pageSize") int pageSize, @Query("columnCode") String columnCode, @Query("startDate") String startDate, @Query("endDate") String endDate);

    @GET("/api/v1/info/news/get")
    Observable<QModel<ModelWeb>> webData(@Query(("id")) String id);

    @GET("/api/v1/shop/product-category/select-option")
    Observable<QModel<List<ModelGoodsClassify>>> getGoodsListClssify();

    @GET("/api/v1/shop/product/list")
    Observable<QModel<ModelGoodsShop>> getGoodsListShop(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Query("productCategoryId") String productCategoryId);

    @GET("/api/v1/shop/order/list-cur-user")
    Observable<QModel<ModelGoodsOrder>> getGoodsOrder(@Query("pageNo") int pageNo, @Query("pageSize") int pageSiz);

    @GET("/api/v1/shop/order-item/list")
    Observable<QModel<ModelGoodsOrderShop>> getGoodsOrderShop(@Query("pageNo") int pageNo, @Query("pageSize") int pageSiz, @Query("orderId") String orderId);

    @POST("/api/v1/shop/order/generate-order")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Observable<QModel<ModelMakeOrder>> getMakeOrder(@Body RequestBody requestBody);

    @POST("/api/v1/shop/pay-account/pay-by-order")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Observable<QModel<String>> getGoodsPay(@Body RequestBody requestBody);

    @POST("/api/v1/shop/order-item/confirm-receipt")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Observable<QModel<String>> getGoodsConfirm(@Body RequestBody requestBody);

    @GET("/api/v1/prison/weekly-cookbook/get-current-week")
    Observable<QModel<ModelMakeRecipesSearch>> getMakeRecipesSearch();

    @GET("/api/v1/prison/case-notice/list")
    Observable<QModel<ModelCaseNotice>> getCaseNotice(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Query("startDate") String startDate, @Query("endDate") String endDate);

    @GET("/api/v1/prison/case-notice/get")
    Observable<QModel<ModelCaseNoticeDetails>> getCaseNoticeDetails(@Query(("id")) String id);

    @GET("/api/v1/prison/meeting-notice/list")
    Observable<QModel<ModelMeeting>> getMeeting(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Query("startDate") String startDate, @Query("endDate") String endDate, @Query("noticeType") int noticeType);

    @GET("/api/v1/prison/meeting-notice/get")
    Observable<QModel<ModelMeetingDetails>> getMeetingNoticeDetails(@Query(("id")) String id);

    @GET("/api/v1/auth/user/list-by-usertype")
    Observable<QModel<ModelApplyOrder>> getApplyOrder(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Query("userType") int userType);

    @POST("/api/v1/prison/appointment/add")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Observable<QModel<ModelApplyOrder>> getApplyMakeOrder(@Body RequestBody requestBody);

    @GET("/api/v1/shop/meal-supply/list")
    Observable<QModel<ModelMealOrder>> getMealOrder(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Query("timeSlot") int timeSlot);

    @POST("/api/v1/shop/order/meal/generate-order")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Observable<QModel<ModelMealGenerate>> getMealGenerate(@Body RequestBody requestBody);

    @GET("/api/v1/shop/meal-supply/list")
    Observable<QModel<ModelMealOrderList>> getMealOrderList(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize);

    @GET("/api/v1/prison/prison-bed/list")
    Observable<QModel<ModelAreaBed>> getAreaBed(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Query("roomId") String roomId);

    @GET("/api/v1/prison/duty-table/list")
    Observable<QModel<ModelAreaDuty>> getAreaDuty(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Query("roomId") String roomId);

    @GET("/api/v1/prison/duty-roster/list-by-week")
    Observable<QModel<List<ModelAreaRoster>>> getAreaRoster(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Query("roomId") String roomId);

    @POST("/api/v1/auth/cur-user-info")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Observable<QModel<ModelUserInfo>> getUserArea();

    @GET("/api/v1/prison/ill-record/list-cur-user")
    Observable<QModel<ModelHospitalSearch>> getHospitalSearch(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Query("illStatus") String illStatus);

    @GET("/api/v1/prison/appointment/list")
    Observable<QModel<ModelApplySearch>> getApplySearch(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize);

    @GET("/api/v1/prison/ill-record/list-cur-user")
    Observable<QModel<ModelHospitalRecord>> getHospitalRecord(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize);

    @GET("/api/v1/prison/ill-category/list")
    Observable<QModel<ModelHospitalOrderClassify>> getHospitalOrderClassify(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize);

    @GET("/api/v1/prison/ill/list")
    Observable<QModel<ModelHospitalOrderList>> getHospitalOrderList(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Query("categoryId") String orderId);

    @POST("/api/v1/prison/ill-appointment/add")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Observable<QModel<ModelHospitalOrder>> getHospitalOrder(@Body RequestBody requestBody);

    @POST("/api/v1/prison/ill-record/drug-receipt-confirm")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Observable<QModel<String>> getHospitalConfirm(@Body RequestBody requestBody);

    // @GET("/api/v1/auth/logout")
    @POST("/api/v1/auth/logout")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Observable<QModel<String>> getLogout();

    @GET("/api/v1/prison/device-auth/cur-room-info")
    Observable<QModel<ModelDeviceInfo>> getRoomInfo();

    @GET("/api/v1/prison/room-police/list")
    Observable<QModel<ModelPolice>> getPolice(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Query("roomId") String roomId);
}
