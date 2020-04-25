package com.ihsinformatics.korona.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ihsinformatics.korona.R;
import com.ihsinformatics.korona.model.partners.BasePartners;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PartnersAdapter extends RecyclerView.Adapter<PartnersAdapter.PartnersViewHolder> {

    private List<BasePartners> partners;

    public PartnersAdapter(List<BasePartners> partners) {
        this.partners = partners;
    }

    private Context context;

    @NonNull
    @Override
    public PartnersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_partners, parent, false);
        context = parent.getContext();
        return new PartnersAdapter.PartnersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PartnersViewHolder holder, int position) {
        holder.partnerName.setText(partners.get(position).getName());
        Picasso.get().load(partners.get(position).getImageUrl()).into(holder.logo);

    }

    @Override
    public int getItemCount() {
        return partners.size();
    }

    public class PartnersViewHolder extends RecyclerView.ViewHolder {

        ImageView logo;
        TextView partnerName;

        //TextView description;

        public PartnersViewHolder(@NonNull View itemView) {
            super(itemView);

            logo = itemView.findViewById(R.id.logo);
            partnerName = itemView.findViewById(R.id.name);
        }
    }
}
