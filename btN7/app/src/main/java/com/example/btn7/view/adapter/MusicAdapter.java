package com.example.btn7.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btn7.ItemMusicHandle;
import com.example.btn7.R;
import com.example.btn7.model.MusicModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {
    private Context context;
    private ArrayList<MusicModel> musicModels;
    private ItemMusicHandle musicHandle;

    public MusicAdapter(Context context, ArrayList<MusicModel> musicModels,ItemMusicHandle itemMusicHandle) {
        this.context = context;
        this.musicModels = musicModels;
        this.musicHandle=itemMusicHandle;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.music_item, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MusicAdapter.ViewHolder holder, int position) {
        holder.txtTitle.setText(musicModels.get(position).getName());
        int m = musicModels.get(position).getPlayBackTime() / 60;
        int s = musicModels.get(position).getPlayBackTime() % 60;
        holder.txtPlayBackTime.setText(m + " : " + s);
        holder.currentItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicHandle.onSelected(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return musicModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle, txtPlayBackTime;
        private LinearLayout currentItemLayout;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txtTitle=itemView.findViewById(R.id.txtTitle);
            txtPlayBackTime=itemView.findViewById(R.id.txtDuration);
            currentItemLayout=itemView.findViewById(R.id.currentItemLayout);
            txtPlayBackTime.setVisibility(View.GONE);
        }
    }
}
