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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

        // Quebrando o endereço em linhas
        String enderecoFormatado = evento.getRua() + "\n" +
                evento.getBairro() + "\n" +
                evento.getNumero();
        holder.txtEndereco.setText(enderecoFormatado);

        // Formatando data e hora: "25/03 às 20:00"
        String dataHoraFormatada = formatarDataHora(evento.getDataHora());
        holder.txtDataHora.setText(dataHoraFormatada);

        // Carregar imagem
        if (evento.getImagemUri() != null) {
            try {
                holder.imgEvento.setImageURI(evento.getImagemUri());
            } catch (Exception e) {
                holder.imgEvento.setImageResource(R.drawable.ic_launcher_foreground);
            }
        } else {
            holder.imgEvento.setImageResource(R.drawable.ic_launcher_foreground);
        }

        // Clicar para abrir detalhe
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ConfirmacaoEventoActivity.class);
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

    // Utilitário para formatar a data e hora
    private String formatarDataHora(String dataHoraStr) {
        try {
            SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
            Date dataHora = parser.parse(dataHoraStr);

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM 'às' HH:mm", Locale.getDefault());
            return formatter.format(dataHora);
        } catch (Exception e) {
            return dataHoraStr; // Caso dê erro, retorna como está
        }
    }
}

