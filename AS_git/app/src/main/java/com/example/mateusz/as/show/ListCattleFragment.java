package com.example.mateusz.as.show;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mateusz.as.R;
import com.example.mateusz.as.adapters.AdapterCattle;
import com.example.mateusz.as.saveModelFragments.CattleFragment;
import com.example.mateusz.as.viewHolder.TeamViewHolder;

public class ListCattleFragment extends Fragment {


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
                ft.commit();
            }
        });
    }

}
