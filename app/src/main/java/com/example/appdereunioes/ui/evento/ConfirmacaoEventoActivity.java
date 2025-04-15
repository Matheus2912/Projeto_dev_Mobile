package com.example.appdereunioes.ui.evento;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appdereunioes.R;
import com.example.appdereunioes.User.PresencaManager;
import com.example.appdereunioes.User.PresencasConfirmadasActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ConfirmacaoEventoActivity extends AppCompatActivity {

    private ImageView fotoEvento;
    private TextView txtAnfitriao, txtRua, txtBairro, txtNumero, txtDataHora;
    private Button btnConfirmar, btnVerMapa, btnVisualizarConfirmados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Oculta a action bar se houver
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
            String dataHoraOriginal = evento.getDataHora(); // String "dd/MM/yyyy HH:mm"
            String dataHoraFormatada = dataHoraOriginal;
            try {
                SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
                Date dataObj = parser.parse(dataHoraOriginal);
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 'às' HH:mm", Locale.getDefault());
                dataHoraFormatada = formatter.format(dataObj);
            } catch (Exception e) {
                Log.e("ConfirmacaoEvento", "Erro ao formatar data/hora: " + dataHoraOriginal, e);
            }
            txtDataHora.setText("Data e Hora: " + dataHoraFormatada);

            if (evento.getImagemUri() != null) {
                fotoEvento.setImageURI(evento.getImagemUri());
            } else {
                fotoEvento.setImageResource(R.drawable.ic_launcher_foreground);
            }

            btnConfirmar.setOnClickListener(v -> {
                String email = getSharedPreferences("usuarios", MODE_PRIVATE)
                        .getString("usuario_logado", null);

                if (email != null) {
                    PresencaManager.confirmarPresenca(this, String.valueOf(eventoIndex), email);
                    Toast.makeText(this, "Presença confirmada!", Toast.LENGTH_SHORT).show();
                    finish(); // ou redirecione para outra tela, se necessário
                } else {
                    Toast.makeText(this, "Erro: usuário não logado!", Toast.LENGTH_SHORT).show();
                }
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

            // Alteração: abrindo a activity de presenças confirmadas em vez de exibir apenas um Toast.
            btnVisualizarConfirmados.setOnClickListener(v -> {
                Intent intent = new Intent(ConfirmacaoEventoActivity.this, PresencasConfirmadasActivity.class);
                intent.putExtra("eventoId", String.valueOf(eventoIndex));
                // Se não, você pode usar o nome do anfitrião ou qualquer outra informação significativa.
                intent.putExtra("eventoTitulo", evento.getAnfitriao());
                startActivity(intent);
            });

        } else {
            Toast.makeText(this, "Evento não encontrado!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
