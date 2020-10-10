package developers.android.attendencemanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WelcomeScreen extends AppCompatActivity {
    ImageView imgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        imgView=findViewById(R.id.imgView);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.anim_fade);
        imgView.startAnimation(animation);

        Thread thread=new Thread(){

            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent intent=new Intent(getApplicationContext(),LogInActivity.class);
                    startActivity(intent);
                    finish();
                    super.run();
                } catch (InterruptedException e) {
                    // e.printStackTrace();
                }
            }
        };
        thread.start();

    }
}