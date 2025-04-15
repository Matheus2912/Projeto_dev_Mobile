package com.example.appdereunioes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appdereunioes.User.CadastroActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail, editSenha;
    private Button btnEntrar;
    private TextView txtCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        btnEntrar = findViewById(R.id.btnEntrar);
        txtCadastro = findViewById(R.id.txtCadastro);

        btnEntrar.setOnClickListener(v -> {
            String email = editEmail.getText().toString().trim();
            String senha = editSenha.getText().toString().trim();

            SharedPreferences prefs = getSharedPreferences("usuarios", MODE_PRIVATE);
            String savedEmail = prefs.getString("email", null);
            String savedSenha = prefs.getString("senha", null);

            if (email.equals(savedEmail) && senha.equals(savedSenha)) {
                // Armazena o usuário logado com a chave "usuario_logado"
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("usuario_logado", email);
                editor.apply();

                Toast.makeText(this, "Login realizado!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Email ou senha incorretos", Toast.LENGTH_SHORT).show();
            }
        });

        txtCadastro.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, CadastroActivity.class));
        });
    }
}


