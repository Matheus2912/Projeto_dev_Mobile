package com.example.appdereunioes.ui.evento;


import android.net.Uri;

import java.io.Serializable;

public class Evento {
    private String anfitriao;
    private String bairro;
    private String rua;
    private String numero;
    private String data;
    private String hora;
    private Uri imagemUri; // opcional

    public Evento(String anfitriao, String endereco, String dataHora, Uri imagemUri) {
        this.anfitriao = anfitriao;
        this.rua = endereco; // pode adaptar
        this.bairro = "";
        this.numero = "";
        this.data = dataHora;
        this.hora = "";
        this.imagemUri = imagemUri;
    }


    // Getters
    public String getAnfitriao() { return anfitriao; }
    public String getRua() {
        return rua;
    }
    public String getBairro() {
        return bairro;
    }
    public String getNumero() {
        return numero;
    }
    public String getEndereco() { return rua + "\n" + bairro + "\n" + "Número " + numero; }
    public String getDataHora() { return data + " " + hora; }
    public Uri getImagemUri() { return imagemUri; }
}
