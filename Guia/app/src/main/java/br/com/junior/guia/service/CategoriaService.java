package br.com.junior.guia.service;

import java.util.List;

import br.com.junior.guia.model.Categoria;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Junior Bezerra on 04/03/2017.
 */

public interface CategoriaService {

    @GET("categoria")
    Call<List<Categoria>> listaCategoria();

}
