package com.example.appdereunioes.User;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdereunioes.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PresencasConfirmadasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView eventoTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presencas_confirmadas);

        recyclerView = findViewById(R.id.recycler_presencas);
        eventoTitulo = findViewById(R.id.evento_titulo);

        String eventoId = getIntent().getStringExtra("eventoId");
        String titulo = getIntent().getStringExtra("eventoTitulo");

        eventoTitulo.setText("Presenças - " + titulo);

        Set<String> presencas = PresencaManager.getPresencas(this, eventoId);
        List<String> lista = new ArrayList<>(presencas);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PresencasAdapter(lista));
    }
}
