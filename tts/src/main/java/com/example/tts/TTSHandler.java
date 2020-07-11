package com.example.tts;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.blankj.utilcode.util.LogUtils;
import com.example.tts.utils.FileUtils;
import com.example.tts.utils.OnVoicePlayListener;
import com.shuangtu.prison.common.constant.Global;
import com.unisound.client.SpeechConstants;
import com.unisound.client.SpeechSynthesizer;
import com.unisound.client.SpeechSynthesizerListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TTSHandler implements SpeechSynthesizerListener {

    private static final String TAG = "TTSHandler";
    private static volatile TTSHandler instance = null;
    private boolean isInitSuccess = false;

    private SpeechSynthesizer mTTSPlayer;
    public static String SAMPLE_DIR = null;
    // public static final String SAMPLE_DIR = "/storage/emulated/0/unisound/tts/";
    public static final String FRONTEND_MODEL = "/frontend_model";
    public static final String BACKEND_MODEL = "/backend_lzl";
    private static final String APPKEY = "6gizplcn7rxxslhb2m664juh3xzfsj5tpx26taiq";//这里换成你的key和secret
    private static final String SECRET = "559a70dc0c1a6a16896fe2494d9a51f2";
    private OnVoicePlayListener onVoicePlayListener;

    public void setOnVoicePlayListener(OnVoicePlayListener onVoicePlayListener) {
        this.onVoicePlayListener = onVoicePlayListener;
    }

    private TTSHandler() {
    }

    public static TTSHandler getInstance() {
        if (instance == null) {
            synchronized (TTSHandler.class) {
                if (instance == null) {
                    instance = new TTSHandler();
                }
            }
        }
        return instance;
    }

    public static void initialization() {
        LogUtils.aTag(TAG, "initialization");
        TTSHandler.getInstance().create();
    }

    public void create() {
        SAMPLE_DIR = FileUtils.getDiskCacheDir(Global.mContext, "tts").toString();
        Log.e(TAG, "SAMPLE_DIR:" + SAMPLE_DIR);
        FileUtils.getInstance(Global.mContext).copyAssetsToSD("OfflineTTSModels", SAMPLE_DIR).setFileOperateCallback(new FileUtils.FileOperateCallback() {
            @Override
            public void onSuccess() {
                // TODO: 文件复制成功时，主线程回调
                Log.e(TAG, "文件复制成功时，主线程回调");
                init();
            }

            @Override
            public void onFailed(String error) {
                // TODO: 文件复制失败时，主线程回调
                Log.e(TAG, "文件复制失败时，主线程回调");
            }
        });

    }

    public void init() {
        try {
            Context context = Global.mContext;
            //判断文件是否完整
            File _FrontendModelFile = new File(SAMPLE_DIR + FRONTEND_MODEL);
            File _BackendModelFile = new File(SAMPLE_DIR + BACKEND_MODEL);
            Log.e(TAG, "file1:" + _FrontendModelFile.toString() + " file2:" + _BackendModelFile.toString());
            //校验文件的完整性
            String file1 = getFileMD5(_FrontendModelFile);
            String file2 = getFileMD5(_BackendModelFile);

            mTTSPlayer = new SpeechSynthesizer(context, APPKEY, SECRET);
            mTTSPlayer.setOption(SpeechConstants.TTS_SERVICE_MODE, SpeechConstants.TTS_SERVICE_MODE_LOCAL); // 设置本地合成
            File file = new File(SAMPLE_DIR);
            if (!file.exists()) {
                file.mkdirs();
            }
            mTTSPlayer.setOption(SpeechConstants.TTS_KEY_FRONTEND_MODEL_PATH, SAMPLE_DIR + FRONTEND_MODEL);// 设置前端模型
            mTTSPlayer.setOption(SpeechConstants.TTS_KEY_BACKEND_MODEL_PATH, SAMPLE_DIR + BACKEND_MODEL);// 设置后端模型
//            setOption(int key, java.lang.Object value)
//            设置合成相关参数
//            示例：
//            设置合成语速 SpeechConstants.TTS_KEY_VOICE_SPEED 范围 0 ~ 100 int
//            设置合成音高 SpeechConstants.TTS_KEY_VOICE_PITCH 范围 0 ~ 100 int
//            设置合成音量 SpeechConstants.TTS_KEY_VOICE_VOLUME 范围 0 ~ 100 int
//            设置是否将英文按拼音读 SpeechConstants.TTS_KEY_IS_READ_ENLISH_IN_PINYIN 如：wang->王 boolean
//            设置语音结尾段的静音时长 SpeechConstants.TTS_KEY_BACK_SILENCE 0 ~ 1000 单位ms int
//            设置语音开始段的静音时长 SpeechConstants.TTS_KEY_FRONT_SILENCE 0 ~ 1000 单位ms int
            mTTSPlayer.setOption(SpeechConstants.TTS_KEY_VOICE_SPEED, 45);
            mTTSPlayer.setOption(SpeechConstants.TTS_KEY_VOICE_PITCH, 55);
//            mTTSPlayer.setOption(SpeechConstants.TTS_KEY_VOICE_VOLUME,200);
            //文字加数字，“玩啊”+106  106会读一百零六    “H”+106   会一个一个读出来 H 1 0 6
//            mTTSPlayer.setOption(SpeechConstants.TTS_KEY_IS_READ_ENLISH_IN_PINYIN,false);
            mTTSPlayer.setOption(SpeechConstants.TTS_KEY_BACK_SILENCE, 300);//设置尾音
//            mTTSPlayer.setOption(SpeechConstants.TTS_KEY_FRONT_SILENCE,1000);//设置开始音
            mTTSPlayer.setOption(SpeechConstants.TTS_KEY_PLAY_START_BUFFER_TIME, 250);//语音开始缓冲时间
//            mTTSPlayer.setOption(SpeechConstants.TTS_KEY_STREAM_TYPE, AudioManager.STREAM_SYSTEM);
            mTTSPlayer.setTTSListener(this);// 设置回调监听
            mTTSPlayer.init(null);// 初始化合成引擎
        } catch (Exception e) {
//            e.printStackTrace();
            Toast.makeText(Global.mContext, "下载离线包后即可使用语音合成功能！", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public void setOption(int option, int value) {
        mTTSPlayer.setOption(option, value);
    }

    /**获取文件的md5码，判断文件的完整性*/

    /**
     * 获取文件的md5码，判断文件的完整性
     */
    private static String getFileMD5(File file) throws NoSuchAlgorithmException, IOException {
        if (!file.exists() || !file.isFile()) {
//            不是文件,或者不存在
            return "";
        }
        MessageDigest digest;
        FileInputStream in;
        byte buffer[] = new byte[1024];
        int len;
        digest = MessageDigest.getInstance("MD5");
        in = new FileInputStream(file);
        while ((len = in.read(buffer, 0, 1024)) != -1) {
            digest.update(buffer, 0, len);
        }
        in.close();
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }

    /**
     * 一个字节一个字节读
     */
    public static void speckText(String msg) {
        TTSHandler.getInstance().speak(getS(msg));
    }

    /**
     * 语义识别朗读
     */
    public static void speeckTrueText(String msg) {
        if (msg == null || "".equals(msg)) {
            msg = "";
        }
        if (msg.length() > 25) {
            msg = msg.substring(0, 15);
        }
        TTSHandler.getInstance().speak(msg);
    }

    public static String getS(String msg) {
        if (msg == null || "".equals(msg)) {
            return "";
        }
        char[] chars = msg.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            sb.append(aChar + "\"");
        }
        return sb.toString();
    }

    public void speak(String msg) {
        try {
            if (isInitSuccess) {
                mTTSPlayer.playText(msg);
            } else {
                init();
            }
        } catch (Exception e) {
            Log.e(TAG, "下载离线包后即可使用语音合成功能！");
        }
    }

    public void stop() {
        mTTSPlayer.stop();
    }

    public void pause() {
        mTTSPlayer.pause();
    }

    public void resume() {
        mTTSPlayer.resume();
    }

    public void release() {
        if (null != mTTSPlayer) {
            // 释放离线引擎
            mTTSPlayer.release(SpeechConstants.TTS_RELEASE_ENGINE, null);
        }
    }

    @Override
    public void onEvent(int type) {
        switch (type) {
            case SpeechConstants.TTS_EVENT_INIT:
                isInitSuccess = true;
                break;
            case SpeechConstants.TTS_EVENT_SYNTHESIZER_START:
                // 开始合成回调
                Log.i(TAG, "beginSynthesizer");
                break;
            case SpeechConstants.TTS_EVENT_SYNTHESIZER_END:
                // 合成结束回调
                Log.i(TAG, "endSynthesizer");
                break;
            case SpeechConstants.TTS_EVENT_BUFFER_BEGIN:
                // 开始缓存回调
                Log.i(TAG, "beginBuffer");
                break;
            case SpeechConstants.TTS_EVENT_BUFFER_READY:
                // 缓存完毕回调
                Log.i(TAG, "bufferReady");
                break;
            case SpeechConstants.TTS_EVENT_PLAYING_START:
                // 开始播放回调
                Log.i(TAG, "onPlayBegin");
                break;
            case SpeechConstants.TTS_EVENT_PLAYING_END:
                // 播放完成回调
                Log.i(TAG, "onPlayEnd");
                if (onVoicePlayListener != null) {
                    onVoicePlayListener.onPlayEnd();
                }
                break;
            case SpeechConstants.TTS_EVENT_PAUSE:
                // 暂停回调
                Log.i(TAG, "pause");
                break;
            case SpeechConstants.TTS_EVENT_RESUME:
                // 恢复回调
                Log.i(TAG, "resume");
                break;
            case SpeechConstants.TTS_EVENT_STOP:
                // 停止回调

                Log.i(TAG, "stop");
                break;
            case SpeechConstants.TTS_EVENT_RELEASE:
                // 释放资源回调
                Log.i(TAG, "release");
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(int type, String errorMSG) {
        Log.e(TAG, "语音合成错误回调: " + errorMSG);
    }
    /**如果将assets里面的文件放在你自己的assets下，就需要用到下面的方法*/
//    public static void copyAssetsFile2SDCard(Context context, String fileName, String path) {
//        InputStream is=null;
//        FileOutputStream fos=null;
//        try {
//            is= context.getAssets().open(fileName);
//            fos= new FileOutputStream(new File(path));
//            byte[] buffer = new byte[1024];
//            int byteCount = 0;
//            while ((byteCount = is.read(buffer)) != -1) {// 循环从输入流读取buffer字节
//                fos.write(buffer, 0, byteCount);// 将读取的输入流写入到输出流
//            }
//           fos.flush();
//        } catch (IOException e) {
//            Log.e(TAG, "copyAssetsFile2SDCard: " + e.toString());
//        } finally {
//            if(fos!=null){
//                try {
//                    fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if(is!=null){
//                try {
//                    is.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
