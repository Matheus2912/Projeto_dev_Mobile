package com.example.appdereunioes.User;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appdereunioes.R;
import com.example.appdereunioes.LoginActivity;

public class CadastroActivity extends AppCompatActivity {

    private EditText editNome, editEmail, editSenha;
    private Button btnCadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_cadastro);
        editNome = findViewById(R.id.editNome);
        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(v -> {
            String nome = editNome.getText().toString().trim();
            String email = editEmail.getText().toString().trim();
            String senha = editSenha.getText().toString().trim();

                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences prefs = getSharedPreferences("usuarios", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("nome", nome);
                editor.putString("email", email);
                editor.putString("senha", senha);
                editor.apply();

                Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            });
        }
    }
