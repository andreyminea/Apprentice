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

import java.util.List;

import ro.changeneers.apprentice.R;
import ro.changeneers.apprentice.activities.QuestDetailActivity;
import ro.changeneers.apprentice.models.Quest;

public class QuestListAdapter extends RecyclerView.Adapter<QuestListAdapter.QuestListViewHolder>{

    private List<Quest> qList;
    private Context qContext;

    public QuestListAdapter(List<Quest> qList,Context qContext) {
        this.qList = qList;
        this.qContext = qContext;
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

        final Quest quest = qList.get(position);

        holder.questId.setText(quest.getId()+1+"/"+qList.size());
        holder.questTitle.setText(quest.getTitle());

        holder.questLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(qContext, QuestDetailActivity.class);
                intent.putExtra("position",position);
                qContext.startActivity(intent);
            }
        });

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

}