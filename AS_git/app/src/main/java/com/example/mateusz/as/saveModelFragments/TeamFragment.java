package com.example.mateusz.as.saveModelFragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mateusz.as.R;
import com.example.mateusz.as.models.Cowshed;
import com.example.mateusz.as.models.Team;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class TeamFragment extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private EditText name;
    private Spinner type, cowshedSpinner;
    private Button save;
    private List<Cowshed> cowsheds;
    private List<String> listNameCowshed;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void init(View view) {
        cowsheds = new ArrayList<>();
        listNameCowshed = new ArrayList<>();
        listNameCowshed.add("None");
        readCowshed();
        name = view.findViewById(R.id.name_team);
        type = view.findViewById(R.id.type_team);
        cowshedSpinner = view.findViewById(R.id.cowshed_team);
        save = view.findViewById(R.id.save_team);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTeam();
            }
        });
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, listNameCowshed);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cowshedSpinner.setAdapter(dataAdapter);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(), R.array.type_group, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_save_team, container, false);

        init(view);

        return view;
    }

    public void addTeam() {
        if (!name.getText().toString().isEmpty() && !type.getSelectedItem().toString().isEmpty() && !cowshedSpinner.getSelectedItem().equals("None")) {
            final Team team = new Team();

            team.setName(name.getText().toString());
            team.setIdCowshed((int) cowsheds.get((int) cowshedSpinner.getSelectedItemId() - 1).getIdCowshed());
            if (type.getSelectedItemId() == 0) {
                team.setType("EAT");
            } else {
                team.setType("SICK");
            }

            db.collection("Team")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                List<Team> teams = new ArrayList<>();
                                teams.addAll(task.getResult().toObjects(Team.class));
                                if (teams.size() == 0) {
                                    team.setIdGroup(0);
                                } else {
                                    team.setIdGroup((int) (teams.get(teams.size() - 1).getIdGroup() + 1));
                                }

                                List<Integer> idTeam = new ArrayList<>();
                                if (cowsheds.get((int) cowshedSpinner.getSelectedItemId() - 1).getTeamList() == null) {
                                    idTeam.add((int) team.getIdGroup());
                                    cowsheds.get((int) cowshedSpinner.getSelectedItemId() - 1).setTeamList(idTeam);
                                } else {
                                    cowsheds.get((int) cowshedSpinner.getSelectedItemId() - 1).getTeamList().add((int) team.getIdGroup());
                                }

                                db.collection("Team")
                                        .document(String.valueOf(team.getIdGroup()))
                                        .set(team);

                                db.collection("Cowshed")
                                        .document(String.valueOf(cowsheds.get((int) cowshedSpinner.getSelectedItemId() - 1).getIdCowshed()))
                                        .set(cowsheds.get((int) cowshedSpinner.getSelectedItemId() - 1));
                            } else {
                                Log.w("TeamFragment.read: ", "Error getting documents.", task.getException());
                            }
                        }
                    });

        }else{
            Toast.makeText(getContext(), getString(R.string.info_add), Toast.LENGTH_SHORT).show();
        }
    }

    public void readCowshed() {
        db.collection("Cowshed")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            cowsheds.addAll(task.getResult().toObjects(Cowshed.class));
                            for (Cowshed cowshed : cowsheds) {
                                listNameCowshed.add(cowshed.getName());
                            }
                        } else {
                            Log.w("TeamFragment.cowshed: ", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

}
