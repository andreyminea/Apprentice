package ro.changeneers.apprentice;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    EditText roomName;
    Button createRoom;
    ListView roomList;
    ArrayList<String> roomArrayList;
    ArrayAdapter<String> roomAdapter;

    DatabaseReference databaseReference;

    private String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roomName = (EditText) findViewById(R.id.roomName);
        createRoom = (Button) findViewById(R.id.createRoom);
        roomList = (ListView) findViewById(R.id.roomList);

        roomArrayList = new ArrayList<String>();
        roomAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, roomArrayList);

        roomList.setAdapter(roomAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference().getRoot();

        request_name();

    }
    private void request_name()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Enter your name");
        final EditText editText = new EditText(this);

        builder.setView(editText);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {

            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                userName = editText.getText().toString();

                if(!TextUtils.isEmpty(userName))
                {

                }
                else
                {
                    request_name();
                }
            }

        }).setNegativeButton("Quit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.cancel();
                request_name();
            }
        });

        builder.show();
    }
}










