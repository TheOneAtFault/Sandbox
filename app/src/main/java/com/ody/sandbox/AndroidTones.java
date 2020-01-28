package com.ody.sandbox;

import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Handler;

public class AndroidTones {
    private static void PlayGoodTone() {
        final ToneGenerator tone = new ToneGenerator(AudioManager.STREAM_DTMF, 100);
        Handler handler = new Handler();
        tone.startTone(ToneGenerator.TONE_CDMA_SOFT_ERROR_LITE);

        handler.postDelayed(new Runnable() {
            public void run() {
                tone.release();
            }
        }, 125);
    }

    private static void PlayBadTone() {
        final ToneGenerator tone = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
        Handler handler = new Handler();
        tone.startTone(ToneGenerator.TONE_SUP_ERROR);

        handler.postDelayed(new Runnable() {
            public void run() {
                tone.release();
            }
        }, 330);
    }
}
