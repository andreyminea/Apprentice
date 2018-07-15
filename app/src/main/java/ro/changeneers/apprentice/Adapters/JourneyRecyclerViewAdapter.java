package ro.changeneers.apprentice.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ro.changeneers.apprentice.Activities.JourneyCardStackActivity;
import ro.changeneers.apprentice.Models.JourneyItem;
import ro.changeneers.apprentice.R;

public class JourneyRecyclerViewAdapter extends RecyclerView.Adapter<JourneyRecyclerViewAdapter.MyJourneyViewHolder> {
    private static Context jContext;
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
        holder.journeyOverviewThumbnail.setImageResource(R.mipmap.ico_info);
        holder.journeyBaniThumbnail.setImageResource(R.mipmap.ico_bani);
        holder.journeyStresThumbnail.setImageResource(R.mipmap.ico_stres);
        holder.journeyJobsThumbnail.setImageResource(R.mipmap.ico_jobs);

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
            journeyThumbnail = itemView.findViewById(R.id.ImageViewJourney);
            journeyTitle = itemView.findViewById(R.id.TextViewJourneyTitle);
            journeyOverviewThumbnail = itemView.findViewById(R.id.ico_overview);
            journeyBaniThumbnail = itemView.findViewById(R.id.ico_bani);
            journeyStresThumbnail = itemView.findViewById(R.id.ico_stres);
            journeyJobsThumbnail = itemView.findViewById(R.id.ico_job);

            journeyOverviewThumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),JourneyCardStackActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
