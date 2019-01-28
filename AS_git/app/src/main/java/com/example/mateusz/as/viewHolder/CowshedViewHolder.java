package com.example.mateusz.as.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.mateusz.as.show.ListFragment;
import com.example.mateusz.as.R;

public class CowshedViewHolder extends RecyclerView.ViewHolder {

    private TextView name, numberOfGroup, address;
    private int id;


    public CowshedViewHolder(@NonNull View itemView, final ListFragment home) {
        super(itemView);
        name = itemView.findViewById(R.id.name_title_cowshed);
        numberOfGroup = itemView.findViewById(R.id.number_od_group);
        address = itemView.findViewById(R.id.address_title_cowshed);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home.chosenAdapter(1, id);
            }
        });

    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setNumberOfGroup(int numberOfGroup) {
        this.numberOfGroup.setText(String.valueOf(numberOfGroup));
    }

    public void setAddress(String address) {
        this.address.setText(address);
    }

    public void setId(int id) {
        this.id = id;
    }
}
