package ro.changeneers.apprentice.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import ro.changeneers.apprentice.R;
import ro.changeneers.apprentice.models.Message;

public class MessageListAdapter extends BaseAdapter {

    private String currentUser;
    private ArrayList<Message> messages;


    public MessageListAdapter(String currentUser, ArrayList<Message> messages) {
        this.currentUser = currentUser;
        this.messages = messages;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Message getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        Context context = parent.getContext();

        Message currentMessage = messages.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder;

        if (view == null) {
            view = inflater.inflate(R.layout.chat_message_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.myMessageContent = (RelativeLayout) view.findViewById(R.id.myChatContent);
            viewHolder.myTimeTextView = (TextView) view.findViewById(R.id.myTimeTv);
            viewHolder.myMessageTextView = (TextView) view.findViewById(R.id.myMessageTv);
            viewHolder.myTakePicImageView = (ImageView) view.findViewById(R.id.myTakePictureIv);

            viewHolder.theirMessageContent = (RelativeLayout) view.findViewById(R.id.theiChatContent);
            viewHolder.theirTimeTextView = (TextView) view.findViewById(R.id.theirTimeTv);
            viewHolder.theirMessageTextView = (TextView) view.findViewById(R.id.theirMessageTv);
            viewHolder.theirTakePicImageView = (ImageView) view.findViewById(R.id.theirTakePicIm);
            viewHolder.theirAvatar = (ImageView) view.findViewById(R.id.avatar);
            viewHolder.theirName = (TextView) view.findViewById(R.id.name);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag(); // this is used to re-use views
        }

        // populate list item
        String name = currentMessage.getName();
        String text = currentMessage.getText();
        Boolean link = currentMessage.getLink();
        String date = currentMessage.getDate();
        String userPicUrl = currentMessage.getUserPic();

        StorageReference reference = FirebaseStorage.getInstance().getReference();

        Log.d("===adapter==", "name="+ name + " message=" + text + " link=" + link + " userPic=" + userPicUrl);
        if (currentUser.equals(name)) {

            viewHolder.myMessageContent.setVisibility(View.VISIBLE);
            viewHolder.theirMessageContent.setVisibility(View.GONE);

            if (link != null && link) {
                Glide.with(context).using(new FirebaseImageLoader())
                        .load(reference.child(text)).override(1280, 720).into(viewHolder.myTakePicImageView);

                viewHolder.myTakePicImageView.setVisibility(View.VISIBLE);
            } else {
                viewHolder.myTakePicImageView.setVisibility(View.GONE);
            }

            viewHolder.myTimeTextView.setText(date);
            viewHolder.myMessageTextView.setText(text);
        } else {

            if (link != null && link) {
                Glide.with(context).using(new FirebaseImageLoader())
                        .load(reference.child(text)).override(1280, 720).into(viewHolder.theirTakePicImageView);
                viewHolder.theirTakePicImageView.setVisibility(View.VISIBLE);
            } else {
                viewHolder.theirTakePicImageView.setVisibility(View.GONE);
            }

            viewHolder.theirName.setText(name);
            viewHolder.theirTimeTextView.setText(date);
            viewHolder.theirMessageTextView.setText(text);

            Picasso.get().load(userPicUrl)
                    .transform(new CropCircleTransformation()).fit()
                    .into(viewHolder.theirAvatar);
        }

        return view;

    }

    class ViewHolder {

        RelativeLayout myMessageContent;
        RelativeLayout theirMessageContent;

        TextView myTimeTextView;
        TextView myMessageTextView;
        ImageView myTakePicImageView;

        TextView theirTimeTextView;
        TextView theirMessageTextView;
        ImageView theirTakePicImageView;
        ImageView theirAvatar;
        TextView theirName;

    }
}
