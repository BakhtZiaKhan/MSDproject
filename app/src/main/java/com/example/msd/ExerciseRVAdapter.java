package com.example.msd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ExerciseRVAdapter extends RecyclerView.Adapter<ExerciseRVAdapter.ExerciseViewHolder> {


    private ArrayList<ExerciseRVModal> exerciseRVModalArrayList;


    private Context context;


    private ExerciseClientInterface exerciseClientInterface;

    public ExerciseRVAdapter(ArrayList<ExerciseRVModal> exerciseRVModalArrayList1, Context context, ExerciseClientInterface exerciseClientInterface) {
        this.exerciseClientInterface = exerciseClientInterface;
        this.context = context;
        this.exerciseRVModalArrayList = exerciseRVModalArrayList1;
    }


    @NonNull
    @Override
    public ExerciseRVAdapter.ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_rv_item,parent,false);
        return new ExerciseViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseRVAdapter.ExerciseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder {
    private TextView exerciseTV,timeTV;
    private LottieAnimationView exericeLAV;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseTV = itemView.findViewById(R.id.idtv)
        }
    }

    public interface ExerciseClientInterface {
        void onExerciseClick(int position);


    }


}
