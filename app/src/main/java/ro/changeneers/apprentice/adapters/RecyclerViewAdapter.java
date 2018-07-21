package ro.changeneers.apprentice.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ro.changeneers.apprentice.activities.ChatActivity;
import ro.changeneers.apprentice.models.DashboardItem;
import ro.changeneers.apprentice.activities.JourneyListActivity;
import ro.changeneers.apprentice.R;

/**
 * Created by retea on 11-Jul-18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<DashboardItem> mList;

    private static final int POSITION_JOURNEY = 0;
    private static final int POSITON_CHAT = 1;

    public RecyclerViewAdapter(Context mContext, List<DashboardItem> mList) {
        this.mContext = mContext;
        this.mList = mList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        view = mInflater.inflate(R.layout.cardview_item_dashboard,parent,false);
        MyViewHolder mvh = new MyViewHolder(view);
        return mvh;
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
        final int i = position;
        switch (position){
            case POSITION_JOURNEY:
                Picasso.get().load(R.drawable.adventure).into(holder.dashboardItemThumbnail);
                break;
            case POSITON_CHAT:
                Picasso.get().load(R.drawable.chatting).into(holder.dashboardItemThumbnail);
                break;
        }
        holder.dashboardItemTitle.setText(mList.get(position).getTitle());
        holder.dashboardItemDescription.setText(mList.get(position).getDescription());
        holder.dashboardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==POSITION_JOURNEY){
                    Intent intent = new Intent(mContext,JourneyListActivity.class);
                    mContext.startActivity(intent);
                }else{
                    Intent intent = new Intent(mContext,ChatActivity.class);
                    mContext.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public  ImageView dashboardItemThumbnail;
        public TextView dashboardItemTitle;
        public TextView dashboardItemDescription;
        public LinearLayout dashboardLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            dashboardLayout = itemView.findViewById(R.id.LinearLayoutDashboard);
            dashboardItemThumbnail = itemView.findViewById(R.id.ImageViewPresentation);
            dashboardItemTitle = itemView.findViewById(R.id.TextViewTitle);
            dashboardItemDescription = itemView.findViewById(R.id.TextViewDescription);

        }
    }
}
