package br.com.junior.guia;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import java.util.List;
import br.com.junior.guia.adapter.AdapterCategoria;
import br.com.junior.guia.model.Categoria;
import br.com.junior.guia.model.Subcategoria;
import br.com.junior.guia.retrofit.RetrofitInicializador;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoriaActivity extends AppCompatActivity {

    private ListView listaCategooria;
    private SwipeRefreshLayout swipeCategoria;
    private AdapterCategoria adapterCategoria;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Guia Quixadá");
        setSupportActionBar(toolbar);
        inciarVariaveis();

        listaCategooria.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Categoria categoria = (Categoria) adapterCategoria.getItem(position);

                Intent intent = new Intent(CategoriaActivity.this,SubCategoriaActivity.class);
                intent.putExtra("categoria",categoria);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (swipeCategoria.isRefreshing()){
            swipeCategoria.setRefreshing(false);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        carregarLista();
    }

    @Override
    protected void onResume() {
        super.onResume();
        swipeCategoria.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                carregarLista();
                listaCategooria.deferNotifyDataSetChanged();
                swipeCategoria.setRefreshing(false);
            }
        });
    }

    public void carregarLista(){

        Call<List<Categoria>> call = new RetrofitInicializador().getCategoriaService().listaCategoria();
        swipeCategoria.setRefreshing(true);
        call.enqueue(new Callback<List<Categoria>>() {

            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                if (response.isSuccessful()) {

                    List<Categoria> categorias = response.body();
                    adapterCategoria = new AdapterCategoria(getBaseContext(), categorias);
                    listaCategooria.setAdapter(adapterCategoria);
                    swipeCategoria.setRefreshing(false);

                    if (categorias.isEmpty()) {
                        swipeCategoria.setRefreshing(false);
                        listaCategooria.setVisibility(View.GONE);
                        relativeLayout.setVisibility(View.VISIBLE);
                    }


                }

            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                swipeCategoria.setRefreshing(false);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.btn_sobre:

                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
    public void inciarVariaveis(){
        listaCategooria = (ListView) findViewById(R.id.ListaCategoria);
        swipeCategoria = (SwipeRefreshLayout) findViewById(R.id.swipeCategoria);
        relativeLayout = (RelativeLayout) findViewById(R.id.listaVazia);
        swipeCategoria.setColorSchemeColors(Color.GRAY);
    }
}
