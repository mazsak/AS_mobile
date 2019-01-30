package com.example.mateusz.as.show;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mateusz.as.DeleteItem;
import com.example.mateusz.as.R;
import com.example.mateusz.as.adapters.AdapterCattle;
import com.example.mateusz.as.saveModelFragments.CattleFragment;
import com.example.mateusz.as.viewHolder.TeamViewHolder;
import com.google.firebase.firestore.FirebaseFirestore;

public class ListCattleFragment extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private int idTeam;
    private RecyclerView listCattleHome;
    private AdapterCattle adapterCattle;
    private Button addCattle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idTeam = getArguments().getInt(TeamViewHolder.GROUP_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_cattle, container, false);

        initFirst(view);

        return view;
    }

    public void initFirst(final View view) {
        addCattle = view.findViewById(R.id.add_cattle);
        listCattleHome = view.findViewById(R.id.list_cattle);
        listCattleHome.setHasFixedSize(true);
        listCattleHome.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapterCattle = new AdapterCattle(idTeam, this);
        listCattleHome.setAdapter(adapterCattle);
        addCattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CattleFragment cattleFragment = new CattleFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(TeamViewHolder.GROUP_ID, idTeam);
                cattleFragment.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.container_fragment, cattleFragment);
                ft.addToBackStack("tag");
                ft.commit();
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                DeleteItem.deleteCattle(adapterCattle.getCattles().get(viewHolder.getPosition()));
                adapterCattle.getCattles().remove(viewHolder.getPosition());
                adapterCattle.notifyDataSetChanged();
            }
        }).attachToRecyclerView(listCattleHome);
    }

}
