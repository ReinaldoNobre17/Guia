package br.com.junior.guia.model;

import java.io.Serializable;

/**
 * Created by Junior Bezerra on 28/02/2017.
 */

public class Categoria implements Serializable {

    private Long id;
    private String descricao;
    private byte[] imagem;



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

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
}
