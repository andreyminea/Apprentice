package ro.changeneers.apprentice;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class JourneyRecyclerViewAdapter extends RecyclerView.Adapter<JourneyRecyclerViewAdapter.MyJourneyViewHolder> {
    private Context jContext;
    private List<JourneyItem> jList;

    public JourneyRecyclerViewAdapter(Context jContext, List<JourneyItem> jList) {
        this.jContext = jContext;
        this.jList =jList;
    }

    @NonNull
    @Override
    public MyJourneyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        view = mInflater.inflate(R.layout.cardview_item_journey_list,parent,false);
        MyJourneyViewHolder mvh = new MyJourneyViewHolder(view);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyJourneyViewHolder holder, int position) {
        holder.journeyThumbnail.setImageResource(jList.get(position).getThumbnail());
        holder.journeyTitle.setText(jList.get(position).getTitle());
        holder.journeyOverviewThumbnail.setImageResource(R.drawable.ic_launcher_background);
        holder.journeyBaniThumbnail.setImageResource(R.drawable.ic_launcher_background);
        holder.journeyStresThumbnail.setImageResource(R.drawable.ic_launcher_background);
        holder.journeyJobsThumbnail.setImageResource(R.drawable.ic_launcher_background);

    }

    @Override
    public int getItemCount() {
        return jList.size();
    }

    public static class MyJourneyViewHolder extends RecyclerView.ViewHolder{

        public TextView journeyTitle;
        public ImageView journeyThumbnail;
        public ImageView journeyOverviewThumbnail;
        public ImageView journeyBaniThumbnail;
        public ImageView journeyStresThumbnail;
        public ImageView journeyJobsThumbnail;

        public MyJourneyViewHolder(@NonNull View itemView) {
            super(itemView);
            journeyThumbnail = itemView.findViewById(R.id.ImageViewPresentation);
            journeyTitle = itemView.findViewById(R.id.TextViewJourneyTitle);
            journeyOverviewThumbnail = itemView.findViewById(R.id.ico_overview);
            journeyBaniThumbnail = itemView.findViewById(R.id.ico_bani);
            journeyStresThumbnail = itemView.findViewById(R.id.ico_stres);
            journeyJobsThumbnail = itemView.findViewById(R.id.ico_job);
        }
    }
}
