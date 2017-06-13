package br.com.junior.guia.service;

import java.util.List;

import br.com.junior.guia.model.Subcategoria;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Junior Bezerra on 23/03/2017.
 */

public interface SubcategoriaService {

    @GET("subcategoria/{id}")
    Call<List<Subcategoria>> ListarSubcategoria(@Path("id") Long id);
}
