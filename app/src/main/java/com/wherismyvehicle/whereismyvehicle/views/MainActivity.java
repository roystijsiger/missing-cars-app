package com.wherismyvehicle.whereismyvehicle.views;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.wherismyvehicle.whereismyvehicle.data.authentication.Singleton;


public class MainActivity extends AppCompatActivity {

    private VehiclesFragment vehiclesFragment;
    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;
    private ProfileFragment profileFragment;
    private MyVehiclesFragment myVehiclesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Singleton.instantiate(this);

        vehiclesFragment = new VehiclesFragment();
        loginFragment = createLoginFragment();
        registerFragment = createRegisterFragment();
        profileFragment = createProfileFragment();
        myVehiclesFragment = new MyVehiclesFragment();

        replaceFragment(vehiclesFragment);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch(menuItem.getItemId()){
                case R.id.nav_profile:
                    if(Singleton.getInstance().isAuthenticated()) {
                        replaceFragment(profileFragment);
                    }
                    else {
                        replaceFragment(loginFragment);
                    }
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

        fragment.setOnOpenMissingVehicles(new Runnable() {
            @Override
            public void run() {
                replaceFragment(myVehiclesFragment);
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
