package br.com.junior.guia.model;

import java.io.Serializable;

/**
 * Created by Junior Bezerra on 04/03/2017.
 */

public class Subcategoria implements Serializable {

    private Long id;
    private String descricao;
    private Categoria categoria;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return getDescricao();
    }
}
