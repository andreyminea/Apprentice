package ro.changeneers.apprentice.activities;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import ro.changeneers.apprentice.models.Message;
import ro.changeneers.apprentice.adapters.MessageListAdapter;
import ro.changeneers.apprentice.R;


public class ChatRoomActivity extends NavDrawer {

    private ImageButton btn_send_msg;
    private EditText input_msg;

    private ListView listview;
    private ArrayList<Message> chat;
    private MessageListAdapter adapter;

    private String user_name, room_name;
    private DatabaseReference root;
    private String temp_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        listview = (ListView) findViewById(R.id.chatMessages);
        listview.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
        listview.setStackFromBottom(true);
        chat = new ArrayList<Message>();

        btn_send_msg = (ImageButton) findViewById(R.id.sendBtn);
        input_msg = (EditText) findViewById(R.id.sendMsg);

        user_name = getIntent().getExtras().getString("user_name");
        room_name = getIntent().getExtras().getString("room_name");
        setTitle(" Room - " + room_name);

        adapter = new MessageListAdapter(this,R.layout.my_message, R.layout.their_message, chat, user_name);


        root = FirebaseDatabase.getInstance().getReference().child(room_name);

        btn_send_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String, Object> map = new HashMap<String, Object>();
                temp_key = root.push().getKey();
                root.updateChildren(map);

                DatabaseReference message_root = root.child(temp_key);
                Map<String, Object> map2 = new HashMap<String, Object>();
                map2.put("name", user_name);
                map2.put("msg", input_msg.getText().toString());

                message_root.updateChildren(map2);
            }
        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();
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
    protected void onResume() {
        super.onResume();
        scrollMyListViewToBottom();
    }

    private String chat_msg,chat_user_name;
    private TextView.BufferType nimic;
    private Message message;


    private void append_chat_conversation(DataSnapshot dataSnapshot)
    {

        Iterator i = dataSnapshot.getChildren().iterator();
        message = new Message("","");

        while (i.hasNext()){

            chat_msg = (String) ((DataSnapshot)i.next()).getValue();
            chat_user_name = (String) ((DataSnapshot)i.next()).getValue();
            message.setBoth(chat_user_name, chat_msg);

            chat.add(message);
            scrollMyListViewToBottom();

            input_msg.setText("", nimic);


        }
        listview.setAdapter(adapter);



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
