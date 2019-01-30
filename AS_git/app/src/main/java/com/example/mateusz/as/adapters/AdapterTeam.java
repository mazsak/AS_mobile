package com.example.mateusz.as.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mateusz.as.R;
import com.example.mateusz.as.models.Team;
import com.example.mateusz.as.show.ListFragment;
import com.example.mateusz.as.viewHolder.TeamViewHolder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AdapterTeam extends RecyclerView.Adapter<TeamViewHolder> {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Team> teams = new ArrayList<>();
    private ListFragment home;

    public AdapterTeam(int idCowshed, ListFragment home){
        this.home = home;
        loadTeam(idCowshed);
    }

    public List<Team> getTeams() {
        return teams;
    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_group, null);
        return new TeamViewHolder(view, home);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder teamViewHolder, int i) {
        teamViewHolder.setName(teams.get(i).getName());
        teamViewHolder.setNumberOfCattle(teams.get(i).getCattleList().size());
        teamViewHolder.setType(teams.get(i).getType());
        teamViewHolder.setId((int) teams.get(i).getIdGroup());
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public void loadTeam(int idCowshed){
        db.collection("Team")
                .whereEqualTo("idCowshed", idCowshed)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            teams.addAll(task.getResult().toObjects(Team.class));
                            notifyDataSetChanged();
                        } else {
                            Log.w("AdapterCowshed.load: ", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

}
