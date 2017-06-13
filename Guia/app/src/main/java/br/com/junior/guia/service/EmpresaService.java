package br.com.junior.guia.service;

import java.util.List;

import br.com.junior.guia.model.Empresa;
import br.com.junior.guia.model.Subcategoria;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Junior Bezerra on 12/06/2017.
 */

public interface EmpresaService {

    @GET("empresa/{id}")
    Call<List<Empresa>> ListarEmpresa(@Path("id") Long id);

}
