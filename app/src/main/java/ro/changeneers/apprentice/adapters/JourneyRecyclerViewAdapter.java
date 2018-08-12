package ro.changeneers.apprentice.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ro.changeneers.apprentice.activities.BottomNavigationActivity;
import ro.changeneers.apprentice.models.DashboardItem;
import ro.changeneers.apprentice.models.JourneyItem;
import ro.changeneers.apprentice.R;

public class JourneyRecyclerViewAdapter extends RecyclerView.Adapter<JourneyRecyclerViewAdapter.MyJourneyViewHolder> {
    private List<JourneyItem> jList;
    private OnJourneyItemClickListener listener;

    public JourneyRecyclerViewAdapter(List<JourneyItem> jList, OnJourneyItemClickListener listener) {
        this.jList =jList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyJourneyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        view = mInflater.inflate(R.layout.cardview_item_journey_list,parent,false);
        MyJourneyViewHolder mvh = new MyJourneyViewHolder(view, listener);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyJourneyViewHolder holder, int position) {
        final int i = position;
        switch (position){
            case 0:
                Picasso.get().load(R.drawable.java).fit().centerInside().into(holder.journeyThumbnail);
                break;
        }

        holder.journeyTitle.setText(jList.get(position).getTitle());

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
        public CardView journeyCardView;

        private OnJourneyItemClickListener listener;

        public MyJourneyViewHolder(@NonNull View itemView, final OnJourneyItemClickListener listener) {
            super(itemView);
            journeyThumbnail = itemView.findViewById(R.id.ImageViewJourney);
            journeyTitle = itemView.findViewById(R.id.TextViewJourneyTitle);
            journeyOverviewThumbnail = itemView.findViewById(R.id.ico_overview);
            journeyBaniThumbnail = itemView.findViewById(R.id.ico_bani);
            journeyStresThumbnail = itemView.findViewById(R.id.ico_stres);
            journeyJobsThumbnail = itemView.findViewById(R.id.ico_job);
            journeyCardView = itemView.findViewById(R.id.CardViewJourneyGeneral);
            this.listener = listener;

            journeyCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listener.onJourneyCardClick();

                }
            });


            journeyOverviewThumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listener.onOverviewIconClick();

                }
            });
            journeyBaniThumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listener.onBaniIconClick();

                }
            });
            journeyStresThumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listener.onStresIconClick();

                }
            });
            journeyJobsThumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listener.onJobsIconClick();

                }
            });
        }
    }

    public interface OnJourneyItemClickListener {

        void onJourneyCardClick();
        void onOverviewIconClick();
        void onBaniIconClick();
        void onStresIconClick();
        void onJobsIconClick();

    }
}
