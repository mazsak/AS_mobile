package com.example.mateusz.as.show;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mateusz.as.R;
import com.example.mateusz.as.adapters.AdapterCowshed;
import com.example.mateusz.as.adapters.AdapterSearchCattle;
import com.example.mateusz.as.adapters.AdapterTeam;
import com.example.mateusz.as.barcode.BarcodeFragment;

public class ListSearchFragment extends Fragment {


    private RecyclerView listHome;
    private AdapterSearchCattle  adapterSearchCattle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            adapterSearchCattle = new AdapterSearchCattle(getArguments().getIntegerArrayList(BarcodeFragment.ID_CATTLES), this);
            adapterSearchCattle.notifyDataSetChanged();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        initFirst(view);

        return view;
    }

    public void initFirst(View view) {
        listHome = view.findViewById(R.id.list_home);
        listHome.setHasFixedSize(true);
        listHome.setLayoutManager(new LinearLayoutManager(view.getContext()));
        listHome.setAdapter(adapterSearchCattle);
    }

}
