package ro.changeneers.apprentice.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import ro.changeneers.apprentice.R;

import static android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;



public class MyProfileActivity extends NavDrawer implements View.OnClickListener {

    ImageView imageView;
            int SELECT_FILE=0;

            String image = "";
            int PERMISSION_REQUEST = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);

        imageView = findViewById(R.id.profile);
        imageView.setOnClickListener(this);
    }

    @Override
    protected int getNavigationItemID() {
        return R.id.nav_profile;
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