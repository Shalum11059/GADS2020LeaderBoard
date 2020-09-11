package com.moise.gadsledersboards.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moise.gadsledersboards.others.HourLeader;
import com.moise.gadsledersboards.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HoursAdapter extends RecyclerView.Adapter<HoursAdapter.ViewHolder>{

    private List<HourLeader> mHourLeaderList;

    public HoursAdapter(List<HourLeader> hourLeaderList){
        mHourLeaderList = hourLeaderList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_learning_leaders_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HourLeader leader = mHourLeaderList.get(position);
        holder.mLeaderName.setText(leader.getName());
        holder.mHours.setText(String.format("Hours: %s", leader.getHours()));
        holder.mCountry.setText(String.format("Country: %s", leader.getCountry()));
        Picasso.get().load(leader.getBadgeUrl()).into(holder.mBadge);

    }

    @Override
    public int getItemCount() {
        return mHourLeaderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mLeaderName;
        TextView mHours;
        TextView mCountry;
        ImageView mBadge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mLeaderName = itemView.findViewById(R.id.skill_iq_leader_name);
            mHours = itemView.findViewById(R.id.skill_iq_leader_score);
            mCountry = itemView.findViewById(R.id.skill_iq_leader_country);
            mBadge = itemView.findViewById(R.id.skill_iq_leader_badge);
        }
    }
}
