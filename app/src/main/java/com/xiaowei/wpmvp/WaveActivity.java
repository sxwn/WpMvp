package com.xiaowei.wpmvp;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.xiaowei.wpmvp.view.WaveView;

public class WaveActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wave_layout);
        WaveView waveView=findViewById(R.id.waveView);
        waveView.startAnimation();
    }
}
