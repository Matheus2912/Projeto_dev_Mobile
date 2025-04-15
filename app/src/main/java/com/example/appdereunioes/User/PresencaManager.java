package com.example.appdereunioes.User;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class PresencaManager {
    private static final String PREF_NAME = "presencas_confirmadas";

    public static void confirmarPresenca(Context context, String eventoId, String userEmail) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Set<String> presencas = prefs.getStringSet(eventoId, new HashSet<>());
        presencas = new HashSet<>(presencas); // evitar erro com set imutável
        presencas.add(userEmail);
        editor.putStringSet(eventoId, presencas);
        editor.apply();
    }

    public static Set<String> getPresencas(Context context, String eventoId) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getStringSet(eventoId, new HashSet<>());
    }

    public static void clearAll(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().clear().apply();
    }
}

