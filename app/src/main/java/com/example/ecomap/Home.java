package com.example.ecomap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Home extends AppCompatActivity {

    ImageView bgapp, clover;
    LinearLayout textsplash, texthome, menus;
    Animation frombottom;
    private CardView paperCard, plasticCard, riskyCard, showMapCard;
    private Button buttonEcoDirectory, buttonProgramm, buttonNews, buttonShowMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombotton);


        bgapp = (ImageView) findViewById(R.id.bgapp);
        clover = (ImageView) findViewById(R.id.clover);
        textsplash = (LinearLayout) findViewById(R.id.textsplash);
        texthome = (LinearLayout) findViewById(R.id.texthome);
        menus = (LinearLayout) findViewById(R.id.menus);

        bgapp.animate().translationY(-2200).setDuration(800).setStartDelay(300);
        clover.animate().alpha(0).setDuration(800).setStartDelay(600);
        textsplash.animate().translationY(140).alpha(0).setDuration(800).setStartDelay(300);

        texthome.startAnimation(frombottom);
        menus.startAnimation(frombottom);

        buttonShowMap = (Button) findViewById(R.id.buttonShowMap);
        buttonEcoDirectory = (Button) findViewById(R.id.buttonEcoDirectory);
        buttonProgramm = (Button) findViewById(R.id.buttonProgramm);
        buttonNews = (Button) findViewById(R.id.buttonNews);


        buttonEcoDirectory.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openEcoDirectory();
            }
        });

        buttonProgramm.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        buttonNews.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        buttonShowMap.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openMapActivity();
            }
        });
    }
    public void openMapActivity()
    {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
    public void openEcoDirectory()
    {
        Intent intent = new Intent(this, EcoDirectoryActivity.class );
        startActivity(intent);
    }
}
