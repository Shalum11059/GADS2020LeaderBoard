package com.moise.gadsledersboards.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.moise.gadsledersboards.R;
import com.moise.gadsledersboards.others.SkillIqLeader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SkillIqAdapter extends RecyclerView.Adapter<SkillIqAdapter.ViewHolder> {
    private List<SkillIqLeader> mSkillIqLeaderList;
    public SkillIqAdapter(List<SkillIqLeader> mSkillLeader){

        mSkillIqLeaderList = mSkillLeader;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent
                        .getContext())
                .inflate(R.layout.fragment_skill_iq_leaders_items, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SkillIqLeader leader = mSkillIqLeaderList.get(position);
        holder.mLeaderName.setText(leader.getName());
        holder.mCountry.setText(String.format("Country: %s", leader.getCountry()));
        holder.mScore.setText(String.format("Score: %s", leader.getScore()));
        Picasso.get().load(leader.getBadgeUrl()).into(holder.mBadge);


    }

    @Override
    public int getItemCount() {
        return mSkillIqLeaderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mLeaderName;
        TextView mScore;
        TextView mCountry;
        ImageView mBadge;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mLeaderName = itemView.findViewById(R.id.skill_iq_leader_name);
            mScore = itemView.findViewById(R.id.skill_iq_leader_score);
            mCountry = itemView.findViewById(R.id.skill_iq_leader_country);
            mBadge = itemView.findViewById(R.id.skill_iq_leader_badge);
        }
    }
}
