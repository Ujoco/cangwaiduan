package com.shuangtu.prison.discern.face.http.common;

import android.text.TextUtils;
import android.util.Log;


import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shuangtu.prison.common.constant.Constant;
import com.shuangtu.prison.common.constant.STUserManager;
import com.shuangtu.prison.common.notice.NoticeLogin;
import com.shuangtu.prison.discern.face.FeatureVo;
import com.shuangtu.prison.discern.face.UserFeatureVo;
import com.shuangtu.prison.discern.face.http.common.Entity.ResponseEntity;
import com.shuangtu.prison.discern.model.ModelNoticeFaceDiscern;

import org.greenrobot.eventbus.EventBus;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtils {
    final static String RESP_SUCCESS = "0000";
    final static String RESP_INVALID_TOKEN = "8002";
    public static String X_ACCESS_TOKEN = "x-access-token";
    private static String token = "";
    private static Gson gson = new Gson();
    private static final String TAG = "HttpUtils";
    private static String mLoginUrl = "";
    private static String mUserName = "";
    private static String mPassword = "";
    private static String mLoginType = "";
    private static final MediaType jsonType = MediaType.parse("application/json; charset=utf-8");
    private static OkHttpClient client = new OkHttpClient();

    private static String doPost(String url, String json) {
        String result = "";

        try {
            RequestBody body = RequestBody.create(jsonType, json);
            Request request = null;

            request = new Request.Builder()
                    .url(url)
                    .addHeader(X_ACCESS_TOKEN, token)
                    .post(body)
                    .build();
            LogUtils.aTag(TAG, "request:" + request.url().toString());
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            Log.e(TAG, "Login fail!" + e.getLocalizedMessage(), e);
        }

        return result;
    }


//    public static String login(String loginUrl, String userName, String password, String loginType) {
//        mLoginUrl = loginUrl;
//        mUserName = userName;
//        mPassword = password;
//        mLoginType = loginType;
//
//        if (token == null || token.length() < 1) {
//            LoginEntity loginEntity = new LoginEntity(userName, password, loginType);
//            String url = loginUrl.trim();
//
//            try {
//                String result = doPost(url, gson.toJson(loginEntity));
//                ResponseEntity<LoginResponseEntity> response = gson.fromJson(result, new TypeToken<ResponseEntity<LoginResponseEntity>>() {
//                }.getType());
//
//                if (response.getCode().compareTo(RESP_SUCCESS) == 0) {
//                    token = response.getData().getToken();
//                    Log.e(TAG, "Login sucessfully,token:" + token);
//
//                } else {
//                    Log.e(TAG, "Login fail!code:" + response.getCode() + ",message:" + response.getMessage());
//                }
//
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                Log.e(TAG, "convert to json error:" + e.getMessage(), e);
//            }
//        }
//
//        return token;
//    }
//


    public static UserFeatureVo match(String url, float[] feature, int type) {

        UserFeatureVo userFeatureVo = null;
        token = STUserManager.getInstance().getToekn_device();

        FeatureVo featureVo = new FeatureVo();
        featureVo.setFeature(feature);
        featureVo.setType(type);
        try {

            // String result = doPost(Constant.getMatch(), test());
            String result = doPost(url, gson.toJson(featureVo));
            LogUtils.aTag(TAG, "result:" + result + " url:" + url + "token :" + token);
            if (url.equals(Constant.getMatch())) {
                ResponseEntity<UserFeatureVo> response = gson.fromJson(result, new TypeToken<ResponseEntity<UserFeatureVo>>() {
                }.getType());
                if (response != null && response.getData() != null && response.getCode().equals("0000")) {
                    userFeatureVo = response.getData();
                    Log.d(TAG, "match sucessfully,user:" + userFeatureVo);
                    STUserManager.getInstance().setToken_user(response.getData().getToken());
                    NoticeLogin login = new NoticeLogin(1, true);
                    login.setCover(userFeatureVo.getAvatar());
                    login.setName(userFeatureVo.getTrueName());
                    EventBus.getDefault().post(login);
                }
//                    if (response.getCode().compareTo(RESP_SUCCESS) == 0) {
//                        userFeatureVo = response.getData();
//                        Log.d(TAG, "match sucessfully,user:" + userFeatureVo);
//                        STUserManager.getInstance().setToken_user(response.getData().getToken());
//                        EventBus.getDefault().post(new NoticeLogin(1, true));
//                    } else if (response.getCode().compareTo(RESP_INVALID_TOKEN) == 0)//token 无效重新登录
//                    {
//                        token = "";
//                        //login(mLoginUrl, mUserName, mPassword, mLoginType);
//                    } else {
//                        Log.e(TAG, "match fail!code:" + response.getCode() + ",message:" + response.getMessage());
//                    }
            } else if (TextUtils.equals(url, Constant.getFaceDiscern())) {
                ModelNoticeFaceDiscern model = gson.fromJson(result, new TypeToken<ModelNoticeFaceDiscern>() {
                }.getType());
                if (model != null && model.getCode().equals("0000")) {
                    model.setSuccess(type);
                    EventBus.getDefault().post(model);
                    userFeatureVo = new UserFeatureVo();
                }
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e(TAG, "convert to json error:" + e.getMessage(), e);
            userFeatureVo = null;
        }

        return userFeatureVo;
    }

    private static String test() {
        return "{\"feature\":[0.0,0.0,0.0,0.0,0.044052538,0.0,0.0,0.01966776,0.010009181,0.0,0.0,0.0,0.05667581,0.0,0.0,0.009175924,0.014377052,0.14331973,0.0,0.0,0.016029507,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.01166667,0.008961582,0.0,0.0,0.0,0.0,0.013007127,0.0,0.101254694,0.007838147,0.0,0.0,0.0,0.0,0.0,0.012105358,0.0,0.0,0.0,0.0,0.007978395,0.0,0.0,0.045370866,0.107302114,0.0,0.009012455,0.018097376,0.016023314,0.010451275,0.0,0.005682682,0.0,0.0,0.011352142,0.012497955,0.03162133,0.0,0.003208837,0.029026382,0.0049424754,0.0,0.0,0.0,0.0,0.0,0.0,0.021555064,0.0,0.0,0.0,0.0,0.010388897,0.0,0.0,0.0,0.0,0.0,0.047230627,0.016479457,0.0,0.0,0.0,0.0,0.0,0.0,0.095551796,0.010616497,0.0,0.034260876,0.0,0.0,0.0,0.014634315,0.0,0.033141404,0.0,0.0,0.0,0.040437263,0.008283346,0.0,0.032393448,0.0,0.008442089,0.0,0.015823316,0.0,0.19332476,0.027677914,0.011432731,0.0,0.0,0.0,0.0,0.0,0.050523985,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.066585675,0.05010274,0.0,0.012843071,0.0,0.0,0.028796759,0.0,0.0108440565,0.0,0.006112161,0.006320261,0.0,0.0,0.056738455,0.0,0.06721109,0.0,0.0,0.039908666,0.0,0.0026040594,0.0,0.0,0.0,0.03563582,0.0,0.0,0.0,0.14861472,0.0,0.0,0.052230626,0.0,0.0,0.045995668,0.03017817,0.0,0.0039938395,0.0,0.0,0.0,0.05968734,0.0,0.0,0.0,0.0,0.0,0.0,0.009958538,0.07718451,0.0,0.0706539,0.0,0.0,0.017693022,0.0,0.0,0.0,0.005046968,0.0,0.0,0.19633555,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.019440062,0.0,0.0,0.0,0.18329039,0.0,0.0,0.03031391,0.0,0.0,0.0,0.0,0.0,0.016750785,0.020738399,0.0,0.0,0.032858692,0.0,0.0,0.0,0.04437413,0.07330968,0.009562021,0.024415039,0.0,0.0,0.010322037,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.017498778,0.0,0.0,0.0,0.017524952,0.0,0.0,0.011309128,0.043004684,0.0,0.022003083,0.0408009,0.0,0.0,0.082009,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.11134554,0.0,0.0,0.0053902245,0.0,0.0,0.0,0.0,0.0,0.0077706752,0.0,0.0070405803,0.0,0.0,0.0,0.0,0.0,0.011517603,0.0,0.0,0.0,0.030745666,0.0,0.0,0.0,0.0,0.13669504,0.0,0.0,0.0,0.019120423,0.0020482023,0.11841774,0.0,0.0,0.0,0.0,0.0,0.07027535,0.0195039,0.0,0.0,0.0,0.030643262,0.0,0.0,0.0,0.009449566,0.0,0.0819878,0.0,0.0,0.0,0.0,0.0,0.01798176,0.01849947,0.089299455,0.0,0.0,0.0,0.0065550962,0.0,0.0,0.0,0.027072638,0.073436946,0.0,0.0,0.020417083,0.0,0.0,0.0,0.23071522,0.029929077,0.0,0.031981252,0.03873422,0.0,0.01903561,0.0,0.01218782,0.0,0.0,0.0,0.0,0.09285098,0.0,0.0,0.0,0.0,0.0,0.016355015,0.0,0.0,0.0,0.12198838,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.003875053,0.009337816,0.0,0.0,0.0,0.0,0.0,0.0,0.009618424,0.051495895,0.0,0.013503024,0.0,0.0,0.0,0.0,0.0,0.035536315,0.11903508,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.03156157,0.022474583,0.0,0.0,0.0,0.0,0.06514593,0.08348076,0.0,0.007594257,0.0,0.0,0.0057643075,0.0,0.0,0.0,0.0049046115,0.006328562,0.011171935,0.0,0.0,0.010358054,0.024476042,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.023364142,0.0,0.0,0.025785105,0.0,0.04037804,0.0,0.0058540087,0.025469864,0.0,0.011559846,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.03214398,0.09963563,0.0,0.02974707,0.0,0.0,0.0065530953,0.0,0.098551296,0.028778225,0.0,0.0064083952,0.007818808,0.14165367,0.030858615,0.0,0.0,0.05025606,0.0,0.0,0.0,0.014008673,0.0,0.020554176,0.0,0.0,0.0,0.0,0.0,0.0053158193,0.0,0.005971506,0.0,0.0027625784,0.0,0.0,0.03380232,0.054001056,0.043455765,0.06957448,0.0,0.0,0.13066433,0.0,0.0,0.0675468,0.0144839315,0.0,0.01290597,0.010970082,0.0,0.0,0.041299365,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.07178636,0.0070839482,0.0,0.0,0.0,0.0,0.020501792,0.0038847898,0.0039131204,0.0,0.013891981,0.0,0.0,0.005697214,0.074102595,0.005094239,0.0,0.0,0.0,0.0,0.0335668,0.0,0.0,0.020023819,0.021930913,0.0,0.0,0.0,0.0,0.0,0.051148817,0.0,0.06439701,0.0,0.013157773,0.0,0.0,0.07081561,0.019439971,0.0,0.008364968,0.1468245,0.0,0.0,0.0,0.07580308,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.036238074,0.055404495,0.012952155,0.023117483,0.040104896,0.08425766,0.0,0.08048213,0.0,0.0,0.02052506,0.0,0.0,0.0,0.019329088,0.0,0.006832654,0.13311458,0.0,0.0,0.0,0.008901747,0.0,0.0,0.0,0.014261831,0.0,0.009604467,0.0,0.0,0.008722938,0.077561975,0.0,0.017118314,0.0,0.0,0.0,0.0,0.0,0.0,0.11363648,0.08212298,0.0,0.0,0.080172434,0.07279434,0.002047413,0.01373129,0.0,0.016816152,0.0,0.0,0.0,0.0,0.0,0.020948734,0.0147048,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.014746191,0.014829356,0.0,0.014303072,0.0,0.0,0.0,0.0,0.0,0.0,0.003067368,0.0,0.0,0.0,0.012728474,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0052534235,0.0,0.0,0.0,0.0,0.0,0.057806544,0.0,0.03331583,0.041757494,0.0,0.009689222,0.0104009565,0.0,0.03885726,0.0,0.0065743015,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.041546717,0.018367844,0.08338413,0.0,0.11227264,0.0,0.0,0.0111685805,0.0,0.056974255,0.11516194,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.036046255,0.0,0.02275973,0.0,0.022482548,0.0,0.01820003,0.0,0.0,0.0,0.0,0.0,0.0065888604,0.052988514,0.07370664,0.032378875,0.060767338,0.0,0.0,0.0,0.0,0.0,0.0,0.017820828,0.032481328,0.0,0.0,0.0,0.0,0.0,0.036143962,0.0,0.0,0.0,0.06419828,0.008145629,0.0,0.004887225,0.0,0.015594696,0.0,0.0,0.0,0.0,0.012644575,0.0,0.0,0.007397664,0.0,0.0,0.05410704,0.08808555,0.01605121,0.0,0.0,0.0,0.0,0.07174154,0.045445178,0.0,0.0033152413,0.021490581,0.0,0.030061772,0.0,0.0,0.0,0.0,0.0,0.0,0.019373983,0.0,0.019790547,0.0,0.0,0.0,0.0,0.012981788,0.0,0.010791896,0.0,0.015805075,0.0,0.0,0.0,0.032313183,0.018611947,0.0,0.030028317,0.15290824,0.0,0.07060403,0.0,0.0,0.0317283,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.031406064,0.0,0.0,0.017629301,0.02382616,7.781177E-4,0.0,0.0,0.0,0.007269514,0.0,0.0,0.036870293,0.0,0.0,0.007940973,0.015746932,0.0,0.0,0.0,0.039773535,0.028109327,0.0,0.010050353,0.0,0.008122255,0.0,0.0,0.07905501,0.0,0.037807904,0.0,0.0,0.0,0.0,0.0,0.0,0.039131936,0.07662153,0.0,0.048680432,0.0,0.0,0.005346699,0.0,0.0,0.0,0.07735541,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.037720267,0.0983182,0.017370116,0.0,0.02593902,0.017212978,0.03445581,0.0041609886,0.0,0.0,0.05169244,0.0,0.006976612,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.008469457,0.13150422,0.0,0.010863506,0.010745083,0.0,0.001829858,0.006768537,0.0,0.0,0.005360548,0.0,0.0,0.086730935,0.030856438,0.0,0.0,0.0,0.09655122,0.0014836111,0.0,0.0,0.0,0.030317662,0.018250866,0.0,0.0,0.0,0.0,0.038790405,0.0082143545,0.0,0.18338515,0.035273843,0.0,0.0,0.044097725,0.0,0.0,0.0,0.0,0.0,0.0,0.00686926,0.0,0.011443825,0.0,0.0,0.0,0.0,0.08357826,0.0,0.061672136,0.0,0.0,0.0,0.0,0.0,0.0,0.0039879335,0.0,0.03725,0.0,0.043799784,0.0,0.0,0.006708211,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.009478535,0.0,0.0,0.013484985,0.0,0.0,0.05939169,0.0,0.04461869,0.057005752,0.0,0.0,0.0,0.002168596,0.0034477583,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.09048139,0.0,0.02214435,0.024556166,0.0,0.0,0.0,0.007080889,0.0,0.0,0.0,0.0,0.0,0.07518889,0.009761876,0.0,0.0,0.013833673,0.0,0.0,0.0,0.0,0.0,0.0,0.011720558,0.0,0.0,0.0,0.0,0.0,0.07787659,0.02128338,0.0,0.0,0.0,0.0,0.0,0.008046605,0.1935188]}";
    }

}
