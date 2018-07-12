package ro.changeneers.apprentice;

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

import java.util.List;

/**
 * Created by retea on 11-Jul-18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<DashboardItem> mList;

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
        holder.dashboardItemThumbnail.setImageResource(mList.get(position).getThumbnail());
        holder.dashboardItemTitle.setText(mList.get(position).getTitle());
        holder.dashboardItemDescription.setText(mList.get(position).getDescription());
        holder.dashboardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==0){
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
