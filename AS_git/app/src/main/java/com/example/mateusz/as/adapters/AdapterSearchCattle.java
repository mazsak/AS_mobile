package com.example.mateusz.as.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mateusz.as.R;
import com.example.mateusz.as.models.Cattle;
import com.example.mateusz.as.viewHolder.CattleViewHolder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AdapterSearchCattle extends RecyclerView.Adapter<CattleViewHolder> {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Cattle> cattles = new ArrayList<>();
    private List<Integer> idCattle = new ArrayList<>();

    public AdapterSearchCattle(List<Integer> idCattle) {
        this.idCattle = idCattle;
        loadCattle();
    }

    @NonNull
    @Override
    public CattleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cattle, null);

        return new CattleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CattleViewHolder cattleViewHolder, int i) {
        cattleViewHolder.setName(cattles.get(i).getName());
        cattleViewHolder.setEarring(cattles.get(i).getEarring());
        cattleViewHolder.setBirthDate(new SimpleDateFormat("dd.MM.yyyy").format(cattles.get(i).getBirthDate()));
    }

    @Override
    public int getItemCount() {
        return cattles.size();
    }

    public void loadCattle() {
        db.collection("Cattle")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Cattle> helpCattles = new ArrayList<>();
                            helpCattles.addAll(task.getResult().toObjects(Cattle.class));
                            for (Cattle cattle : helpCattles) {
                                for (Integer id : idCattle) {
                                    if (id.equals(cattle.getIdCattle())) {
                                        cattles.add(cattle);
                                    }
                                }
                            }
                            notifyDataSetChanged();
                        } else {
                            Log.w("AdapterCattle.load: ", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

}
