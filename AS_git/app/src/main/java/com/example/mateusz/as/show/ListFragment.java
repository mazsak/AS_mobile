package com.example.mateusz.as.show;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mateusz.as.DeleteItem;
import com.example.mateusz.as.R;
import com.example.mateusz.as.adapters.AdapterCowshed;
import com.example.mateusz.as.adapters.AdapterTeam;
import com.google.firebase.firestore.FirebaseFirestore;

public class ListFragment extends Fragment {

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RecyclerView listHome;
    private AdapterCowshed adapterCowshed;
    private AdapterTeam adapterTeam;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        initFirst(view);

        return view;
    }


    public void initFirst(View view) {
        adapterCowshed = new AdapterCowshed(this);
        listHome = view.findViewById(R.id.list_home);
        listHome.setHasFixedSize(true);
        listHome.setLayoutManager(new LinearLayoutManager(view.getContext()));
        listHome.setAdapter(adapterCowshed);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                if (listHome.getAdapter().equals(adapterCowshed)) {
                    if (adapterCowshed.getCowsheds().get(viewHolder.getAdapterPosition()).getTeamList().isEmpty()) {
                        Toast.makeText(ListFragment.this.getContext(), getString(R.string.delete_cowshed) + " \"" + adapterCowshed.getCowsheds().get(viewHolder.getAdapterPosition()).getName() + "\"", Toast.LENGTH_SHORT).show();
                        DeleteItem.deleteCowshed(adapterCowshed.getCowsheds().get(viewHolder.getAdapterPosition()));
                        adapterCowshed.getCowsheds().remove(viewHolder.getAdapterPosition());
                        adapterCowshed.notifyDataSetChanged();
                    } else {
                        Toast.makeText(ListFragment.this.getContext(), getString(R.string.info_delete_cowshed), Toast.LENGTH_SHORT).show();
                        adapterCowshed.notifyDataSetChanged();
                    }
                } else if (listHome.getAdapter().equals(adapterTeam)) {
                    if (adapterTeam.getTeams().get(viewHolder.getAdapterPosition()).getCattleList().isEmpty()) {
                        Toast.makeText(ListFragment.this.getContext(), getString(R.string.delete_team) + " \"" + adapterTeam.getTeams().get(viewHolder.getAdapterPosition()).getName() + "\"", Toast.LENGTH_SHORT).show();
                        DeleteItem.deleteTeam(adapterTeam.getTeams().get(viewHolder.getAdapterPosition()));
                        adapterTeam.getTeams().remove(viewHolder.getAdapterPosition());
                        adapterTeam.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), getString(R.string.info_delete), Toast.LENGTH_SHORT).show();
                        adapterTeam.notifyDataSetChanged();
                    }
                }
            }
        }).attachToRecyclerView(listHome);

    }


    public void chosenAdapter(int adapter, int element) {
        switch (adapter) {
            case 0:
                listHome.setAdapter(adapterCowshed);
                break;
            case 1:
                adapterTeam = new AdapterTeam(element, this);
                listHome.setAdapter(adapterTeam);
                break;
        }
    }

}
