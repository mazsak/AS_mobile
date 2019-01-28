package com.example.mateusz.as.show;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mateusz.as.R;
import com.example.mateusz.as.models.Cattle;
import com.example.mateusz.as.models.Team;
import com.example.mateusz.as.viewHolder.TeamViewHolder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CattleInfoFragment extends Fragment {

    public static final String CATTLE_EARRNIG = "EARRNIG_CATTLE_SHOW";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private TextView name, earring, cowshedNumber, birthDate, joinDate, leaveDate, leaveReason, note, sex;
    private String earringCattle;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        earringCattle = getArguments().getString(CATTLE_EARRNIG);
        getCattle();
    }

    public void init(View view) {
        name = view.findViewById(R.id.name_cattle_info);
        earring = view.findViewById(R.id.earring_cattle_info);
        cowshedNumber = view.findViewById(R.id.number_cowshed_cattle_info);
        birthDate = view.findViewById(R.id.birth_date_cattle_info);
        joinDate = view.findViewById(R.id.join_date_cattle_info);
        leaveDate = view.findViewById(R.id.leave_date_cattle_info);
        leaveReason = view.findViewById(R.id.leave_reason_cattle_info);
        note = view.findViewById(R.id.note_cattle_info);
        sex = view.findViewById(R.id.sex_cattle_info);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_cattle, container, false);

        init(view);

        return view;
    }

    public void getCattle() {
        db.collection("Cattle")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Cattle> cattles = new ArrayList<>();
                            cattles.addAll(task.getResult().toObjects(Cattle.class));
                            for (Cattle cattle : cattles) {
                                if (cattle.getEarring().equals(earringCattle)) {
                                    if (cattle.getName() != null)
                                        name.setText(cattle.getName());
                                    earring.setText(cattle.getEarring());
                                    if (cattle.getCowshedNumber() != null)
                                        cowshedNumber.setText(cattle.getCowshedNumber().toString());
                                    birthDate.setText(new SimpleDateFormat("dd.MM.yyyy").format(cattle.getBirthDate()));
                                    joinDate.setText(new SimpleDateFormat("dd.MM.yyyy").format(cattle.getJoinDate()));
                                    if (cattle.getLeaveDate() != null)
                                        leaveDate.setText(new SimpleDateFormat("dd.MM.yyyy").format(cattle.getLeaveDate()));
                                    if (cattle.getLeaveReason() != null)
                                        leaveReason.setText(cattle.getLeaveReason());
                                    if (cattle.getNotes() != null)
                                        note.setText(cattle.getNotes());
                                    sex.setText(cattle.getSex());
                                }
                            }
                        } else {
                            Log.w("CattleInfo.getCattle: ", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

}
