package com.example.quizme;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quizme.databinding.RowLeaderboardsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class LeaderboardsAdapter extends RecyclerView.Adapter<LeaderboardsAdapter.LeaderboardViewHolder> {

    public static String userRank ="1";
    Context context;
    ArrayList<User> users;

    public LeaderboardsAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public LeaderboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_leaderboards, parent, false);
        return new LeaderboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardViewHolder holder, int position) {
        User user = users.get(position);
        FirebaseUser u = FirebaseAuth.getInstance().getCurrentUser();
        String m = u.getEmail();

        if (user.getEmail().equals(m)){
            userRank = String.format("%d", position+1);
            // Log.v("msg","rank is "+String.format("#%d", position+1));
        }

        holder.binding.name.setText(user.getName());
        holder.binding.coins.setText(String.valueOf(user.getCoins()));
        holder.binding.index.setText(String.format("#%d", position+1));

        Glide.with(context)
                .load(user.getProfile())
                .into(holder.binding.imageView7);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class LeaderboardViewHolder extends RecyclerView.ViewHolder {

        RowLeaderboardsBinding binding;
        public LeaderboardViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RowLeaderboardsBinding.bind(itemView);
        }
    }
}
