package com.example.appdereunioes.ui.evento;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appdereunioes.R;

import java.util.List;

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.EventoViewHolder> {
    private List<Evento> eventos;
    private Context context;

    public EventoAdapter(List<Evento> eventos, Context context) {
        this.eventos = eventos;
        this.context = context;
    }

    @NonNull
    @Override
    public EventoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_evento, parent, false);
        return new EventoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventoViewHolder holder, int position) {
        Evento evento = eventos.get(position);
        holder.txtEndereco.setText(evento.getEndereco());
        holder.txtDataHora.setText(evento.getDataHora());

        if (evento.getImagemUri() != null) {
            try {
                holder.imgEvento.setImageURI(evento.getImagemUri());
            } catch (Exception e) {
                holder.imgEvento.setImageResource(R.drawable.ic_launcher_foreground);
            }
        } else {
            holder.imgEvento.setImageResource(R.drawable.ic_launcher_foreground);
        }

        // Navegar para a tela de confirmação ao clicar
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ConfirmacaoEventoActivity.class);
            intent.putExtra("eventoIndex", position);
            context.startActivity(intent);
            intent.putExtra("eventoIndex", position);
            context.startActivity(intent);
        });
    }
    @Override
    public int getItemCount() {
        return eventos.size();
    }
    static class EventoViewHolder extends RecyclerView.ViewHolder {
        ImageView imgEvento;
        TextView txtEndereco, txtDataHora;

        public EventoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgEvento = itemView.findViewById(R.id.fotoAnfitriao);
            txtEndereco = itemView.findViewById(R.id.txtEndereco);
            txtDataHora = itemView.findViewById(R.id.dataHora);
        }
    }
}

