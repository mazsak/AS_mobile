package com.example.mateusz.as.saveModelFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mateusz.as.R;
import com.example.mateusz.as.models.Cowshed;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CowshedFragment extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private EditText name, address, note;
    private Button save;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void init(View view) {
        name = view.findViewById(R.id.name_cowshed);
        address = view.findViewById(R.id.address_cowshed);
        note = view.findViewById(R.id.note_cowshed);
        save = view.findViewById(R.id.save_cowshed);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCowshed();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_save_cowshed, container, false);

        init(view);

        return view;
    }

    public void addCowshed() {
        if (!name.getText().toString().isEmpty()) {
            final Cowshed cowshed = new Cowshed();

            cowshed.setName(name.getText().toString());
            cowshed.setAddress(address.getText().toString());
            cowshed.setInfo(note.getText().toString());
            db.collection("Cowshed")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                List<Cowshed> cowsheds = new ArrayList<>();
                                cowsheds.addAll(task.getResult().toObjects(Cowshed.class));
                                if(cowsheds.size() == 0) {
                                    cowshed.setIdCowshed(0);
                                }else {
                                    cowshed.setIdCowshed((int) (cowsheds.get(cowsheds.size() - 1).getIdCowshed() + 1));
                                }

                                db.collection("Cowshed")
                                        .document(String.valueOf(cowshed.getIdCowshed()))
                                        .set(cowshed);
                            } else {
                                Log.w("CowshedFragment.add: ", "Error getting documents.", task.getException());
                            }
                        }
                    });

        }else{
            Toast.makeText(getContext(), getString(R.string.info_add), Toast.LENGTH_SHORT).show();
        }
    }

}
