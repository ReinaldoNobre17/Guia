package br.com.junior.guia;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.List;

import br.com.junior.guia.adapter.AdapterEmpresa;
import br.com.junior.guia.model.Empresa;
import br.com.junior.guia.model.Subcategoria;
import br.com.junior.guia.retrofit.RetrofitInicializador;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmpresaActivity extends AppCompatActivity {

    private Subcategoria subcategoria;
    private SwipeRefreshLayout swipeRefreshLayout;
    private AdapterEmpresa adapterEmpresa;
    private RelativeLayout relativeLayout;
    private ListView listaEmpresa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa);
        variaveis();
        Intent intent = getIntent();
        subcategoria = new Subcategoria();
        subcategoria = (Subcategoria) intent.getSerializableExtra("subcategoria");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(subcategoria.getDescricao());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listaEmpresa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Empresa empresa = (Empresa) adapterEmpresa.getItem(position);

                Intent intent = new Intent(EmpresaActivity.this,EmpresaInformacaoActivity.class);
                intent.putExtra("empresa",empresa);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        carregarLista();
    }

    @Override
    protected void onResume() {
        super.onResume();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                carregarLista();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void carregarLista(){

        Call<List<Empresa>> call = new RetrofitInicializador().getEmpresa().ListarEmpresa(subcategoria.getId());
        swipeRefreshLayout.setRefreshing(true);
        call.enqueue(new Callback<List<Empresa>>() {
            @Override
            public void onResponse(Call<List<Empresa>> call, Response<List<Empresa>> response) {

                if(response.isSuccessful()){
                    List<Empresa> empresas = response.body();
                    swipeRefreshLayout.setRefreshing(false);

                    if (empresas.isEmpty()) {
                        swipeRefreshLayout.setRefreshing(false);
                        listaEmpresa.setVisibility(View.GONE);
                        relativeLayout.setVisibility(View.VISIBLE);
                    }
                    adapterEmpresa = new AdapterEmpresa(getBaseContext(),empresas);
                    listaEmpresa.setAdapter(adapterEmpresa);
                }


            }

            @Override
            public void onFailure(Call<List<Empresa>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Snackbar snackbar = Snackbar.make(findViewById(R.id.frame),"Erro ao conectar ao servidor",Snackbar.LENGTH_LONG)
                        .setAction("Wifi", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                            }
                        });

                snackbar.show();
            }
        });


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
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

    public void variaveis(){
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeEmpresa);
        listaEmpresa = (ListView) findViewById(R.id.ListaEmpresa);
        relativeLayout = (RelativeLayout) findViewById(R.id.listaVaziaEmpresa);
        swipeRefreshLayout.setColorSchemeColors(Color.GRAY);
    }

}
