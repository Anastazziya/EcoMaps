package com.example.ecomap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EcoDirectoryActivity extends AppCompatActivity
{
    private Button buttonPlastic, buttonGlass, buttonPaper, buttonMetal, buttonOther;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eco_directory);

        buttonPlastic = (Button) findViewById(R.id.plastic);
        buttonGlass = (Button) findViewById(R.id.glass);
        buttonPaper = (Button) findViewById(R.id.paper);
        buttonMetal = (Button) findViewById(R.id.metal);
        buttonOther = (Button) findViewById(R.id.other);

        buttonPlastic.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openPlastic();
            }
        });

        buttonGlass.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openGlass();
            }
        });

        buttonMetal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openMetal();
            }
        });

        buttonPaper.setOnClickListener((new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openPaper();
            }
        }));

        buttonOther.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openOther();
            }
        });
    }

    public void openPlastic()
    {
        Intent intent = new Intent(this, Plastic.class);
        startActivity(intent);
    }

    public void openGlass()
    {
        Intent intent = new Intent(this, Glass.class);
        startActivity(intent);
    }

    public void openMetal()
    {
        Intent intent = new Intent(this, Metal.class);
        startActivity(intent);
    }

    public void openPaper()
    {
        Intent intent = new Intent(this, Paper.class);
        startActivity(intent);
    }

    public void openOther()
    {
        Intent intent = new Intent(this, Other.class);
        startActivity(intent);
    }
}
