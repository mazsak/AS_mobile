package com.example.mateusz.as.adapters;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mateusz.as.R;
import com.example.mateusz.as.models.Cattle;
import com.example.mateusz.as.show.ListCattleFragment;
import com.example.mateusz.as.viewHolder.CattleViewHolder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AdapterCattle extends RecyclerView.Adapter<CattleViewHolder> {

    public static final String CATTLE_ID = "ID_CATTLE";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Cattle> cattles = new ArrayList<>();
    private int idTeam;
    private ListCattleFragment cattleHome;

    public AdapterCattle(int idTeam, ListCattleFragment cattleHome) {
        this.idTeam = idTeam;
        this.cattleHome = cattleHome;
        loadCattle();
    }

    public List<Cattle> getCattles() {
        return cattles;
    }

    @NonNull
    @Override
    public CattleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cattle, null);
        return new CattleViewHolder(view, cattleHome);
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
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Cattle> helpCattles = new ArrayList<>();
                            helpCattles.addAll(task.getResult().toObjects(Cattle.class));
                            for (Cattle cattle : helpCattles) {
                                Integer id = Math.toIntExact(cattle.getTeam());
                                if (id.equals(idTeam)) {
                                    cattles.add(cattle);
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
