package com.example.mateusz.as.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mateusz.as.R;
import com.example.mateusz.as.models.Cowshed;
import com.example.mateusz.as.show.ListFragment;
import com.example.mateusz.as.viewHolder.CowshedViewHolder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AdapterCowshed extends RecyclerView.Adapter<CowshedViewHolder> {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Cowshed> cowsheds = new ArrayList<>();
    private ListFragment home;

    public AdapterCowshed(ListFragment home){
        this.home = home;
        loadCowshed();
    }

    public List<Cowshed> getCowsheds() {
        return cowsheds;
    }

    @NonNull
    @Override
    public CowshedViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cowshed, null);

        return new CowshedViewHolder(view, home);
    }

    @Override
    public void onBindViewHolder(@NonNull CowshedViewHolder cowshedViewHolder, int i) {
        cowshedViewHolder.setName(cowsheds.get(i).getName());
        cowshedViewHolder.setNumberOfGroup(cowsheds.get(i).getTeamList().size());
        cowshedViewHolder.setAddress(cowsheds.get(i).getAddress());
        cowshedViewHolder.setId((int) cowsheds.get(i).getIdCowshed());
    }

    @Override
    public int getItemCount() {
        return cowsheds.size();
    }

    public void loadCowshed(){
        db.collection("Cowshed")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            cowsheds.addAll(task.getResult().toObjects(Cowshed.class));
                            notifyDataSetChanged();
                        } else {
                            Log.w("AdapterCowshed.load: ", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

}
