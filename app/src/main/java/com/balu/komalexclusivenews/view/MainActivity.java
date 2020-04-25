package com.balu.komalexclusivenews.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.balu.komalexclusivenews.R;
import com.balu.komalexclusivenews.view.covid.CovidWorldSummaryFragment;
import com.balu.komalexclusivenews.view.cricket.CricHomeFragment;
import com.balu.komalexclusivenews.view.cricket.NewMatchesFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private String selectedCountry;
    private View welcomeAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        welcomeAnim = findViewById(R.id.welocome_anim);
        welcomeAnim.setVisibility(View.VISIBLE);
        dl = findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.top_headlines:
                        welcomeAnim.setVisibility(View.GONE);
                        openCountryDialog();
                        break;
                    case R.id.everything:
                        Toast.makeText(MainActivity.this, "Everything",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.sources:
                        Toast.makeText(MainActivity.this, "Sources",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.cricket:
                         welcomeAnim.setVisibility(View.GONE);
                         launchFragment(null, new CricHomeFragment());
                        break;
                    case R.id.covid:
                        welcomeAnim.setVisibility(View.GONE);
                         launchFragment(null, new CovidWorldSummaryFragment());
                         break;
                    case R.id.whether:
                        welcomeAnim.setVisibility(View.GONE);
                        break;
                    default:
                        return true;
                }
                dl.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(t.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    public void openCountryDialog(){
        //setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Choose an country");
        // add a radio button list
        final String[] animals = {"ae", "ar", "at", "au", "be", "bg", "br", "ca", "ch", "cn", "co", "cu", "cz", "de", "eg", "fr", "gb", "gr", "hk", "hu", "id", "ie", "il",
        "in", "it", "jp", "kr", "lt", "lv", "ma", "mx", "my", "ng", "nl", "no", "nz", "ph", "pl", "pt", "ro", "rs", "ru", "sa", "se",
        "sg", "si", "sk", "th", "tr", "tw", "ua", "us", "ve", "za"};

        builder.setSingleChoiceItems(animals, 23, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedCountry = animals[which];
            }
        });
        // add OK and Cancel buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                OpenTopHeadLines();
            }
        });
        builder.setNegativeButton("Cancel", null);
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void OpenTopHeadLines(){
        TopHeadLinesFragment topHeadLinesFragment = new TopHeadLinesFragment();
        Bundle bundle = new Bundle();
        bundle.putString("countrycode", selectedCountry);
        topHeadLinesFragment.setArguments(bundle);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, topHeadLinesFragment);
        fragmentTransaction.commit();
    }

    public void launchFragment(Bundle bundle, Fragment fragment){
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

