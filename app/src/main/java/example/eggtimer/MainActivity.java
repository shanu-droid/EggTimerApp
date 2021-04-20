package example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextClock;
import android.widget.TextView;

import java.sql.Time;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {
     TextView timerText;
    SeekBar timerseek;
    ImageView newimage ;
    ImageView oldimage;
    TextView pop;
    CountDownTimer countDownTimer;
    Button stoping;

    public void resetTimer(){
        timerText.setText("0:30");
        timerseek.setProgress(30);
        timerseek.setEnabled(true);    }

    public void OnStopping(View view){
       countDownTimer.cancel();
    }
    public void OnClicking(View view){
        pop.animate().alpha(0).setDuration(1000);
        newimage.animate().alpha(0).setDuration(1000);
        oldimage.animate().alpha(1).setDuration(1000);

        countDownTimer = new CountDownTimer(timerseek.getProgress()*1000 + 100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            updateTimer((int)millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                oldimage.animate().alpha(0).setDuration(700);
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.finishsound);
                mediaPlayer.start();
                newimage.animate().alpha(1).setDuration(1000);
                pop.animate().alpha(1).setDuration(1000);
                resetTimer();

            }
        }.start();
    }
    public void updateTimer(int secondLeft){
        int minute = secondLeft / 60;
        int seconds = secondLeft % 60;

        if(seconds < 10) {
            timerText.setText(minute + ":0" + seconds);
        }
        else{
            timerText.setText(minute + ":" + seconds);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newimage = findViewById(R.id.newchick);
        oldimage = findViewById(R.id.mychick);
        pop = findViewById(R.id.timeout);
        stoping = findViewById(R.id.stop);

        timerText = findViewById(R.id.myTime);
        timerseek = findViewById(R.id.seekBar);

        timerseek.setMax(600);
        timerseek.setProgress(30);

        timerseek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
