package br.com.junior.guia.retrofit;


import br.com.junior.guia.service.CategoriaService;
import br.com.junior.guia.service.EmpresaService;
import br.com.junior.guia.service.SubcategoriaService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Junior Bezerra on 02/03/2017.
 */

public class RetrofitInicializador {

    private final Retrofit retrofit;

    public RetrofitInicializador(){

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.137.187:8081/AppGuiaQuixada/rest/")
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    public CategoriaService getCategoriaService(){
        return retrofit.create(CategoriaService.class);
    }

    public SubcategoriaService getSubcategoria(){
        return retrofit.create(SubcategoriaService.class);
    }

    public EmpresaService getEmpresa(){
        return retrofit.create(EmpresaService.class);
    }

}
