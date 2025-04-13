package com.example.appdereunioes.ui.CadastroEvento;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.appdereunioes.R;

public class CadastroEventoFragment extends Fragment {
    private static final int REQUEST_IMAGE_PICK = 1;
    private ImageButton btnFoto;
    private EditText editAnfitriao, editRua, editData, editHora, editBairro, editNumero;
    private Button btnSalvar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cadastro_evento, container, false);

        editAnfitriao = root.findViewById(R.id.editAnfitriao);
        editBairro = root.findViewById(R.id.editBairro);
        editRua = root.findViewById(R.id.editRua);
        editNumero = root.findViewById(R.id.editNumero);
        editData = root.findViewById(R.id.editData);
        editHora = root.findViewById(R.id.editHorario);
        btnFoto = root.findViewById(R.id.btnFoto);
        btnSalvar = root.findViewById(R.id.btnCriarEvento);

        btnFoto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_IMAGE_PICK);
        }); // <<<<< esse parêntese estava faltando aqui

        btnSalvar.setOnClickListener(v -> {
            String anfitriao = editAnfitriao.getText().toString();
            String bairro = editBairro.getText().toString();
            String rua = editRua.getText().toString();
            String numero = editNumero.getText().toString();
            String data = editData.getText().toString();
            String hora = editHora.getText().toString();

            Toast.makeText(getContext(), "Encontro salvo!", Toast.LENGTH_SHORT).show();

                // (Opcional) Limpar os campos
                //editTitulo.setText("");
                //editDescricao.setText("");
                //editData.setText("");
                //editHora.setText("");
                //  editLocal.setText("");
            });

        ;return root;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                btnFoto.setImageURI(selectedImageUri); // Mostra a imagem no botão
            }
        }
    }

}