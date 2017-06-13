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
import br.com.junior.guia.adapter.AdapterSubcategoria;
import br.com.junior.guia.model.Categoria;
import br.com.junior.guia.model.Subcategoria;
import br.com.junior.guia.retrofit.RetrofitInicializador;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubCategoriaActivity extends AppCompatActivity {

    private Categoria categoria;
    private SwipeRefreshLayout swiperSubcategoria;
    private ListView listaSubcategoria;
    private AdapterSubcategoria adapterSubcategoria;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_categoria);
        iniciarVariavieis();
        Intent intent = getIntent();
        categoria = new Categoria();
        categoria = (Categoria) intent.getSerializableExtra("categoria");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(categoria.getDescricao());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        listaSubcategoria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Subcategoria subcategoria = (Subcategoria) adapterSubcategoria.getItem(position);

                Intent intent = new Intent(SubCategoriaActivity.this,EmpresaActivity.class);
                intent.putExtra("subcategoria",subcategoria);
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
        swiperSubcategoria.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                carregarLista();
                swiperSubcategoria.setRefreshing(false);
            }
        });
    }

    public void carregarLista(){

        Call<List<Subcategoria>> call = new RetrofitInicializador().getSubcategoria().ListarSubcategoria(categoria.getId());
        swiperSubcategoria.setRefreshing(true);
        call.enqueue(new Callback<List<Subcategoria>>() {

            @Override
            public void onResponse(Call<List<Subcategoria>> call, Response<List<Subcategoria>> response) {
                if (response.isSuccessful()) {

                    List<Subcategoria> subcategorias = response.body();
                    swiperSubcategoria.setRefreshing(false);

                    if (subcategorias.isEmpty()) {
                        swiperSubcategoria.setRefreshing(false);
                        listaSubcategoria.setVisibility(View.GONE);
                        relativeLayout.setVisibility(View.VISIBLE);
                    }
                    adapterSubcategoria = new AdapterSubcategoria(getBaseContext(), subcategorias);
                    listaSubcategoria.setAdapter(adapterSubcategoria);

                }

            }

            @Override
            public void onFailure(Call<List<Subcategoria>> call, Throwable t) {
                swiperSubcategoria.setRefreshing(false);
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
        if (swiperSubcategoria.isRefreshing()){
            swiperSubcategoria.setRefreshing(false);
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

    public void iniciarVariavieis(){
        listaSubcategoria = (ListView) findViewById(R.id.ListaSubcategoria);
        swiperSubcategoria = (SwipeRefreshLayout) findViewById(R.id.swipeSubcategoria);
        relativeLayout = (RelativeLayout) findViewById(R.id.listaVaziaSubcategoria);
        swiperSubcategoria.setColorSchemeColors(Color.GRAY);
    }

}
