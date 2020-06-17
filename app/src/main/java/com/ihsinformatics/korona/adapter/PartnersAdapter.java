package com.ihsinformatics.korona.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ihsinformatics.korona.R;
import com.ihsinformatics.korona.model.partners.BasePartners;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

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
        holder.partnerName.setText(partners.get(position).getPartnerName());
        holder.url.setText(partners.get(position).getUrl());
        Picasso.get().load(partners.get(position).getLogoUrl()).into(holder.logo);
        holder.parent.setOnClickListener(new ItemListener(partners.get(position).getUrl()));
    }

    @Override
    public int getItemCount() {
        return partners.size();
    }

    public class PartnersViewHolder extends RecyclerView.ViewHolder {

        private final TextView url;
        private final ImageView logo;
        private final TextView partnerName;
        private final RelativeLayout parent;

        //TextView description;

        public PartnersViewHolder(@NonNull View itemView) {
            super(itemView);

            logo = itemView.findViewById(R.id.logo);
            partnerName = itemView.findViewById(R.id.name);
            url = itemView.findViewById(R.id.url);
            parent = itemView.findViewById(R.id.parent);
        }
    }

    private class ItemListener implements View.OnClickListener {
        private String url;

        public ItemListener(String url) {
            this.url = url;
        }

        @Override
        public void onClick(View view) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            context.startActivity(i);
        }
    }
}
