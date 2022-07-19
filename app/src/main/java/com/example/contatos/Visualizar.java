package com.example.contatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Visualizar extends AppCompatActivity  implements View.OnClickListener {
    private Button BtnExcluirVisu, BtnAltVisu, BtnVoltarVisu;
    private EditText edtCodVisu, edtNomeVisu, edtNumeroVisu, edtEmailVisu, edtEnderecoVisu, edtDataVisu;
    private ArrayList<Contato> lista;
    private Adapter adapter;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualizar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lista = getIntent().getParcelableArrayListExtra("lista");

        BtnExcluirVisu = findViewById(R.id.BtnExcluirVisu);
        BtnExcluirVisu.setOnClickListener(this);

        BtnAltVisu = findViewById(R.id.BtnAltVisu);
        BtnAltVisu.setOnClickListener(this);

        BtnVoltarVisu = findViewById(R.id.BtnVoltarVisu);
        BtnVoltarVisu.setOnClickListener(this);

        edtNomeVisu = findViewById(R.id.edtNomeVisu);
        edtNumeroVisu = findViewById(R.id.edtNumeroVisu);
        edtEmailVisu = findViewById(R.id.edtEmailVisu);
        edtEnderecoVisu = findViewById(R.id.edtEnderecoVisu);
        edtDataVisu = findViewById(R.id.edtDataVisu);
        edtCodVisu = findViewById(R.id.edtCodigoVisu);


        String posi = getIntent().getStringExtra("pos");
        pos = Integer.parseInt(posi);
        edtCodVisu.setText(posi);

        //Toast.makeText(this, "Click Visu: " + pos, Toast.LENGTH_SHORT).show();

        edtNomeVisu.setText(lista.get(pos).getNome());
        edtNumeroVisu.setText(lista.get(pos).getNumero());
        edtEmailVisu.setText(lista.get(pos).getEmail());
        edtEnderecoVisu.setText(lista.get(pos).getEndereco());
        edtDataVisu.setText(lista.get(pos).getData());

        /*String nome = getIntent().getStringExtra("nome");
        edtNomeVisu.setText(nome);

        String numero = getIntent().getStringExtra("numero");
        edtNumeroVisu.setText(numero);

        String email = getIntent().getStringExtra("email");
        edtEmailVisu.setText(email);

        String endereco = getIntent().getStringExtra("endereco");
        edtEnderecoVisu.setText(endereco);

        String data = getIntent().getStringExtra("data");
        edtDataVisu.setText(data);*/
    }

    private void limpar(){
        edtCodVisu.setText("");
        edtNomeVisu.setText("");
        edtNumeroVisu.setText("");
        edtEmailVisu.setText("");
        edtEnderecoVisu.setText("");
        edtDataVisu.setText("");
    }

    public boolean onSupportNavigateUp(){
        finalizar();

        return true;
    }

    @Override
    public void onBackPressed() {
        finalizar();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        lista = data.getParcelableArrayListExtra("lista");

        adapter.setLista(lista);
        adapter.notifyDataSetChanged();
    }

    private void finalizar(){
        Intent intent = new Intent();
        intent.putExtra("lista", lista);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void voltarVisu(View view) {
        finalizar();
    }

    public void excluirCont() {
        limpar();
        //String posi = getIntent().getStringExtra("pos");
        //int pos = Integer.parseInt(posi);
        lista.remove(pos);
        //Intent intentCancelarNovo = new Intent(this, MainActivity.class);
        //startActivity(intentCancelarNovo);
        finalizar();
    }

    private void altVisu(){
        Contato contato = new Contato();

        contato.setNome(edtNomeVisu.getText().toString());
        contato.setNumero(edtNumeroVisu.getText().toString());
        contato.setEmail(edtEmailVisu.getText() + "");
        contato.setEndereco(edtEnderecoVisu.getText().toString());
        contato.setData(edtDataVisu.getText().toString());

        lista.set(pos, contato);

        limpar();

        finalizar();

        //String telefone = ((EditText)findViewById(R.id.edtNumeroNovo)).getText().toString();
    }

    @Override
    public void onClick(View v) {
        if(v.equals(BtnVoltarVisu)){
            finalizar();
        }

        else if(v.equals(BtnExcluirVisu)){
            excluirCont();
        }

        else if(v.equals(BtnAltVisu)){
            altVisu();
        }
    }
}