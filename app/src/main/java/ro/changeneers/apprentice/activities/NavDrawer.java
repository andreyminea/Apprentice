package ro.changeneers.apprentice.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import ro.changeneers.apprentice.R;
import ro.changeneers.apprentice.utils.SharedPrefManager;


public abstract class NavDrawer extends AppCompatActivity implements OnNavigationItemSelectedListener {
    private LinearLayout view_stub; //This is the framelayout to keep your content view
    private NavigationView navigation_view; // The new navigation view from Android Design Library. Can inflate menu resources. Easy
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Menu drawerMenu;
    private String TAG = NavDrawer.class.getSimpleName();

    private String mFullName, mEmail;
    private TextView mFullNameTextView, mEmailTextView;
    private ImageView mProfileImageView;
    SharedPrefManager sharedPrefManager;
    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.app_base_layout);// The base layout that contains your navigation drawer.
        view_stub = (LinearLayout) findViewById(R.id.view_stub);
        navigation_view = (NavigationView) findViewById(R.id.navigation_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, 0, 0);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerToggle.syncState();

        drawerMenu = navigation_view.getMenu();
        navigation_view.setNavigationItemSelectedListener(this);

        //Initializare nume,email,poza
        View header = navigation_view.getHeaderView(0);
        mFullNameTextView = header.findViewById(R.id.nav_header_name);
        mEmailTextView = header.findViewById(R.id.nav_header_email);
        mProfileImageView = header.findViewById(R.id.nav_header_photo);

        // create an object of sharedPreferenceManager and get stored user data
        sharedPrefManager = new SharedPrefManager(mContext);
        mFullName = sharedPrefManager.getName();
        mEmail = sharedPrefManager.getUserEmail();
        String uri = sharedPrefManager.getPhoto();
        Uri mPhotoUri = Uri.parse(uri);

        //Set data gotten from SharedPref to the Navigation Header view
        mFullNameTextView.setText(mFullName);
        mEmailTextView.setText(mEmail);

        Picasso.get().load(mPhotoUri)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(mProfileImageView);


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /* Override all setContentView methods to put the content view to the FrameLayout view_stub
     * so that, we can make other activity implementations looks like normal activity subclasses.
     */
    @Override
    public void setContentView(int layoutResID) {
        if (view_stub != null) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            View stubView = inflater.inflate(layoutResID, view_stub, false);
            view_stub.addView(stubView, lp);
        }
    }

    @Override
    public void setContentView(View view) {
        if (view_stub != null) {
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            view_stub.addView(view, lp);
        }
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        if (view_stub != null) {
            view_stub.addView(view, params);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            mDrawerLayout.closeDrawer(GravityCompat.START, false);
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        mDrawerLayout.closeDrawer(GravityCompat.START);

        final int mItemId = menuItem.getItemId();
        if (mItemId == getNavigationItemID())
        {
            return true;
        }
        onMenuItemClick(mItemId);
        return true;
    }

    protected abstract int getNavigationItemID();

    public void onMenuItemClick(int item) {
        switch (item) {
            case R.id.nav_home:
                Log.d(TAG, "open Home Activity");
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;
            case R.id.nav_chat:
                Log.d(TAG, "open Chat Activity");
                Intent j = new Intent(this, ChatMainActivity.class);
                startActivity(j);
                break;
            case R.id.nav_about:
                Log.d(TAG, "open About Activity");
                Intent a = new Intent(this, JourneyListActivity.class);
                startActivity(a);
                break;
            case R.id.nav_profile:
                Log.d(TAG, "open Profile Activity");
                Intent b = new Intent(this, MyProfileActivity.class);
                startActivity(b);
                break;
            case R.id.nav_settings:
                Log.d(TAG, "open Settings Activity");
                Intent d = new Intent(this, MainActivity.class);
                startActivity(d);
                break;
            case R.id.nav_logout:
                Log.d(TAG, "open Logout Activity");
                logout();
                break;
            // and so on...
        }
    }

    @Override
    public void onBackPressed() {
        if (this.mDrawerLayout.isDrawerVisible(GravityCompat.START)) {
            this.mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume() callback");
    }


    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "onPause() callback");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy callback");
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, LogInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
