package ro.changeneers.apprentice.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import ro.changeneers.apprentice.R;

import static android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

public class QuestFinishActivity extends AppCompatActivity {

    Button questFinish;
    private final int PICK_IMAGE_REQUEST = 71;
    private final int SELECT_FILE = 0;
    private ImageView imageView;
    private Uri filePath;
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_finish);

        questFinish = findViewById(R.id.btn_upload_quest);
        questFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(requestStoragePermission()) {
                choseImage();
            }
            }
        });
    }

    private void choseImage(){
        Intent gallery = new Intent(Intent.ACTION_PICK, EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, SELECT_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SELECT_FILE && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
//            filePath = data.getData();
//            try {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//                if(bitmap != null){
//                 imageView.setImageBitmap(bitmap);
//            }
//            }
//            catch (IOException e)
//            {
//                e.printStackTrace();
//            }
            startProgress();
        }
    }
    private Boolean requestStoragePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            //request dialog
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.d("", "Permission is granted");
                return true;

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PICK_IMAGE_REQUEST);
                return false;
            }

        } else {
            //granted by default at app install
            return true;
        }
    }

    private void startProgress(){
        progressDialog = new ProgressDialog(QuestFinishActivity.this);
        progressDialog.setMax(100);
        progressDialog.setMessage("Verificam....");
        progressDialog.setTitle("Verificare");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (progressDialog.getProgress() <= progressDialog
                            .getMax()) {
                        Thread.sleep(100);
                        handle.sendMessage(handle.obtainMessage());
                        if (progressDialog.getProgress() == progressDialog
                                .getMax()) {
                            progressDialog.dismiss();
                            QuestFinishActivity.this.finish();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    Handler handle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progressDialog.incrementProgressBy(1);
        }
    };



}
