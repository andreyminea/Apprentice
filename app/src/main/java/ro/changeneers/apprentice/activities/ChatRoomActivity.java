package ro.changeneers.apprentice.activities;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import ro.changeneers.apprentice.models.Message;
import ro.changeneers.apprentice.adapters.MessageListAdapter;
import ro.changeneers.apprentice.R;
import ro.changeneers.apprentice.utils.SharedPrefManager;


public class ChatRoomActivity extends NavDrawer {

    private static final String TAG = "ChatRoomActivity";
    private static final int TAKE_PIC_REQUEST_CODE = 124;

    private static final String DB_DATE = "date";
    private static final String DB_LINK = "link";
    private static final String DB_MSG = "msg";
    private static final String DB_NAME = "name";
    private static final String DB_PHOTO = "photo";

    private ImageButton btnSendMsg;
    private ImageButton btnUploadImg;
    private EditText inputMsgEditText;

    private ListView listview;
    private ArrayList<Message> chatMessagesList = new ArrayList<>();
    private MessageListAdapter adapter;

    private String userName, roomName;
    private DatabaseReference root;
    private String tempKey;

    private StorageReference storageReference;
    private StorageReference images;

    private SharedPrefManager sharedPrefManager;

    private Date currentTime;
    private String currentDate;

    private String chatMsg, chatUserName;
    private String dateSend, userPicture;
    private Boolean link;
    private Message message;

    private SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        sharedPrefManager =  SharedPrefManager.getInstance();

        listview = (ListView) findViewById(R.id.chatMessages);
        listview.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
        listview.setStackFromBottom(true);

        btnSendMsg = (ImageButton) findViewById(R.id.sendBtn);
        btnUploadImg = (ImageButton) findViewById(R.id.upload_img);
        inputMsgEditText = (EditText) findViewById(R.id.sendMsg);

        if (getIntent().getExtras() != null) {
            userName = getIntent().getExtras().getString("user_name");
            roomName = getIntent().getExtras().getString("room_name");
        } else {
            Log.e(TAG, "something went wrong, close activity");
            finish();
        }

        setTitle(getString(R.string.chat_activity_title, roomName));

        simpleDateFormat = new SimpleDateFormat("HH:mm");

        adapter = new MessageListAdapter(userName, chatMessagesList);
        listview.setAdapter(adapter);

        root = FirebaseDatabase.getInstance().getReference().child(roomName + "/Chat");

        btnSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempKey = root.push().getKey();
                if (tempKey != null) {
                    DatabaseReference messageRoot = root.child(tempKey);

                    String userPic = sharedPrefManager.getPhoto();

                    Map<String, Object> newMessage = new HashMap<String, Object>();

                    currentTime = Calendar.getInstance().getTime();
                    currentDate = simpleDateFormat.format(currentTime);

                    newMessage.put(DB_NAME, userName);
                    newMessage.put(DB_MSG, inputMsgEditText.getText().toString());
                    newMessage.put(DB_LINK, false);
                    newMessage.put(DB_DATE, currentDate);
                    newMessage.put(DB_PHOTO, userPic);

                    messageRoot.updateChildren(newMessage);

                    //clear edit text
                    inputMsgEditText.setText("");
                }
            }
        });

        btnUploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTime = Calendar.getInstance().getTime();
                images = storageReference.child("ChatImages/" + currentTime + ".jpg");

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, TAKE_PIC_REQUEST_CODE);

            }
        });

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildAdded");
                appendChatConversation(dataSnapshot);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildChanged");
                appendChatConversation(dataSnapshot);

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        scrollMyListViewToBottom();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAKE_PIC_REQUEST_CODE) {
            if (resultCode == RESULT_OK && data.getExtras() != null) {

                Bitmap bitmap = (Bitmap) data.getExtras().get("data");

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                byte[] date = baos.toByteArray();

                UploadTask uploadTask = images.putBytes(date);
                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Log.d(TAG, "onSuccess");

                        final String link = images.getPath();
                        currentTime = Calendar.getInstance().getTime();
                        currentDate = simpleDateFormat.format(currentTime);

                        tempKey = root.push().getKey();
                        if (tempKey != null) {

                            DatabaseReference messageRoot = root.child(tempKey);

                            final String userPic = sharedPrefManager.getPhoto();

                            final Map<String, Object> newMessage = new HashMap<String, Object>();

                            newMessage.put(DB_NAME, userName);
                            newMessage.put(DB_MSG, link);
                            newMessage.put(DB_LINK, true);
                            newMessage.put(DB_DATE, currentDate);
                            newMessage.put(DB_PHOTO, userPic);

                            messageRoot.updateChildren(newMessage, new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                    Log.d(TAG, "oncomplete");
                                }
                            });
                        }
                    }
                });
            }
        }
    }

    @Override
    protected int getNavigationItemID() {
        return R.id.nav_chat;
    }

    private void appendChatConversation(DataSnapshot dataSnapshot) {

        for (DataSnapshot child : dataSnapshot.getChildren()) {
            message = new Message();

            String key = child.getKey();
            if (key != null) {
                try {
                    switch (key) {
                        case DB_NAME:
                            chatUserName = child.getValue(String.class);
                            break;
                        case DB_MSG:
                            chatMsg = child.getValue(String.class);
                            break;
                        case DB_LINK:
                            link = child.getValue(Boolean.class);
                            break;
                        case DB_DATE:
                            dateSend = child.getValue(String.class);
                            break;
                        case DB_PHOTO:
                            userPicture = child.getValue(String.class);
                            break;
                        default:
                            Log.e(TAG, "invalid db key");
                    }
                } catch (DatabaseException exception) {
                    Log.e(TAG, "message db exception, ", exception);
                }
            }
        }

        message.setAll(chatUserName, chatMsg, link, dateSend, userPicture);

        chatMessagesList.add(message);

        Log.d(TAG, "chat messages size = " + chatMessagesList.size());
        adapter.notifyDataSetChanged();
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
