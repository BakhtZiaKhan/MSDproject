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

// Adapter for the RecyclerView in ExerciseActivity.
// // Reference: The following code is from https://www.geeksforgeeks.org/videos/how-to-create-health-fitness-application-in-android/
public class ExerciseRVAdapter extends RecyclerView.Adapter<ExerciseRVAdapter.ExerciseViewHolder> {

    // List of exercise data and context from the Activity.
    private ArrayList<ExerciseRVModal> exerciseRVModalArrayList;
    private Context context;
    // Interface for handling click events on items.
    private ExerciseClickInterface exerciseClickInterface;

    // Constructor for the adapter.
    public ExerciseRVAdapter(ArrayList<ExerciseRVModal> exerciseRVModalArrayList, Context context, ExerciseClickInterface exerciseClickInterface) {
        this.exerciseRVModalArrayList = exerciseRVModalArrayList;
        this.context = context;
        this.exerciseClickInterface = exerciseClickInterface;
    }

    // Method to create new views.
    @NonNull
    @Override
    public ExerciseRVAdapter.ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the custom layout for each item in the RecyclerView.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_rv_item, parent, false);
        return new ExerciseViewHolder(view);
    }

    // Method to replace the contents of a view.
    @Override
    public void onBindViewHolder(@NonNull ExerciseRVAdapter.ExerciseViewHolder holder, int position) {
        // Get element from the dataset at this position and replace the contents of the view with that element.
        ExerciseRVModal exercise = exerciseRVModalArrayList.get(position);
        holder.exerciseTV.setText(exercise.getExerciseName());
        String time = exercise.getTime() + " MIN";
        holder.timeTV.setText(time);

        // Setting up click listener for each item in the RecyclerView.
        holder.itemView.setOnClickListener(v -> {
            int currentPosition = holder.getAdapterPosition();
            if (currentPosition != RecyclerView.NO_POSITION) {
                exerciseClickInterface.onExerciseClick(currentPosition);
            }
        });
    }
// Reference complete
    // Method to return the size of the dataset.
    @Override
    public int getItemCount() {
        return exerciseRVModalArrayList.size();
    }

    // Provide a reference to the views for each data item.
    public class ExerciseViewHolder extends RecyclerView.ViewHolder {
        private TextView exerciseTV, timeTV;
        private ImageView exerciseIV;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            // Find each component within our view holder.
            exerciseTV = itemView.findViewById(R.id.idTVExerciseName);
            timeTV = itemView.findViewById(R.id.idTVExerciseTime);
            exerciseIV = itemView.findViewById(R.id.idIVExercise);
        }
    }

    // Interface for handling clicks - passing the position of the item clicked.
    public interface ExerciseClickInterface {
        void onExerciseClick(int position);
    }
}
