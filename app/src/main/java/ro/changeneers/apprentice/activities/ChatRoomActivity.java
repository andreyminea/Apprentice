package ro.changeneers.apprentice.activities;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import ro.changeneers.apprentice.models.Message;
import ro.changeneers.apprentice.adapters.MessageListAdapter;
import ro.changeneers.apprentice.R;
import ro.changeneers.apprentice.utils.SharedPrefManager;


public class ChatRoomActivity extends NavDrawer {

    private ImageButton btn_send_msg;
    private ImageButton btn_upload_img;
    private EditText input_msg;

    private ListView listview;
    private ArrayList<Message> chat;
    private MessageListAdapter adapter;

    private String user_name, room_name;
    private DatabaseReference root;
    private String temp_key;

    private FirebaseStorage storage;
    private StorageReference storageReference;
    private StorageReference images;

    Context mContext = this;

    SharedPrefManager sharedPrefManager;

    private Date currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        sharedPrefManager = new SharedPrefManager(mContext);

        listview = (ListView) findViewById(R.id.chatMessages);
        listview.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
        listview.setStackFromBottom(true);
        chat = new ArrayList<Message>();

        btn_send_msg = (ImageButton) findViewById(R.id.sendBtn);
        btn_upload_img = (ImageButton) findViewById(R.id.upload_img);
        input_msg = (EditText) findViewById(R.id.sendMsg);

        user_name = getIntent().getExtras().getString("user_name");
        room_name = getIntent().getExtras().getString("room_name");

        setTitle(" Room - " + room_name);

        adapter = new MessageListAdapter(this, R.layout.chat_my_message, R.layout.chat_their_message, chat, user_name);


        root = FirebaseDatabase.getInstance().getReference().child(room_name + "/Chat");

        btn_send_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String, Object> map = new HashMap<String, Object>();
                temp_key = root.push().getKey();
                root.updateChildren(map);

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String aux = sdf.format(Calendar.getInstance().getTime());

                DatabaseReference message_root = root.child(temp_key);


                String user_pic = sharedPrefManager.getPhoto();

                Map<String, Object> map2 = new HashMap<String, Object>();

                map2.put("name", user_name);
                map2.put("msg", input_msg.getText().toString());
                map2.put("link", false);
                map2.put("date", aux);
                map2.put("photo", user_pic);

                message_root.updateChildren(map2);
            }
        });

        btn_upload_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTime = Calendar.getInstance().getTime();
                images = storageReference.child("ChatImages/" + currentTime + ".jpg");

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);

            }
        });

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                append_chat_conversation(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                append_chat_conversation(dataSnapshot);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        byte[] date = baos.toByteArray();

        UploadTask uploadTask = images.putBytes(date);

        String link = images.getPath();

        Map<String, Object> map = new HashMap<String, Object>();
        temp_key = root.push().getKey();
        root.updateChildren(map);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String aux = sdf.format(Calendar.getInstance().getTime());

        DatabaseReference message_root = root.child(temp_key);

        String user_pic = sharedPrefManager.getPhoto();

        Map<String, Object> map2 = new HashMap<String, Object>();

        map2.put("name", user_name);
        map2.put("msg", link);
        map2.put("link", true);
        map2.put("date", aux);
        map2.put("photo", user_pic);

        message_root.updateChildren(map2);
    }

    @Override
    protected int getNavigationItemID() {
        return R.id.nav_chat;
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onResume() {
        super.onResume();
        scrollMyListViewToBottom();
    }

    private String chat_msg, chat_user_name;
    private String date_send, user_picture;
    private Boolean link;
    private TextView.BufferType nimic;
    private Message message;


    private void append_chat_conversation(DataSnapshot dataSnapshot)
    {

        Iterator i = dataSnapshot.getChildren().iterator();
        message = new Message("", "", link, date_send, user_picture);

        while (i.hasNext()) {

            date_send = (String) ((DataSnapshot) i.next()).getValue();
            link = (Boolean) ((DataSnapshot) i.next()).getValue();
            chat_msg = (String) ((DataSnapshot) i.next()).getValue();
            chat_user_name = (String) ((DataSnapshot) i.next()).getValue();
            user_picture = (String) ((DataSnapshot) i.next()).getValue();

            message.setAll(chat_user_name, chat_msg, link, date_send, user_picture);

            chat.add(message);
            scrollMyListViewToBottom();

            input_msg.setText("", nimic);


        }
        listview.setAdapter(adapter);
        scrollMyListViewToBottom();

    }

    private void scrollMyListViewToBottom() {
        listview.post(new Runnable() {
            @Override
            public void run() {
                listview.setSelection(adapter.getCount() - 1);
            }
        });
    }

}