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

public class ListFragment extends Fragment {


    private RecyclerView listHome;
    private AdapterCowshed adapterCowshed;
    private AdapterTeam adapterTeam;
    private AdapterSearchCattle  adapterSearchCattle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            adapterSearchCattle = new AdapterSearchCattle(getArguments().getIntegerArrayList(BarcodeFragment.ID_CATTLES));
            listHome.setAdapter(adapterSearchCattle);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        initFirst(view);

        if(getArguments() != null){
            adapterSearchCattle = new AdapterSearchCattle(getArguments().getIntegerArrayList(BarcodeFragment.ID_CATTLES));
            listHome.setAdapter(adapterSearchCattle);
        }

        return view;
    }

    public void initFirst(View view) {
        adapterCowshed = new AdapterCowshed(this);
        listHome = view.findViewById(R.id.list_home);
        listHome.setHasFixedSize(true);
        listHome.setLayoutManager(new LinearLayoutManager(view.getContext()));
        listHome.setAdapter(adapterCowshed);
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
