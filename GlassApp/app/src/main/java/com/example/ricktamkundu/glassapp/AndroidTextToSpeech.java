package com.example.ricktamkundu.glassapp;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Display;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

/**
 * Created by ricktamkundu on 14-06-2017.
 */

public class AndroidTextToSpeech implements TextToSpeech.OnInitListener{
    private Context mContext;
boolean initialized;
    String queuedText;
    private TextToSpeech tts;

    public AndroidTextToSpeech(Context context) {
        Log.e("TEXT TO SPEECH ", "controller");
        mContext = context;
        tts = new TextToSpeech(context, this);
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            initialized = true;
            tts.setLanguage(Locale.ENGLISH);

            if (queuedText != null) {
                speak(queuedText);
            }
        }
    }

    public void speak(String text) {
        // If not yet initialized, queue up the text.
        if (!initialized) {
            queuedText = text;
            return;
        }
        queuedText = null;
        // Before speaking the current text, stop any ongoing speech.
        tts.stop();
        // Speak the text.
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }


        /*private static android.speech.tts.AndroidTextToSpeech textToSpeech;
    private static AndroidTextToSpeech tts;
   // private AndroidTextToSpeech tts;
        public static void init(final Context context) {
            tts = new AndroidTextToSpeech();
            if (textToSpeech == null) {
                textToSpeech = new android.speech.tts.AndroidTextToSpeech(context, new android.speech.tts.AndroidTextToSpeech.OnInitListener() {


                    @Override
                    public void onInit(int status) {

                        if (status == AndroidTextToSpeech.SUCCESS) {

                            int result = tts.setLanguage(Locale.US);

                            if (result == AndroidTextToSpeech.LANG_MISSING_DATA
                                    || result == AndroidTextToSpeech.LANG_NOT_SUPPORTED) {
                                Log.e("TTS", "This Language is not supported");
                            } else {
                                btnSpeak.setEnabled(true);
                                speakOut();
                            }

                        } else {
                            Log.e("TTS", "Initilization Failed!");
                        }

                    }
                });
            }
        }

        public static void speak(final String text) {
            textToSpeech.speak(text, android.speech.tts.AndroidTextToSpeech.QUEUE_FLUSH, null);
        }*/
    }

