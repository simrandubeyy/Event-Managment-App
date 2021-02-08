package com.example.signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
public class Check extends AppCompatActivity {

   private DrawerLayout drawer;

    GridLayout mainGridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);


        drawer = findViewById(R.id.draw_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,  R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mainGridLayout=(GridLayout) findViewById(R.id.mainGridLayout);

        setSingleEvent(mainGridLayout);

    }

    private void setSingleEvent(GridLayout mainGridLayout) {
        for(int i=0; i<mainGridLayout.getChildCount(); i++)
        {
            CardView cardView = (CardView)  mainGridLayout.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(finalI==0)
                    {
                        Intent intent = new Intent(Check.this,registerevent.class);
                        startActivity(intent);
                        }
                    else if(finalI==1)
                    {
                        Intent intent = new Intent(Check.this,ListView.class);
                        startActivity(intent);
                    }

                    else if(finalI==2)
                    {
                        Intent intent = new Intent(Check.this,registerevent.class);
                        startActivity(intent);
                    }

                    else if(finalI==3)
                    {
                        Intent intent = new Intent(Check.this,Profile.class);
                        startActivity(intent);
                    }

                    else if(finalI==4)
                    {
                        Intent intent = new Intent(Check.this,share.class);
                        startActivity(intent);
                    }

                    else if(finalI==5)
                    {
                        Intent intent = new Intent(Check.this,Connect.class);
                        startActivity(intent);
                    }

                    else if(finalI==6)
                    {
                        Intent intent = new Intent(Check.this,About.class);
                        startActivity(intent);
                    }

                    else if(finalI==7)
                    {
                        Intent intent = new Intent(Check.this,FAQ.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }

    public void onBackPressed() {
           if(drawer.isDrawerOpen(GravityCompat.START))
           {
               drawer.closeDrawer(GravityCompat.START);
           }else {
             super.onBackPressed();
           }
    }
    }
