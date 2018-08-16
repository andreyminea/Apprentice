package ro.changeneers.apprentice.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.Login;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

import ro.changeneers.apprentice.R;
import ro.changeneers.apprentice.adapters.QuestListAdapter;
import ro.changeneers.apprentice.models.Quest;
import ro.changeneers.apprentice.utils.SharedPrefManager;
import ro.changeneers.apprentice.utils.Utils;

import static android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
import static ro.changeneers.apprentice.utils.Constants.ACCES_EXTRA;
import static ro.changeneers.apprentice.utils.Constants.DIFFICULTY_EXTRA;
import static ro.changeneers.apprentice.utils.Constants.ID_EXTRA;


public class MyProfileActivity extends NavDrawer implements View.OnClickListener, QuestListAdapter.OnQuestClickListener {

    private TextView mFullNameTextView;
    private ImageView mProfileImageView;
    private String nume;
    private String profile;
    private Button signOut;
    private TextView starsTextView;
    private int stars;

    private RecyclerView.LayoutManager mLayoutManagerQProg;
    private RecyclerView.LayoutManager mLayoutManagerQDone;


    ImageView imageView;
    int SELECT_FILE=0;

    String image = "";
    int PERMISSION_REQUEST = 123;
    GoogleApiClient mGoogleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);

        imageView = findViewById(R.id.profile);
        imageView.setOnClickListener(this);

        mFullNameTextView = findViewById(R.id.nume);
        sharedPrefManager = new SharedPrefManager(mContext);
        nume = sharedPrefManager.getName();
        mFullNameTextView.setText(nume);
        stars = sharedPrefManager.getStarsFromSharedPrefs();
        starsTextView = findViewById(R.id.TextViewStarsMyProfile);
        starsTextView.setText(Integer.toString(stars));

        mProfileImageView = findViewById(R.id.profile);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(MyProfileActivity.this)
                .enableAutoManage(MyProfileActivity.this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(MyProfileActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        String uri = sharedPrefManager.getPhoto();

        Uri mPhotoUri = null;
        if (uri != null) {
            mPhotoUri = Uri.parse(uri);
        }

        if (mPhotoUri != null) {
            Picasso.get().load(mPhotoUri)
                    .placeholder(android.R.drawable.sym_def_app_icon)
                    .error(android.R.drawable.sym_def_app_icon)
                    .into(mProfileImageView);
        }
        signOut = findViewById(R.id.btn_logout);


        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();

                // Google sign out
                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(@NonNull Status status) {

                                if (status.getStatusCode() == Status.RESULT_SUCCESS.getStatusCode()){
                                    Intent intent = new Intent(MyProfileActivity.this, LogInActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    Toast.makeText(MyProfileActivity.this, "E de nasjpa ! " + status.toString(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });



        List<Quest> questsInProgress = new ArrayList<>();
        List<Quest> questsDone = new ArrayList<>();

        questsInProgress.addAll(Utils.getQuestsInProgress());
        questsDone.addAll(Utils.getQuestsDone());

        RecyclerView questsDoneRV = findViewById(R.id.RecyclerViewQuestsDone);
        questsDoneRV.setHasFixedSize(true);
        RecyclerView questsInProgressRV = findViewById(R.id.RecyclerViewQuestsInProgress);
        questsInProgressRV.setHasFixedSize(true);

        int stars = SharedPrefManager.getInstance().getStarsFromSharedPrefs();
        QuestListAdapter questsInProgressAdapter = new QuestListAdapter(questsInProgress, this, stars );
        mLayoutManagerQProg = new LinearLayoutManager(this);
        questsInProgressRV.setLayoutManager(mLayoutManagerQProg);
        questsInProgressAdapter.notifyDataSetChanged();
        questsInProgressRV.setAdapter(questsInProgressAdapter);

        questsInProgressRV.setItemAnimator(new DefaultItemAnimator());

        QuestListAdapter questsDoneAdapter = new QuestListAdapter(questsDone, this, stars );
        mLayoutManagerQDone = new LinearLayoutManager(this);
        questsDoneRV.setLayoutManager(mLayoutManagerQDone);
        questsDoneAdapter.notifyDataSetChanged();
        questsDoneRV.setAdapter(questsDoneAdapter);

        questsDoneRV.setItemAnimator(new DefaultItemAnimator());



    }

    @Override
    protected int getNavigationItemID() {
        return R.id.nav_profile;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.profile:
                if (requestStoragePermission()) {
                    startPickImageIntent();}
                else {
                    Toast.makeText(this, "no perms", Toast.LENGTH_LONG).show();
                }
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SELECT_FILE && resultCode == RESULT_OK){
            image = data.getData().toString();

            loadImageWithPicasso();
        }
    }

    private void startPickImageIntent(){
        Intent gallery = new Intent(Intent.ACTION_PICK, EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, SELECT_FILE);
    }

    private void loadImageWithPicasso(){
        Picasso.get().load(image).into(imageView);
        Picasso.get().load(image).resize(500, 500).centerCrop().rotate(90).transform(new CircleTransform()).into(imageView);
    }

    private Boolean requestStoragePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            //request dialog
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.d("", "Permission is granted");
                return true;

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
                return false;
            }

        } else {
            //granted by default at app install
            return true;
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == PERMISSION_REQUEST){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Log.d("", "User just granted read external storage permission");
                //start pick image intent

                startPickImageIntent();

            }else{
                Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show();

            }
        }
    }

    @Override
    public void onQuestClick(Quest quest) {

        Intent intent = new Intent(this, QuestDetailActivity.class);
        intent.putExtra(ID_EXTRA, quest.id);
        intent.putExtra(ACCES_EXTRA, true);
        intent.putExtra(DIFFICULTY_EXTRA, quest.getDifficulty());
        startActivity(intent);
        MyProfileActivity.this.finish();

    }


    public class CircleTransform implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());

            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (squaredBitmap != source) {
                source.recycle();
            }

            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap,
                    BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);

            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);

            squaredBitmap.recycle();
            return bitmap;
        }


        @Override
        public String key() {
            return "circle";
        }
    }


}



