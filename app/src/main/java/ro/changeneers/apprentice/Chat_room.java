package ro.changeneers.apprentice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Chat_room extends AppCompatActivity
{
    Button sendBtn;
    TextView received;
    EditText sendMsg;

    DatabaseReference rootRoomName;

    String roomName;
    String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        sendBtn = (Button) findViewById(R.id.sendBtn);
        received = (TextView) findViewById(R.id.received);
        sendMsg = (EditText) findViewById(R.id.sendMsg);

        //Get room name and user name

        roomName = getIntent().getExtras().getString("Room_name");

        userName = getIntent().getExtras().getString("User_name");


        setTitle(roomName);

        rootRoomName = FirebaseDatabase.getInstance().getReference().getRoot().child(roomName);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                DatabaseReference childroot = rootRoomName.push();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("name", userName);
                map.put("message", sendMsg.getText().toString());
                childroot.updateChildren(map);
            }
        });






    }
}
