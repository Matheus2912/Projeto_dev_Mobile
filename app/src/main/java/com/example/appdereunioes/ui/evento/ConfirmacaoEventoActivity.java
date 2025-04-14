package com.example.appdereunioes.ui.evento;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appdereunioes.R;

public class ConfirmacaoEventoActivity extends AppCompatActivity {

    private ImageView fotoEvento;
    private TextView txtAnfitriao, txtRua, txtBairro, txtNumero, txtDataHora;;

    private Button btnConfirmar, btnVerMapa, btnVisualizarConfirmados;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // OCULTA A ACTION BAR
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.fragment_detalhes_evento);

        fotoEvento = findViewById(R.id.fotoEvento);
        txtAnfitriao = findViewById(R.id.txtAnfitriao);
        txtRua = findViewById(R.id.txtRua);
        txtBairro = findViewById(R.id.txtBairro);
        txtNumero = findViewById(R.id.txtNumero);
        txtDataHora = findViewById(R.id.txtDataHora);

        btnConfirmar = findViewById(R.id.btnConfirmar);
        btnVerMapa = findViewById(R.id.btnVerMapa);
        btnVisualizarConfirmados = findViewById(R.id.btnVisualizarConfirmados);

        // Recupera o índice do evento passado pela intent
        int eventoIndex = getIntent().getIntExtra("eventoIndex", -1);

        if (eventoIndex != -1 && eventoIndex < EventoRepository.getEventos().size()) {
            Evento evento = EventoRepository.getEventos().get(eventoIndex);

            txtAnfitriao.setText("Anfitrião: " + evento.getAnfitriao());
            txtRua.setText("Rua: " + evento.getRua());
            txtBairro.setText("Bairro: " + evento.getBairro());
            txtNumero.setText("Número: " + evento.getNumero());
            txtDataHora.setText("Data e Hora: " + evento.getDataHora());


            if (evento.getImagemUri() != null) {
                fotoEvento.setImageURI(evento.getImagemUri());
            } else {
                fotoEvento.setImageResource(R.drawable.ic_launcher_foreground);
            }

            btnConfirmar.setOnClickListener(v -> {
                Toast.makeText(this, "Presença confirmada!", Toast.LENGTH_SHORT).show();
                // Aqui você pode adicionar o evento a uma lista de confirmados se quiser
            });

            btnVerMapa.setOnClickListener(v -> {
                String endereco = evento.getRua() + ", " + evento.getNumero() + ", " + evento.getBairro();
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(endereco));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                } else {
                    Toast.makeText(this, "Google Maps não instalado", Toast.LENGTH_SHORT).show();
                }
            });

            btnVisualizarConfirmados.setOnClickListener(v -> {
                Toast.makeText(this, "Funcionalidade em construção...", Toast.LENGTH_SHORT).show();
            });

        } else {
            Toast.makeText(this, "Evento não encontrado!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
