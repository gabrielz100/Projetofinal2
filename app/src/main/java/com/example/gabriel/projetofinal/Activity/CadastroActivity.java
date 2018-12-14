package com.example.gabriel.projetofinal.Activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.gabriel.projetofinal.CONF.Configuracaofirebase;
import com.example.gabriel.projetofinal.Entidades.Usuarios;
import com.example.gabriel.projetofinal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroActivity extends AppCompatActivity {

    private EditText edtCadEmail;
    private EditText edtCadNome;
    private EditText edtCadSenha;
    private EditText edtCadConfirmarSenha;
    private EditText edtCadAniversario  ;
    private RadioButton rbMasculino;
    private RadioButton rbFeminino;
    private Button btnGravar;
    private Usuarios usuarios;
    private FirebaseAuth autenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtCadEmail = (EditText)findViewById(R.id.edtCadEmail);
        edtCadSenha = (EditText)findViewById(R.id.edtCadSenha);
        edtCadConfirmarSenha = (EditText)findViewById(R.id.edtCadConfirmaSenha);
        edtCadNome = (EditText)findViewById(R.id.edtCadNome);
        edtCadAniversario = (EditText)findViewById(R.id.edtCadAniversario);
        rbFeminino = (RadioButton) findViewById(R.id.rbFeminino);
        rbMasculino = (RadioButton) findViewById(R.id.rbMasculino);
        btnGravar = (Button)findViewById(R.id.btnGravar);

        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtCadSenha.getText().toString().equals(edtCadConfirmarSenha.getText().toString())){

                    usuarios = new Usuarios();
                    usuarios.setNome(edtCadNome.getText().toString());
                    usuarios.setEmail(edtCadEmail.getText().toString());
                    usuarios.setSenha(edtCadSenha.getText().toString());
                    usuarios.setAniversario(edtCadAniversario.getText().toString());
                    usuarios.setNome(edtCadNome.getText().toString());

                    if (rbFeminino.isChecked()){
                        usuarios.setSexo("Feminino");
                    }else {
                        usuarios.setSexo("Masculino");
                    }

                }else{
                    Toast.makeText(CadastroActivity.this, "Senhas Diferentes", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void cadastrarUsuario(){

        autenticacao = Configuracaofirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(

                usuarios.getEmail(),
                usuarios.getSenha()
        ).addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()){
                Toast.makeText(CadastroActivity.this,"Cadastro Feito", Toast.LENGTH_SHORT).show();

            }
            }
        });

    }
}
