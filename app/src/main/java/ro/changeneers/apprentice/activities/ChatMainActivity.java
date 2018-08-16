package ro.changeneers.apprentice.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ro.changeneers.apprentice.R;
import ro.changeneers.apprentice.utils.SharedPrefManager;

public class ChatMainActivity extends NavDrawer {
    ListView roomList;
    ArrayList<String> roomArrayList;
    ArrayAdapter<String> roomAdapter;

    Context mContext = this;

    DatabaseReference databaseReference;

    SharedPrefManager sharedPrefManager;

    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_main);

        roomList = (ListView) findViewById(R.id.roomList);

        roomArrayList = new ArrayList<String>();
        roomAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, roomArrayList);

        roomList.setAdapter(roomAdapter);

        sharedPrefManager = new SharedPrefManager(mContext);
        userName = sharedPrefManager.getName();

        databaseReference = FirebaseDatabase.getInstance().getReference().getRoot().child("Chat");

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

                startActivity(intent);
            }
        });



    }

    @Override
    protected int getNavigationItemID() {

        return R.id.nav_chat;
    }


}