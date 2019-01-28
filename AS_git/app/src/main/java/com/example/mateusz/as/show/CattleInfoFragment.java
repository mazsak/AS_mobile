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

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private EditText name, earring, cowshedNumber, birthDate, joinDate, leaveDate, leaveReason, note;
    private Spinner sex;
    private Button save;
    private Team chosenTeam;
    private int idTeam;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idTeam = getArguments().getInt(TeamViewHolder.GROUP_ID);
        getTeam();
    }

    public void init(View view) {
        name = view.findViewById(R.id.name_cattle);
        earring = view.findViewById(R.id.earring_cattle);
        cowshedNumber = view.findViewById(R.id.cowshed_number_cattle);
        birthDate = view.findViewById(R.id.birth_date_cattle);
        joinDate = view.findViewById(R.id.join_date_cattle);
        leaveDate = view.findViewById(R.id.leave_date_cattle);
        leaveReason = view.findViewById(R.id.leave_reason_cattle);
        note = view.findViewById(R.id.note_cattle);

        sex = view.findViewById(R.id.sex_cattle);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(), R.array.type_sex, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sex.setAdapter(adapter);

        save = view.findViewById(R.id.save_cattle);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCattle();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_save_cattle, container, false);

        init(view);

        return view;
    }


    public void addCattle() {
        if (!earring.getText().toString().isEmpty() && !birthDate.getText().toString().isEmpty() && !joinDate.getText().toString().isEmpty() && !sex.isSelected()) {
            final Cattle cattle = new Cattle();
            if(name.getText().toString().isEmpty()){
                cattle.setName(null);
            }else {
                cattle.setName(name.getText().toString());
            }
            cattle.setEarring(earring.getText().toString());
            cattle.setSex(sex.getSelectedItem().toString());
            if(cowshedNumber.getText().toString().isEmpty()){
                cattle.setCowshedNumber(null);
            }else {
                cattle.setCowshedNumber(Integer.valueOf(cowshedNumber.getText().toString()));
            }
            try {
                cattle.setBirthDate(new SimpleDateFormat("dd.MM.yyyy").parse(birthDate.getText().toString()));
                cattle.setJoinDate(new SimpleDateFormat("dd.MM.yyyy").parse(joinDate.getText().toString()));
                if(leaveDate.getText().toString().isEmpty()){
                    cattle.setLeaveDate(null);
                }else {
                    cattle.setLeaveDate(new SimpleDateFormat("dd.MM.yyyy").parse(leaveDate.getText().toString()));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(leaveReason.getText().toString().isEmpty()){
                cattle.setLeaveReason(null);
            }else {
                cattle.setLeaveReason(leaveReason.getText().toString());
            }
            if(note.getText().toString().isEmpty()){
                cattle.setNotes(null);
            }else {
                cattle.setNotes(note.getText().toString());
            }
            cattle.getTeamList().add(idTeam);


            db.collection("Cattle")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                List<Cattle> cattles = new ArrayList<>();
                                cattles.addAll(task.getResult().toObjects(Cattle.class));
                                if (cattles.size() == 0) {
                                    cattle.setIdCattle(0);
                                } else {
                                    cattle.setIdCattle(cattles.get(cattles.size() - 1).getIdCattle() + 1);
                                }

                                db.collection("Cattle")
                                        .document(String.valueOf(cattle.getIdCattle()))
                                        .set(cattle);

                                if (chosenTeam.getCattleList().isEmpty()) {
                                    List<Integer> list = new ArrayList<>();
                                    list.add((int) cattle.getIdCattle());
                                    chosenTeam.setCattleList(list);
                                } else {
                                    chosenTeam.getCattleList().add((int) cattle.getIdCattle());
                                }

                                db.collection("Team")
                                        .document(String.valueOf(chosenTeam.getIdGroup()))
                                        .set(chosenTeam);
                            } else {
                                Log.w("QueryCattle.read: ", "Error getting documents.", task.getException());
                            }
                        }
                    });
        } else {
            Toast.makeText(getContext(), getString(R.string.info_add), Toast.LENGTH_SHORT).show();
        }
    }

    public void getTeam() {
        db.collection("Team")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Team> teams = new ArrayList<>();
                            teams.addAll(task.getResult().toObjects(Team.class));
                            for(Team team : teams) {
                                if(team.getIdGroup() == idTeam) {
                                    chosenTeam = team;
                                }
                            }
                        } else {
                            Log.w("QueryTeam.read: ", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

}
