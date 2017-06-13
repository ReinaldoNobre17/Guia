package br.com.junior.guia;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.junior.guia.model.Empresa;
import br.com.junior.guia.model.Subcategoria;

public class EmpresaInformacaoActivity extends AppCompatActivity {

    private Empresa empresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa_informacao);
        Intent intent = getIntent();
        empresa = new Empresa();
        empresa = (Empresa) intent.getSerializableExtra("empresa");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(empresa.getDescricao());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bitmap bmp = BitmapFactory.decodeByteArray(empresa.getImagem(), 0, empresa.getImagem().length);
        ImageView imagenEmpresa = (ImageView) findViewById(R.id.imagemEmpresaInformacao);
        imagenEmpresa.setImageBitmap(bmp);

        TextView enderecoEmpresa = (TextView) findViewById(R.id.endereco);
        enderecoEmpresa.setText(empresa.getRua()+", "+empresa.getNumero()+", "+empresa.getCidade());

        TextView telefoneEmpresa = (TextView) findViewById(R.id.telefone);
        telefoneEmpresa.setText(empresa.getTelefone());


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void ligar(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("tel:" + empresa.getTelefone()));
        startActivity(intent);
    }

    public void mapa(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:0,0?q=" + empresa.getRua()+empresa.getNumero()+empresa.getCidade()));
        startActivity(intent);
    }


}
