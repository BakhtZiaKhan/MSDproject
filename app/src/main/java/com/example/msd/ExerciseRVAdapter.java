package com.example.msd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExerciseRVAdapter extends RecyclerView.Adapter<ExerciseRVAdapter.ExerciseViewHolder> {

    private ArrayList<ExerciseRVModal> exerciseRVModalArrayList;
    private Context context;
    private ExerciseClickInterface exerciseClickInterface;

    public ExerciseRVAdapter(ArrayList<ExerciseRVModal> exerciseRVModalArrayList, Context context, ExerciseClickInterface exerciseClickInterface) {
        this.exerciseRVModalArrayList = exerciseRVModalArrayList;
        this.context = context;
        this.exerciseClickInterface = exerciseClickInterface;
    }

    @NonNull
    @Override
    public ExerciseRVAdapter.ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_rv_item, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseRVAdapter.ExerciseViewHolder holder, int position) {
        ExerciseRVModal exercise = exerciseRVModalArrayList.get(position);
        holder.exerciseTV.setText(exercise.getExerciseName());
        holder.exerciseIV.setImageResource(exercise.getImgResId());
        String time = exercise.getTime() + " MIN";
        holder.timeTV.setText(time);

        holder.itemView.setOnClickListener(v -> {
            int currentPosition = holder.getAdapterPosition();
            if (currentPosition != RecyclerView.NO_POSITION) {
                exerciseClickInterface.onExerciseClick(currentPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return exerciseRVModalArrayList.size();
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder {
        private TextView exerciseTV, timeTV;
        private ImageView exerciseIV;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseTV = itemView.findViewById(R.id.idTVExerciseName);
            timeTV = itemView.findViewById(R.id.idTVExerciseTime);
            exerciseIV = itemView.findViewById(R.id.idIVExercise);
        }
    }

    public interface ExerciseClickInterface {
        void onExerciseClick(int position);
    }
}
