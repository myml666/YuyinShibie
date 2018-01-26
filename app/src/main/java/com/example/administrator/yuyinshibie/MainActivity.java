package com.example.administrator.yuyinshibie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.unisound.client.SpeechConstants;
import com.unisound.client.SpeechUnderstander;
import com.unisound.client.SpeechUnderstanderListener;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private EditText et;
    private SpeechUnderstander mSpeechUnderstander;
    private boolean flag=false;
    private String mResult="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et= (EditText) findViewById(R.id.et);
        mSpeechUnderstander = new SpeechUnderstander(this,"ip33wr6ojsxtbyez7twyh2myijiwmeoshuvsz6yh","ae695061b5a356057c276b2f338e5921");
        mSpeechUnderstander.setOption(SpeechConstants.NLU_ENABLE, false);
        mSpeechUnderstander.setListener(new SpeechUnderstanderListener() {
            @Override
            public void onEvent(int i, int i1) {
                switch (i) {
                    case SpeechConstants.ASR_EVENT_VAD_TIMEOUT:
                        // 收到用户停止说话事件，停止录音
                        break;
                    case SpeechConstants.ASR_EVENT_VOLUMECHANGE:
                        // 说话音量实时返回
                        int volume = (Integer)mSpeechUnderstander.getOption(SpeechConstants.GENERAL_UPDATE_VOLUME);
                        break;
                    case SpeechConstants.ASR_EVENT_SPEECH_DETECTED:
                        //用户开始说话
                        break;
                    case SpeechConstants.ASR_EVENT_RECORDING_START:
                        //录音设备打开，开始识别，用户可以开始说话
                        Toast.makeText(MainActivity.this, "dassdadas", Toast.LENGTH_SHORT).show();
                        break;
                    case SpeechConstants.ASR_EVENT_RECORDING_STOP:
                        // 停止录音，请等待识别结果回调
                        break;
                    case SpeechConstants.ASR_EVENT_NET_END:
                        et.setText(mResult);
                        break;
                }
            }

            @Override
            public void onError(int i, String s) {

            }
            @Override
            public void onResult(int i, String s) {
                switch (i) {
                    case SpeechConstants.ASR_RESULT_NET:
                        // 在线识别结果，通常onResult接口多次返回结果，保留识别结果组成完整的识别内容。
                        // 通常onResult接口多次返回结果，保留识别结果组成完整的识别内容。
                        Log.e("测试",s);
                        if (s.contains("net_asr")) {
                            try {
                                JSONObject json = new JSONObject(s);
                                JSONArray jsonArray = json.getJSONArray("net_asr");
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String status = jsonObject.getString("result_type");
                                if (status.equals("partial")) {
                                    mResult+=(((String)jsonObject.get("recognition_result")).replaceAll("。",""));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        mSpeechUnderstander.init("");
    }
    public void onClick(View v){
        if(flag){
            mSpeechUnderstander.stop();
        }else {
            mResult="";
            mSpeechUnderstander.start();
        }
        flag=!flag;
    }
}
