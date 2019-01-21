package com.wherismyvehicle.whereismyvehicle.Views;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.wherismyvehicle.whereismyvehicle.Data.Authentication.AuthenticationSingleton;


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

        AuthenticationSingleton.instantiate(this);

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
                    if(AuthenticationSingleton.getInstance().isAuthenticated()) {
                        replaceFragment(profileFragment);
                    }
                    else {
                        replaceFragment(loginFragment);
                    }

                    break;
                //case R.id.nav_sightings:
                //    replaceFragment(sightingsFragment);
                //    break;
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
        fragment.onClose(new Runnable(){
            @Override
            public void run() {
                replaceFragment(loginFragment);
            }
        });
        return fragment;
    }

    private LoginFragment createLoginFragment() {
        LoginFragment fragment = new LoginFragment();
        fragment.setOnLogin(new Runnable(){
            @Override
            public void run() {
                replaceFragment(profileFragment);
            }
        });
        fragment.setOnRegister(new Runnable(){
            @Override
            public void run() {
                replaceFragment(registerFragment);
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
