package com.wherismyvehicle.whereismyvehicle.Views;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private VehiclesFragment vehiclesFragment;
    private SightingsFragment sightingsFragment;
    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;
    private ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vehiclesFragment = new VehiclesFragment();
        sightingsFragment = new SightingsFragment();
        loginFragment = createLoginFragment();
        registerFragment = createRegisterFragment();
        profileFragment = createProfileFragment();

        replaceFragment(vehiclesFragment);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch(item.getItemId()){
                case R.id.nav_profile:
                    // TODO: Check auth first
                    replaceFragment(registerFragment);
                    break;
                case R.id.nav_sightings:
                    replaceFragment(sightingsFragment);
                    break;
                case R.id.nav_vehicles:
                    replaceFragment(vehiclesFragment);
                    break;
            }

            return true;
            }
        });
    }

    private ProfileFragment createProfileFragment() {
        ProfileFragment fragment = new ProfileFragment();
        fragment.onLoggedOut(new Runnable(){
            @Override
            public void run() {
                replaceFragment(loginFragment);
            }
        });
        return fragment;
    }

    private RegisterFragment createRegisterFragment() {
        RegisterFragment fragment = new RegisterFragment();
        fragment.onRegistered(new Runnable(){
            @Override
            public void run() {
                replaceFragment(loginFragment);
            }
        });
        return fragment;
    }

    private LoginFragment createLoginFragment() {
        LoginFragment fragment = new LoginFragment();
        fragment.onLoggedIn(new Runnable(){
            @Override
            public void run() {
                replaceFragment(profileFragment);
            }
        });
        return fragment;
    }

    private void replaceFragment(Fragment fragment){
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit();
    }
}
