package com.ihsinformatics.korona.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ihsinformatics.korona.R;
import com.ihsinformatics.korona.model.question.QuizResponse;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class ActivitiesAdapter extends RecyclerView.Adapter<ActivitiesAdapter.ActivityViewHolder> {


    private List<QuizResponse> formTypeResponses;
    private AdapterListener.FormClickedListener listener;
    private Context context;

    public ActivitiesAdapter(List<QuizResponse> formTypeResponses,AdapterListener.FormClickedListener listener) {

        this.formTypeResponses = formTypeResponses;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_partners, parent, false);
        context = parent.getContext();
        return new ActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {
        holder.partnerName.setText(formTypeResponses.get(position).getFormGroup().getDefinitionName());
        if (formTypeResponses.get(position).getFormGroup().getDefinitionName().contains("Mental Health"))
            Picasso.get().load("https://i.ibb.co/vV6qWpm/PS-ZIndagi.png").into(holder.logo);
        else
            Picasso.get().load(R.drawable.logo_main).into(holder.logo);

        holder.parent.setOnClickListener(new ItemListener(position));
        holder.url.setText(formTypeResponses.get(position).getFormGroup().getDescription());

    }

    @Override
    public int getItemCount() {
        return formTypeResponses.size();
    }

    public class ActivityViewHolder extends RecyclerView.ViewHolder {

        private final ImageView logo;
        private final TextView partnerName;
        private final RelativeLayout parent;
        private final TextView url;


        public ActivityViewHolder(@NonNull View itemView) {
            super(itemView);

            logo = itemView.findViewById(R.id.logo);
            partnerName = itemView.findViewById(R.id.name);
            parent = itemView.findViewById(R.id.parent);
            url = itemView.findViewById(R.id.url);

        }
    }

    private class ItemListener implements View.OnClickListener {
        private int position;

        public ItemListener(int position) {

            this.position = position;
        }

        @Override
        public void onClick(View view) {
            listener.onFormClicked(formTypeResponses.get(position));
        }
    }
}
