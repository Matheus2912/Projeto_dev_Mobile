package com.example.appdereunioes.User;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PresencasAdapter extends RecyclerView.Adapter<PresencasAdapter.ViewHolder> {

    private List<String> emails;

    public PresencasAdapter(List<String> emails) {
        this.emails = emails;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView emailText;

        public ViewHolder(View itemView) {
            super(itemView);
            emailText = itemView.findViewById(android.R.id.text1);
        }
    }

    @Override
    public PresencasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PresencasAdapter.ViewHolder holder, int position) {
        holder.emailText.setText(emails.get(position));
    }

    @Override
    public int getItemCount() {
        return emails.size();
    }
}
