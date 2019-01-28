package com.example.mateusz.as.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.mateusz.as.R;
import com.example.mateusz.as.show.ListFragment;

public class CattleViewHolder extends RecyclerView.ViewHolder {

    private TextView earring, name, birthDate;


    public CattleViewHolder(@NonNull View itemView) {
        super(itemView);
        earring = itemView.findViewById(R.id.earring_title_cattle);
        name = itemView.findViewById(R.id.name_title_cattle);
        birthDate = itemView.findViewById(R.id.birth_date_title_cattle);
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
