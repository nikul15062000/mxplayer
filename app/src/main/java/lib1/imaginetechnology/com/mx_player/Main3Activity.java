package lib1.imaginetechnology.com.mx_player;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;

public class Main3Activity extends AppCompatActivity {

    VideoView videoView;
    SeekBar seekBar;
    Button play;
    String path;
    int a=0;
    Uri uri;
    Handler h;
    int tot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        videoView=findViewById(R.id.videoView);
        seekBar=findViewById(R.id.seekBar);

        play=findViewById(R.id.button);
        path=getIntent().getStringExtra("vedio");
        tot=getIntent().getIntExtra("total",0);
        Toast.makeText(this, String.valueOf(tot), Toast.LENGTH_SHORT).show();
        uri=Uri.parse(path);

        videoView.setVideoURI(uri);
        videoView.start();
        h=new Handler();


        h.postDelayed(r,0);

        MediaController mediaController=new MediaController(this);
        videoView.setMediaController(mediaController);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                seekBar.setMax(mp.getDuration());
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                h.removeCallbacks(r);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                videoView.seekTo(seekBar.getProgress());
                videoView.start();
                h.postDelayed(r,0);
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a%2==0) {
                    videoView.start();
                    play.setText("pause");
                }
                else
                {
                    videoView.pause();
                    play.setText("play");
                }
                a++;
            }
        });
    }

    Runnable r=new Runnable() {
        @Override
        public void run() {
            seekBar.setProgress(videoView.getCurrentPosition());
            h.postDelayed(r,100);
        }
    };

    public void next_fun(View view) {

    }

    public void prev_fun(View view) {
    }
}
