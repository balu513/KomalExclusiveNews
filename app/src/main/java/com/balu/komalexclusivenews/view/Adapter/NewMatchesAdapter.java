package com.balu.komalexclusivenews.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.balu.komalexclusivenews.R;
import com.balu.komalexclusivenews.model.cricket.Match;

import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewMatchesAdapter extends RecyclerView.Adapter<NewMatchesAdapter.NewMatchesVH> {

    private final Context context;
    private final List<Match> matchList;

    public NewMatchesAdapter(Context context, List<Match> matchList){
        this.context = context;
        this.matchList = matchList;
    }

    @NonNull
    @Override
    public NewMatchesVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.new_matches_item,parent,false);
        return new NewMatchesVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewMatchesVH holder, int position) {
     holder.tv_one.setText(matchList.get(position).getTeam1());
     holder.tv_two.setText(matchList.get(position).getTeam2());
        holder.tv_type.setText(matchList.get(position).getType());
     holder.tv_date.setText(matchList.get(position).getDateTimeGMT().substring(0, matchList.get(position)
             .getDateTimeGMT().length() - 14));
     if(matchList.get(position).getMatchStarted()) {
         holder.tv_result.setText(matchList.get(position).getTossWinnerTeam()!= null ?
                 matchList.get(position).getWinnerTeam() +"Won the match." : "No Result");
     }else{
         holder.tv_result.setText("Match not started");
     }
        holder.iv_team1.setImageResource(getFlags(new Random().nextInt(26)));
        holder.iv_team2.setImageResource(getFlags(new Random().nextInt(26)));
    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }

    public class NewMatchesVH extends RecyclerView.ViewHolder {

        private  TextView tv_one,tv_two,tv_date,tv_type, tv_result;
        private ImageView iv_team1,iv_team2;

        public NewMatchesVH(@NonNull View itemView) {
            super(itemView);
             tv_one = itemView.findViewById(R.id.tv_team_one);
             tv_two = itemView.findViewById(R.id.tv_team_two);
             tv_date = itemView.findViewById(R.id.match_date);
             tv_type = itemView.findViewById(R.id.tv_match_type);
             tv_result =  itemView.findViewById(R.id.tv_match_result);
             iv_team1 = itemView.findViewById(R.id.iv_team_ome);
             iv_team2 = itemView.findViewById(R.id.iv_team_two);
        }

    }

    public int getFlags(int pos){
        int[] resources =  new int[] {R.drawable.flag_1,R.drawable.flag_2,R.drawable.flag_3,R.drawable.flag_4,R.drawable.flag_5,R.drawable.flag_6,
                R.drawable.flag_7,R.drawable.flag_8,R.drawable.flag_9,R.drawable.flag_10,R.drawable.flag_11,R.drawable.flag_12,R.drawable.flag_13,
                R.drawable.flag_14,R.drawable.flag_15,R.drawable.flag_16,R.drawable.flag_17,R.drawable.flag_18,R.drawable.flag_19,R.drawable.flag_20,
                R.drawable.flag_21,R.drawable.flag_22,R.drawable.flag_23,R.drawable.flag_24,R.drawable.flag_25,R.drawable.flag_26,R.drawable.flag_27};
        if(pos < 0 || pos >= resources.length) return resources[10];
        return resources[pos];
    }
}
