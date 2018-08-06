package ro.changeneers.apprentice.adapters;

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

import ro.changeneers.apprentice.models.DashboardItem;
import ro.changeneers.apprentice.R;

/**
 * Created by retea on 11-Jul-18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<DashboardItem> mList;
    private final OnItemClickListener listener;

    public RecyclerViewAdapter(List<DashboardItem> mList, OnItemClickListener listener) {
        this.mList = mList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.cardview_item_dashboard, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final DashboardItem item = mList.get(position);

        holder.dashboardItemTitle.setText(item.getTitle());
        holder.dashboardItemDescription.setText(item.getDescription());
        Picasso.get().load(item.getThumbnail()).into(holder.dashboardItemThumbnail);

        holder.dashboardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(item);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout dashboardLayout;
        ImageView dashboardItemThumbnail;
        TextView dashboardItemTitle;
        TextView dashboardItemDescription;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            dashboardLayout = itemView.findViewById(R.id.LinearLayoutDashboard);
            dashboardItemThumbnail = itemView.findViewById(R.id.ImageViewPresentation);
            dashboardItemTitle = itemView.findViewById(R.id.TextViewTitle);
            dashboardItemDescription = itemView.findViewById(R.id.TextViewDescription);

        }
    }

    public interface OnItemClickListener {
        void onItemClick(DashboardItem item);
    }
}
