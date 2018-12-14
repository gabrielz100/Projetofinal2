package com.example.gabriel.projetofinal.Lembrar;

import android.content.Context;
import android.content.SharedPreferences;

public class Opcoes {

    private Context context;
    private SharedPreferences preferences;
    private String Nome_Arquivo = "aplicativo do projeto";
    private int MODE = 0;
    private SharedPreferences.Editor editor;

    private final String CHAVE_IDENTIFICACAO = "identificarUsuario";
    private final String CHAVE_NOME = "nomeUsuario";

    public Opcoes(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(Nome_Arquivo, MODE);

        editor = preferences.edit();
    }

    public void salvarUsuario( String identificardorUsuario, String nomeUsuario){
        editor.putString(CHAVE_IDENTIFICACAO, identificardorUsuario);
        editor.putString(CHAVE_NOME, nomeUsuario);

    }

    public String getIdentificador(){
        return preferences.getString(CHAVE_IDENTIFICACAO, null);
    }

    public String getNome() {
        return preferences.getString(CHAVE_NOME, null);
    }
