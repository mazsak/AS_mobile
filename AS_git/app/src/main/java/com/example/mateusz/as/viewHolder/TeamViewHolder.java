package com.example.mateusz.as.viewHolder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.mateusz.as.R;
import com.example.mateusz.as.saveModelFragments.TeamFragment;
import com.example.mateusz.as.show.ListCattleFragment;
import com.example.mateusz.as.show.ListFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeamViewHolder extends RecyclerView.ViewHolder {

    public static final String GROUP_ID = "ID_GROUP_TO_CATTLE";

    private TextView name, numberOfCattle, type;
    private List<String> typeName = new ArrayList<>();
    private int id;


    public TeamViewHolder(@NonNull View itemView, final ListFragment home) {
        super(itemView);
        name = itemView.findViewById(R.id.name_title_team);
        numberOfCattle = itemView.findViewById(R.id.number_of_cattle);
        type = itemView.findViewById(R.id.type_title_team);
        typeName.addAll(Arrays.asList(itemView.getResources().getStringArray(R.array.type_group)));
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListCattleFragment listCattleFragment = new ListCattleFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(GROUP_ID, id);
                listCattleFragment.setArguments(bundle);
                FragmentTransaction ft = home.getFragmentManager().beginTransaction();
                ft.replace(R.id.container_fragment, listCattleFragment);
                ft.commit();
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                TeamFragment listCattleFragment = new TeamFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(GROUP_ID, id);
                listCattleFragment.setArguments(bundle);
                FragmentTransaction ft = home.getFragmentManager().beginTransaction();
                ft.replace(R.id.container_fragment, listCattleFragment);
                ft.commit();
                return false;
            }
        });
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setNumberOfCattle(int numberOfCattle) {
        this.numberOfCattle.setText(String.valueOf(numberOfCattle));
    }

    public void setType(String type) {
        if (type.equals("EAT"))
            this.type.setText(typeName.get(0));
        else
            this.type.setText(typeName.get(1));
    }

    public void setId(int id) {
        this.id = id;
    }
}
