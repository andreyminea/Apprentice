package ro.changeneers.apprentice.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ro.changeneers.apprentice.R;
import ro.changeneers.apprentice.models.MQuest;

import static ro.changeneers.apprentice.utils.Constants.FINISHED;
import static ro.changeneers.apprentice.utils.Constants.IN_PROGRESS;
import static ro.changeneers.apprentice.utils.Constants.NOT_STARTED;

public class QuestListAdapter extends RecyclerView.Adapter<QuestListAdapter.QuestListViewHolder>{

    private List<MQuest> qList;
    private final OnQuestClickListener onQuestClickListener;
    private  int stars;


    public QuestListAdapter(List<MQuest> qList, OnQuestClickListener onQuestClickListener, int stars) {
        this.qList = qList;
        this.onQuestClickListener = onQuestClickListener;
        this.stars = stars;
    }

    @NonNull
    @Override
    public QuestListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.cardview_item_quest, parent, false);
        return new QuestListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestListAdapter.QuestListViewHolder holder, final int position) {

        final MQuest quest = qList.get(position);
        String s = position +1+"/"+qList.size();
        holder.questId.setText(s);
        holder.questTitle.setText(quest.title);

        if(quest.getStatus() == FINISHED){
            holder.questThumbnail.setImageResource(R.drawable.ic_done_green_24dp);
        }else if(quest.getStatus() == IN_PROGRESS){
            holder.questThumbnail.setImageResource(R.drawable.ic_progress_orange_24dp);
        }else if(quest.getStatus() == NOT_STARTED){
            if (stars<quest.minimStarsToUnlock){
                holder.questThumbnail.setImageResource(R.drawable.ic_lock_outline_black_24dp);
            }else{
                holder.questThumbnail.setImageResource(R.drawable.ic_lock_open_black_24dp);
            }
        }

        holder.questLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onQuestClickListener.onQuestClick(quest);
            }
        });

        if(stars<quest.minimStarsToUnlock){

        }

    }

     static class QuestListViewHolder extends RecyclerView.ViewHolder{

        LinearLayout questLayout;
        ImageView questThumbnail;
        TextView questId;
        TextView questTitle;

         public QuestListViewHolder(View itemView) {
             super(itemView);

             questLayout = itemView.findViewById(R.id.LinearLayoutQuest);
             questThumbnail = itemView.findViewById(R.id.ImageViewQuest);
             questId = itemView.findViewById(R.id.TextViewQuestId);
             questTitle = itemView.findViewById(R.id.TextViewQuestTitle);
         }
     }

    @Override
    public int getItemCount() {
        return qList.size();
    }

    public interface OnQuestClickListener {
        void onQuestClick(MQuest quest);
    }

}
