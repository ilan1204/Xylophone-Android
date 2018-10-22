package net.sagital.xylophonepm;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // Helpful Constants
    private final int NR_OF_SIMULTANEOUS_SOUNDS = 7;
    private final float LEFT_VOLUME = 1.0f;
    private final float RIGHT_VOLUME = 1.0f;
    private final int NO_LOOP = 0;
    private final int PRIORITY = 1;
    private final float NORMAL_PLAY_RATE = 1.0f;

    // TODO: Add member variables here
    private int mCSoundId;
    private int mDSoundId;
    private int mESoundId;
    private int mFSoundId;
    private int mGSoundId;
    private int mASoundId;
    private int mBSoundId;
    private SoundPool mSoundPool;
    public Button btnArr[] = new Button[7];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mSoundPool.release();
        mSoundPool = null;
    }

    @Override
    protected void onResume() {
        super.onResume();

        // TODO: Create a new SoundPool
        mSoundPool = new SoundPool(NR_OF_SIMULTANEOUS_SOUNDS,AudioManager.STREAM_MUSIC,0);

        // TODO: Load and get the IDs to identify the sounds
        InitSound();

        // TODO: SetUp all LongClickListner
        setUpOnLongClickListner();
    }

    public void InitSound(){
        Context mContext = getApplicationContext();
        // fill your sounds
        mCSoundId = mSoundPool.load(mContext, R.raw.note1_c, 1);
        mDSoundId = mSoundPool.load(mContext, R.raw.note2_d, 1);
        mESoundId = mSoundPool.load(mContext, R.raw.note3_e, 1);
        mFSoundId = mSoundPool.load(mContext, R.raw.note4_f, 1);
        mGSoundId = mSoundPool.load(mContext, R.raw.note5_g, 1);
        mASoundId = mSoundPool.load(mContext, R.raw.note6_a, 1);
        mBSoundId = mSoundPool.load(mContext, R.raw.note7_b, 1);
    }

    public void setUpOnLongClickListner(){
        // Set OnLongClickListner to Buttons
         btnArr[0] = findViewById(R.id.c_key);
         btnArr[1] = findViewById(R.id.d_key);
         btnArr[2] = findViewById(R.id.e_key);
         btnArr[3] = findViewById(R.id.f_key);
         btnArr[4] = findViewById(R.id.g_key);
         btnArr[5] = findViewById(R.id.a_key);
         btnArr[6] = findViewById(R.id.b_key);

         for(int l=0; l<=btnArr.length-1; l++){
            btnArr[l].setOnLongClickListener(myLongClicker);
         }
    }

    // TODO: Add the play methods triggered by the buttons ONLONGCLICK
    View.OnLongClickListener myLongClicker = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            Log.d("tag", "onLongClick: LONG CLICK FIRED");
            // SLEEP FUNCTION LIKE IN B4X.COM ## START ##
            Handler handler1 = new Handler();
            for (int i=0; i<=NR_OF_SIMULTANEOUS_SOUNDS-1;i++){
                final int Pos = i;
                handler1.postDelayed(new Runnable() {
                  @Override
                  public void run() {
                       getMySoundFile(btnArr[Pos]);
                  }
                }, 200*i);
             }
             // ## END ##
            return true;
        }
    };

    // TODO: Add the play methods triggered by the buttons ONCLICK
    public void ButtonOnClick(View v){
        getMySoundFile(v);
    }

    public void getMySoundFile(View v){
        switch (v.getId()) {
            case R.id.c_key:
                mSoundPool.play(mCSoundId, LEFT_VOLUME, RIGHT_VOLUME, PRIORITY, NO_LOOP, NORMAL_PLAY_RATE);
                break;
            case R.id.d_key:
                mSoundPool.play(mDSoundId, LEFT_VOLUME, RIGHT_VOLUME, PRIORITY, NO_LOOP, NORMAL_PLAY_RATE);
                break;
            case R.id.e_key:
                mSoundPool.play(mESoundId, LEFT_VOLUME, RIGHT_VOLUME, PRIORITY, NO_LOOP, NORMAL_PLAY_RATE);
                break;
            case R.id.f_key:
                mSoundPool.play(mFSoundId,LEFT_VOLUME, RIGHT_VOLUME, PRIORITY, NO_LOOP, NORMAL_PLAY_RATE);
                break;
            case R.id.g_key:
                mSoundPool.play( mGSoundId, LEFT_VOLUME, RIGHT_VOLUME, PRIORITY, NO_LOOP, NORMAL_PLAY_RATE);
                break;
            case R.id.a_key:
                mSoundPool.play(mASoundId, LEFT_VOLUME, RIGHT_VOLUME, PRIORITY, NO_LOOP, NORMAL_PLAY_RATE);
                break;
            case R.id.b_key:
                mSoundPool.play(mBSoundId, LEFT_VOLUME, RIGHT_VOLUME, PRIORITY, NO_LOOP, NORMAL_PLAY_RATE);
                break;
        }
    }
}
