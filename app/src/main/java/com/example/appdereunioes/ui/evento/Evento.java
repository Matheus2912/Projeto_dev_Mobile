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

    public Evento(String anfitriao, String rua, String numero, String bairro, String data, String hora, Uri imagemUri) {
        this.anfitriao = anfitriao;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.data = data;
        this.hora = hora;
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
    public String getDataHora() { return data + " " + hora; }
    public Uri getImagemUri() { return imagemUri; }
}
