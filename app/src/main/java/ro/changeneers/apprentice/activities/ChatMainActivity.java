package ro.changeneers.apprentice.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import ro.changeneers.apprentice.R;
import ro.changeneers.apprentice.utils.SharedPrefManager;

public class ChatMainActivity extends NavDrawer {
    EditText roomName;
    Button createRoom;
    ListView roomList;
    ArrayList<String> roomArrayList;
    ArrayAdapter<String> roomAdapter;

    Context mContext = this;

    DatabaseReference databaseReference;

    SharedPrefManager sharedPrefManager;

    private String userName;

    private FirebaseStorage storage ;
    private StorageReference storageReference ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_main);

        roomName = (EditText) findViewById(R.id.roomName);
        createRoom = (Button) findViewById(R.id.createRoom);
        roomList = (ListView) findViewById(R.id.roomList);

        roomArrayList = new ArrayList<String>();
        roomAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, roomArrayList);

        roomList.setAdapter(roomAdapter);

        sharedPrefManager = new SharedPrefManager(mContext);
        userName = sharedPrefManager.getName();

        databaseReference = FirebaseDatabase.getInstance().getReference().getRoot();

        createRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Map<String,Object> map = new HashMap<String, Object>();
                map.put(roomName.getText().toString(), "");
                databaseReference.updateChildren(map);

                DatabaseReference root = FirebaseDatabase.getInstance().getReference().child(roomName.getText().toString());
                Map<String,Object> map2 = new HashMap<>();
                map2.put("Info","");
                root.updateChildren(map2);

            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                Iterator iterator = dataSnapshot.getChildren().iterator();

                Set<String> set = new HashSet<String>();

                while(iterator.hasNext())
                {
                    // Get all the rooms from database
                    set.add( (String) ( (DataSnapshot) iterator.next()).getKey() );
                }
                roomArrayList.clear();
                roomArrayList.addAll(set);

                roomAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        roomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                Intent intent = new Intent(ChatMainActivity.this, ChatRoomActivity.class);
                intent.putExtra("room_name", ((TextView) view).getText().toString());
                intent.putExtra("user_name", userName);

                Map<String,Object> map = new HashMap<>();
                DatabaseReference root = FirebaseDatabase.getInstance().getReference().child(((TextView) view).getText().toString() + "/Info");

                startActivity(intent);
            }
        });



    }

    @Override
    protected int getNavigationItemID() {

        return R.id.nav_chat;
    }


}










