package com.payment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.navigation.NavigationView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.payment.databinding.ActivityMainBinding;
import com.payment.model.viewmodel.RegistrationViewModel;
import com.payment.ui.AddCardFragment;
import com.payment.ui.LoginFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding binding;
    private Boolean isFabOpen = false;
    public RegistrationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        viewModel = ViewModelProviders.of(this).get(RegistrationViewModel.class);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_container_view, new LoginFragment())
                .commit();

        Toolbar mToolbar = findViewById(R.id.toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        mToolbar.setTitle(getString(R.string.toolbar_name));

        binding.appBarContents.fab.setOnClickListener(v -> anim());
        binding.appBarContents.fab1Qr.setOnClickListener(v -> {
            anim();
            new IntentIntegrator(this).initiateScan();
        });
        binding.appBarContents.fab2Card.setOnClickListener(v -> {
            anim();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_container_view, new AddCardFragment())
                    .addToBackStack(null)
                    .commit();
        });
    }

    public void anim() {
        if (isFabOpen) {
            binding.appBarContents.fab1Qr.hide();
            binding.appBarContents.fab2Card.hide();
            isFabOpen = false;
        } else {
            binding.appBarContents.fab1Qr.show();
            binding.appBarContents.fab2Card.show();
            isFabOpen = true;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_test1:
                Toast.makeText(this, "test1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_test2:
                Toast.makeText(this, "test2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_test3:
                Toast.makeText(this, "test3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_test4:
                Toast.makeText(this, "test4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_test1_1:
                Toast.makeText(this, "test5", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_test1_2:
                Toast.makeText(this, "test6", Toast.LENGTH_SHORT).show();
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == IntentIntegrator.REQUEST_CODE) {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result == null) {
                Toast.makeText(this, getString(R.string.re_scan_error), Toast.LENGTH_SHORT).show();
            } else {
                Log.e("scan Url-> ",""+result.getContents());
                viewModel.scanUrlLiveData.setValue(result.getContents());
                if (viewModel.scanUrlLiveData.getValue() != null){
                    viewModel.scanRequest();
                    //TODO:결제창띄우기
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
