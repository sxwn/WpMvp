package com.xiaowei.wpmvp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.xiaowei.wpmvp.view.WaveView;
import com.xiaowei.wpmvp.view.WaveView1;
import com.xiaowei.wpmvp.view.WaveView2;

public class WaveActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wave_layout);
        WaveView1 waveView=findViewById(R.id.waveView);
    }
}
