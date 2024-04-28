package com.example.helloworld;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.helloworld.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {

    private static final int REQUEST_FINE_LOCATION = 1;
    private ActivityMain2Binding binding;
    private ApmReceiver apmBr;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apmBr = new ApmReceiver();

        Toolbar toolbar = findViewById(R.id.gameToolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main2);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main2);
        setupToolbarNavigation(toolbar, navController);

        toolbar.setOnClickListener(v -> {
            MenuItem menuItem = toolbar.getMenu().findItem(R.id.navigation_home);
            if (menuItem != null) {
                NavigationUI.onNavDestinationSelected(menuItem, navController);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                Navigation.findNavController(this, R.id.nav_host_fragment_activity_main2)
                        .navigate(R.id.navigation_home);
                return true;
            case R.id.navigation_dashboard:
                Navigation.findNavController(this, R.id.nav_host_fragment_activity_main2)
                        .navigate(R.id.navigation_dashboard);
                return true;
            case R.id.navigation_notifications:
                Navigation.findNavController(this, R.id.nav_host_fragment_activity_main2)
                        .navigate(R.id.navigation_notifications);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupToolbarNavigation(Toolbar toolbar, NavController navController) {
        NavigationUI.setupWithNavController(toolbar, navController);
    }

    public void requestCheck() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_FINE_LOCATION);
        } else {
            Log.d(TAG, "Käyttöoikeus sijaintitietoihin on jo myönnetty");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_FINE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity2.this, "Sijaintitietolupa myönnetty", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity2.this, "Sijaintitietolupa evätty", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        this.registerReceiver(apmBr, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
