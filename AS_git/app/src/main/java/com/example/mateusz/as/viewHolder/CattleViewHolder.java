package com.example.mateusz.as.viewHolder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.mateusz.as.R;
import com.example.mateusz.as.saveModelFragments.CattleFragment;
import com.example.mateusz.as.show.CattleInfoFragment;

public class CattleViewHolder extends RecyclerView.ViewHolder {

    private TextView earring, name, birthDate;


    public CattleViewHolder(@NonNull View itemView, final Fragment cattleHome) {
        super(itemView);
        earring = itemView.findViewById(R.id.earring_title_cattle);
        name = itemView.findViewById(R.id.name_title_cattle);
        birthDate = itemView.findViewById(R.id.birth_date_title_cattle);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CattleInfoFragment cattleFragment = new CattleInfoFragment();
                Bundle bundle = new Bundle();
                bundle.putString(CattleInfoFragment.CATTLE_EARRNIG, earring.getText().toString());
                cattleFragment.setArguments(bundle);
                FragmentTransaction ft = cattleHome.getFragmentManager().beginTransaction();
                ft.replace(R.id.container_fragment, cattleFragment);
                ft.addToBackStack("tag");
                ft.commit();
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CattleFragment cattleFragment = new CattleFragment();
                Bundle bundle = new Bundle();
                bundle.putString(CattleInfoFragment.CATTLE_EARRNIG, earring.getText().toString());
                cattleFragment.setArguments(bundle);
                FragmentTransaction ft = cattleHome.getFragmentManager().beginTransaction();
                ft.replace(R.id.container_fragment, cattleFragment);
                ft.addToBackStack("tag");
                ft.commit();
                return false;
            }
        });

    }

    public void setEarring(String earring) {
        this.earring.setText(earring);
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setBirthDate(String birthDate) {
        this.birthDate.setText(birthDate);
    }
}
