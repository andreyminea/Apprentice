package ro.changeneers.apprentice;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MessageListAdapter extends ArrayAdapter<Message>

{
    private Context mContext;
    private int mResource1;
    private int mResource2;
    private String UserName;




    public MessageListAdapter(@NonNull Context context, int resource1, int resource2, @NonNull ArrayList<Message> objects, String user_name)
    {
        super(context, resource1,resource2, objects);
        mContext = context;
        mResource1 = resource1;
        mResource2 = resource2;
        UserName = user_name;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        String name = getItem(position).getName();
        String text = getItem(position).getText();


        LayoutInflater inflater = LayoutInflater.from(mContext);

        if(UserName.equals(name))

        {
            convertView= inflater.inflate(mResource1, parent, false);
            TextView txtView_txtMe = (TextView) convertView.findViewById(R.id.message_body_me);
            txtView_txtMe.setText(text);
        }
        else {

            convertView= inflater.inflate(mResource2, parent, false);
            TextView txtView_txtYou = (TextView) convertView.findViewById(R.id.message_body_you);
            txtView_txtYou.setText(text);
            TextView txtView_name = (TextView) convertView.findViewById(R.id.name);
            txtView_name.setText(name);
        }
        return  convertView;

    }


}
