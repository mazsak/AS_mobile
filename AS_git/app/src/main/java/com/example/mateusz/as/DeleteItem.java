package com.example.mateusz.as;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.mateusz.as.models.Cattle;
import com.example.mateusz.as.models.Cowshed;
import com.example.mateusz.as.models.Team;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DeleteItem {

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static void deleteCattle(final Cattle cattle) {
        db.collection("Team")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Team> teams = new ArrayList<>();
                            teams.addAll(task.getResult().toObjects(Team.class));

                            for (Team team : teams) {
                                if (team.getIdGroup() == cattle.getTeam()) {
                                    Integer idCattle = Math.toIntExact(cattle.getIdCattle());
                                    team.getCattleList().remove(idCattle);
                                    db.collection("Cattle")
                                            .document(String.valueOf(cattle.getIdCattle()))
                                            .delete()
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Log.d("DeleteItem.delete: ", "DocumentSnapshot successfully deleted!");
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.w("DeleteItem.delete: ", "Error deleting document", e);
                                                }
                                            });

                                    db.collection("Team")
                                            .document(String.valueOf(team.getIdGroup()))
                                            .set(team);
                                }
                            }
                        }

                    }
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void deleteTeam(final Team team) {
        db.collection("Cowshed")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Cowshed> cowsheds = new ArrayList<>();
                            cowsheds.addAll(task.getResult().toObjects(Cowshed.class));

                            for (Cowshed cowshed : cowsheds) {
                                if (cowshed.getIdCowshed() == team.getIdCowshed()) {
                                    final Integer id = Math.toIntExact(team.getIdGroup());
                                    cowshed.getTeamList().remove(id);
                                    db.collection("Cowshed")
                                            .document(String.valueOf(cowshed.getIdCowshed()))
                                            .set(cowshed);
                                    db.collection("Team")
                                            .document(String.valueOf(team.getIdGroup()))
                                            .delete()
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Log.d("DeleteItem.delete: ", "DocumentSnapshot successfully deleted!");
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.w("DeleteItem.delete: ", "Error deleting document", e);
                                                }
                                            });
                                    break;
                                }
                            }
                        } else {
                            Log.w("DeleteItem.delete: ", "Error getting documents.", task.getException());
                        }
                    }
                });
    }


    public static void deleteCowshed(final Cowshed cowshed) {
        db.collection("Team")
                .whereEqualTo("idCowshed", cowshed.getIdCowshed())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Team> teams = new ArrayList<>();
                            teams.addAll(task.getResult().toObjects(Team.class));

                            for (Team team : teams) {
                                DeleteItem.deleteTeam(team);
                            }

                            db.collection("Cowshed")
                                    .document(String.valueOf(cowshed.getIdCowshed()))
                                    .delete()
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d("DeleteItem.delete: ", "DocumentSnapshot successfully deleted!");
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w("DeleteItem.delete: ", "Error deleting document", e);
                                        }
                                    });

                        } else {
                            Log.w("AdapterCowshed.load: ", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

}
