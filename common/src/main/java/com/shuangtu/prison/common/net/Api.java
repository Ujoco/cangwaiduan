package com.shuangtu.prison.common.net;


import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.shuangtu.prison.common.constant.Constant;
import com.shuangtu.prison.common.model.ModelApplyOrder;
import com.shuangtu.prison.common.model.ModelApplySearch;
import com.shuangtu.prison.common.model.ModelAreaBed;
import com.shuangtu.prison.common.model.ModelAreaDuty;
import com.shuangtu.prison.common.model.ModelAreaRoster;
import com.shuangtu.prison.common.model.ModelCaseNotice;
import com.shuangtu.prison.common.model.ModelCaseNoticeDetails;
import com.shuangtu.prison.common.model.ModelHospitalOrder;
import com.shuangtu.prison.common.model.ModelHospitalOrderClassify;
import com.shuangtu.prison.common.model.ModelHospitalOrderList;
import com.shuangtu.prison.common.model.ModelHospitalRecord;
import com.shuangtu.prison.common.model.ModelHospitalSearch;
import com.shuangtu.prison.common.model.ModelMakeOrder;
import com.shuangtu.prison.common.model.ModelMakeRecipesSearch;
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


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by PENG on 2018/4/9.
 */

public class Api {
    public ApiService service;
    private static final int MAXNUMBER = 9999;

    public Api() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.getServiceAddress())
                .addConverterFactory(ResponseConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(initOkHttpClicent())
                .build();

        LogUtils.a("服务器地址：" + retrofit.baseUrl());
        service = retrofit.create(ApiService.class);
    }

    private OkHttpClient initOkHttpClicent() {
        return new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS).
                        readTimeout(15, TimeUnit.SECONDS).
                        writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(new CommonInterceptor())
                //      .addNetworkInterceptor(new FishInterceptor())
                .build();
    }


    private static Api api;


    public static synchronized Api getInstance() {
        if (api == null) {
            api = new Api();
        }
        return api;
    }

    /*
    登陆类型，1用户登陆，2终端登陆
     */
    public Observable<LoginModel> login(String user, String pwd, int loginType) {
        // @Field("userName") String userName, @Field("password") String password
        HashMap<String, String> map = new HashMap<>();
        map.put("userName", user);
        map.put("password", pwd);
        map.put("loginType", String.valueOf(loginType));
        return service.login(getRequestBody(map)).map(new QModel<LoginModel>()).onErrorResumeNext(new QHttpResponseFunc<LoginModel>());
    }

    public Observable<ModelNoticeMessage> newsList(int pageNo, int pageSize, String columnCode) {
        return service.newsList(pageNo, "0", pageSize, columnCode, null, null).map(new QModel<ModelNoticeMessage>()).onErrorResumeNext(new QHttpResponseFunc<ModelNoticeMessage>());
    }

    public Observable<ModelNoticeMessage> newsList(int pageNo, int pageSize, String columnCode, String isPicNew) {
        return service.newsList(pageNo, isPicNew, pageSize, columnCode, null, null).map(new QModel<ModelNoticeMessage>()).onErrorResumeNext(new QHttpResponseFunc<ModelNoticeMessage>());
    }

    public Observable<ModelNoticeMessage> newsList(int pageNo, int pageSize, String columnCode, String startDate, String endDate) {
        return service.newsList(pageNo, "0", pageSize, columnCode, startDate, endDate).map(new QModel<ModelNoticeMessage>()).onErrorResumeNext(new QHttpResponseFunc<ModelNoticeMessage>());
    }

    public RequestBody getRequestBody(HashMap hashMap) {
        RequestBody requestBody =
                RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"), GsonUtils.getGson().toJson(hashMap));
        return requestBody;
    }

    public Observable<ModelWeb> webData(String id) {
        return service.webData(id).map(new QModel<ModelWeb>()).onErrorResumeNext(new QHttpResponseFunc<ModelWeb>());
    }

    public Observable<List<ModelGoodsClassify>> getGoodsListClssify() {
        return service.getGoodsListClssify().map(new QModel<List<ModelGoodsClassify>>()).onErrorResumeNext(new QHttpResponseFunc<List<ModelGoodsClassify>>());
    }

    public Observable<ModelGoodsShop> getGoodsListShop(int pageNo, int pageSize, String productCategoryId) {
        return service.getGoodsListShop(pageNo, pageSize, productCategoryId).map(new QModel<ModelGoodsShop>()).onErrorResumeNext(new QHttpResponseFunc<ModelGoodsShop>());
    }

    public Observable<ModelGoodsOrder> getGoodsOrder(int pageNo, int pageSize) {
        return service.getGoodsOrder(pageNo, pageSize).map(new QModel<ModelGoodsOrder>()).onErrorResumeNext(new QHttpResponseFunc<ModelGoodsOrder>());
    }

    public Observable<ModelGoodsOrderShop> getGoodsOrderShop(int pageNo, int pageSize, String orderId) {
        return service.getGoodsOrderShop(pageNo, pageSize, orderId).map(new QModel<ModelGoodsOrderShop>()).onErrorResumeNext(new QHttpResponseFunc<ModelGoodsOrderShop>());
    }

    public Observable<ModelMakeOrder> getMakeOrder(List<HashMap<String, String>> list) {
        return service.getMakeOrder(getRequestBodyList(list)).map(new QModel<ModelMakeOrder>()).onErrorResumeNext(new QHttpResponseFunc<ModelMakeOrder>());
    }

    public RequestBody getRequestBodyList(List list) {
        RequestBody requestBody =
                RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"), GsonUtils.getGson().toJson(list));
        return requestBody;
    }

    public Observable<String> getGoodsPay(String id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("orderId", id);
        return service.getGoodsPay(getRequestBody(map)).map(new QModel<String>()).onErrorResumeNext(new QHttpResponseFunc<String>());
    }

    public Observable<String> getGoodsConfirm(String id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("orderItemId", id);
        List list = new ArrayList();
        list.add(map);
        return service.getGoodsConfirm(getRequestBodyList(list)).map(new QModel<String>()).onErrorResumeNext(new QHttpResponseFunc<String>());
    }

    public Observable<ModelMakeRecipesSearch> getMakeRecipesSearch() {
        return service.getMakeRecipesSearch().map(new QModel<ModelMakeRecipesSearch>()).onErrorResumeNext(new QHttpResponseFunc<ModelMakeRecipesSearch>());
    }

    public Observable<ModelCaseNotice> getCaseNotice(int pageNo, int pageSize, String startDate, String endDate) {
        return service.getCaseNotice(pageNo, pageSize, startDate, endDate).map(new QModel<ModelCaseNotice>()).onErrorResumeNext(new QHttpResponseFunc<ModelCaseNotice>());
    }

    public Observable<ModelCaseNoticeDetails> getCaseNoticeDetails(String id) {
        return service.getCaseNoticeDetails(id).map(new QModel<ModelCaseNoticeDetails>()).onErrorResumeNext(new QHttpResponseFunc<ModelCaseNoticeDetails>());
    }

    public Observable<ModelMeeting> getMeeting(int pageNo, int pageSize, String startDate, String endDate, String noticeType) {
        return service.getMeeting(pageNo, pageSize, startDate, endDate, Integer.valueOf(noticeType)).map(new QModel<ModelMeeting>()).onErrorResumeNext(new QHttpResponseFunc<ModelMeeting>());
    }

    public Observable<ModelMeetingDetails> getMeetingNoticeDetails(String id) {
        return service.getMeetingNoticeDetails(id).map(new QModel<ModelMeetingDetails>()).onErrorResumeNext(new QHttpResponseFunc<ModelMeetingDetails>());
    }

    public Observable<ModelApplyOrder> getApplyOrder(int userType) {
        return service.getApplyOrder(1, MAXNUMBER, userType).map(new QModel<ModelApplyOrder>()).onErrorResumeNext(new QHttpResponseFunc<ModelApplyOrder>());
    }

    public Observable<ModelApplyOrder> getApplyMakeOrder(String appointUserId, String appointUserName, int appointType, String appointPhone, String relationship, String cause, String remark) {
        HashMap<String, String> map = new HashMap<>();
        map.put("appointUserId", appointUserId);
        map.put("appointUserName", appointUserName);
        map.put("appointType", String.valueOf(appointType));
        map.put("appointPhone", appointPhone);
        map.put("relationship", relationship);
        map.put("cause", cause);
        return service.getApplyMakeOrder(getRequestBody(map)).map(new QModel<ModelApplyOrder>()).onErrorResumeNext(new QHttpResponseFunc<ModelApplyOrder>());
    }

    public Observable<ModelMealOrder> getMealOrder(int timeSlot) {
        return service.getMealOrder(1, MAXNUMBER, timeSlot).map(new QModel<ModelMealOrder>()).onErrorResumeNext(new QHttpResponseFunc<ModelMealOrder>());
    }

    public Observable<ModelMealGenerate> getMealGenerate(List<HashMap<String, String>> list) {
        RequestBody requestBody = getRequestBodyList(list);
        return service.getMealGenerate(requestBody).map(new QModel<ModelMealGenerate>()).onErrorResumeNext(new QHttpResponseFunc<ModelMealGenerate>());
    }

    public Observable<ModelMealOrderList> getMealOrderList(int pageNo, int pageSize) {
        return service.getMealOrderList(pageNo, pageSize).map(new QModel<ModelMealOrderList>()).onErrorResumeNext(new QHttpResponseFunc<ModelMealOrderList>());
    }

    public Observable<ModelAreaBed> getAreaBed(int pageNo, int pageSize, String roomId) {
        return service.getAreaBed(pageNo, pageSize, roomId).map(new QModel<ModelAreaBed>()).onErrorResumeNext(new QHttpResponseFunc<ModelAreaBed>());
    }

    public Observable<ModelAreaDuty> getAreaDuty(int pageNo, int pageSize, String roomId) {
        return service.getAreaDuty(pageNo, pageSize, roomId).map(new QModel<ModelAreaDuty>()).onErrorResumeNext(new QHttpResponseFunc<ModelAreaDuty>());
    }

    public Observable<List<ModelAreaRoster>> getAreaRoster(int pageNo, int pageSize, String roomId) {
        return service.getAreaRoster(pageNo, pageSize, roomId).map(new QModel<List<ModelAreaRoster>>()).onErrorResumeNext(new QHttpResponseFunc<List<ModelAreaRoster>>());
    }

    public Observable<ModelUserInfo> getUserArea() {
        return service.getUserArea().map(new QModel<ModelUserInfo>()).onErrorResumeNext(new QHttpResponseFunc<ModelUserInfo>());
    }

    public Observable<ModelHospitalSearch> getHospitalSearch(int pageNo, int pageSize, String illStatus) {
        return service.getHospitalSearch(pageNo, pageSize, illStatus).map(new QModel<ModelHospitalSearch>()).onErrorResumeNext(new QHttpResponseFunc<ModelHospitalSearch>());
    }

    public Observable<ModelApplySearch> getApplySearch(int pageNo, int pageSize) {
        return service.getApplySearch(pageNo, pageSize).map(new QModel<ModelApplySearch>()).onErrorResumeNext(new QHttpResponseFunc<ModelApplySearch>());
    }

    public Observable<ModelHospitalRecord> getHospitalRecord(int pageNo, int pageSize) {
        return service.getHospitalRecord(pageNo, pageSize).map(new QModel<ModelHospitalRecord>()).onErrorResumeNext(new QHttpResponseFunc<ModelHospitalRecord>());
    }

    public Observable<ModelHospitalOrderClassify> getHospitalOrderClassify(int pageNo, int pageSize) {
        return service.getHospitalOrderClassify(pageNo, MAXNUMBER).map(new QModel<ModelHospitalOrderClassify>()).onErrorResumeNext(new QHttpResponseFunc<ModelHospitalOrderClassify>());
    }

    public Observable<ModelHospitalOrderList> getHospitalOrderList(int pageNo, int pageSize, String id) {
        return service.getHospitalOrderList(pageNo, pageSize, id).map(new QModel<ModelHospitalOrderList>()).onErrorResumeNext(new QHttpResponseFunc<ModelHospitalOrderList>());
    }

    public Observable<ModelHospitalOrder> getHospitalOrder(String illDetail) {
        HashMap<String, String> map = new HashMap<>();
        map.put("illDetail", illDetail);
        return service.getHospitalOrder(getRequestBody(map)).map(new QModel<ModelHospitalOrder>()).onErrorResumeNext(new QHttpResponseFunc<ModelHospitalOrder>());
    }

    public Observable<String> getHospitalConfirm(String id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);
        return service.getHospitalConfirm(getRequestBody(map)).map(new QModel<String>()).onErrorResumeNext(new QHttpResponseFunc<String>());
    }

    public Observable<String> getLogout() {
        return service.getLogout().map(new QModel<String>()).onErrorResumeNext(new QHttpResponseFunc<String>());
    }

    public Observable<ModelDeviceInfo> getRoomInfo() {
        return service.getRoomInfo().map(new QModel<ModelDeviceInfo>()).onErrorResumeNext(new QHttpResponseFunc<ModelDeviceInfo>());
    }

    public Observable<ModelPolice> getPolice(String roomId) {
        return service.getPolice(1, 1, roomId).map(new QModel<ModelPolice>()).onErrorResumeNext(new QHttpResponseFunc<ModelPolice>());
    }
}
