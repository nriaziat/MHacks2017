package com.example.shashank.pilldispenser;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.v4.view.*;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TimePicker;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.cognito.CognitoSyncManager;
import com.amazonaws.mobileconnectors.cognito.DefaultSyncCallback;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitosync.*;
import com.amazonaws.mobileconnectors.cognito.Dataset;

import java.sql.Time;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnClickListener {

    private Toolbar toolbar;
    private CognitoCachingCredentialsProvider credentialsProvider;
    private CognitoSyncManager syncClient;
    private Dataset dataset;
    private Calendar calendar;
    private UserInfo user;
    private Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        calendar = Calendar.getInstance();

        b1 = (Button)findViewById(R.id.button);
        b1.setOnClickListener(this);
        b2 = (Button)findViewById(R.id.button2);
        b2.setOnClickListener(this);
        b3 = (Button)findViewById(R.id.button3);
        b3.setOnClickListener(this);


        //amazonCogInit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.button: { //Add button: Add Pill action
                boolean[] days = new boolean[7];
                boolean meals = false;

                CheckBox check1 = (CheckBox) findViewById(R.id.checkBox);
                CheckBox check2 = (CheckBox) findViewById(R.id.checkBox2);
                CheckBox check3 = (CheckBox) findViewById(R.id.checkBox3);
                CheckBox check4 = (CheckBox) findViewById(R.id.checkBox4);
                CheckBox check5 = (CheckBox) findViewById(R.id.checkBox5);
                CheckBox check6 = (CheckBox) findViewById(R.id.checkBox6);
                CheckBox check7 = (CheckBox) findViewById(R.id.checkBox7);
                CheckBox check8 = (CheckBox) findViewById(R.id.checkBox8);

                if (check1.isChecked()) {
                    days[0] = true;
                }
                if (check2.isChecked()) {
                    days[1] = true;
                }
                if (check3.isChecked()) {
                    days[2] = true;
                }
                if (check4.isChecked()) {
                    days[3] = true;
                }
                if (check5.isChecked()) {
                    days[4] = true;
                }
                if (check6.isChecked()) {
                    days[5] = true;
                }
                if (check7.isChecked()) {
                    days[6] = true;
                }
                if (check8.isChecked()) {
                    meals = true;
                }

                EditText pillName = (EditText)findViewById(R.id.editText10);
                String pillNameS = pillName.getText().toString();

                EditText trayNum = (EditText)findViewById(R.id.editText11);
                int trayNumInt = Integer.parseInt(pillName.getText().toString());

                EditText pillIntake = (EditText)findViewById(R.id.editText12);
                int pillIntakeInt = Integer.parseInt(pillName.getText().toString());

                TimePicker time = (TimePicker) findViewById(R.id.timePicker);
                int minutes = time.getHour() + time.getMinute();

                user.addPill(pillNameS,);
                break;
            }

            case R.id.button2: { //Remove button

                break;
            }
            case R.id.button3: { //Settings button

                break;
            }
        }
    }


    /*private void amazonCogInit() {

        // Initialize the Amazon Cognito credentials provider
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                getApplicationContext(),
                "us-west-2:06d802e1-25a7-4e1c-b78a-5286fbba33d2", // Identity Pool ID
                Regions.US_WEST_2 // Region
        );

        // Initialize the Cognito Sync client
        CognitoSyncManager syncClient = new CognitoSyncManager(
                getApplicationContext(),
                Regions.US_WEST_2, // Region
                credentialsProvider);

        // Create a record in a dataset and synchronize with the server
        Dataset dataset = syncClient.openOrCreateDataset("myDataset");
        dataset.put("myKey", "myValue");
        dataset.synchronize(new DefaultSyncCallback() {
            @Override
            public void onSuccess(Dataset dataset, List newRecords) {
                //Your handler code here
            }
        });
    }*/

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            //Checkboxes in Add

            case R.id.checkBox:
                if (checked) {

                }
                else {

                }
                break;
            case R.id. checkBox2:
                if (checked) {

                }
                else {

                }
                break;
            case R.id.checkBox3:
                if (checked) {

                }
                else {

                }
                break;
            case R.id. checkBox4:
                if (checked) {

                }
                else {

                }
                break;
            case R.id.checkBox5:
                if (checked) {

                }
                else {

                }
                break;
            case R.id. checkBox6:
                if (checked) {

                }
                else {

                }
                break;
        }
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);

        int viewId = item.getItemId();

        switch (viewId) {
            case R.id.add:
                fragment = new AddFragment();
                title  = "Add";
                break;

            case R.id.removePill:
                fragment = new RemoveFragment();
                title = "Remove";
                break;

            case R.id.schedule:
                fragment = new ScheduleFragment();
                title = "Schedule";
                break;

            case R.id.settings:
                fragment = new SettingFragment();
                title = "Settings";
                break;

        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_container, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
