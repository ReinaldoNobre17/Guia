package br.com.junior.guia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.junior.guia.R;
import br.com.junior.guia.model.Subcategoria;

public class AdapterSubcategoria extends BaseAdapter {

    private final List<Subcategoria> subcategorias;
    private final Context context;

    public AdapterSubcategoria(Context context, List<Subcategoria> subcategorias) {
        this.subcategorias = subcategorias;
        this.context = context;
    }


    @Override
    public int getCount() {
        return subcategorias.size();
    }

    @Override
    public Object getItem(int position) {
        return subcategorias.get(position);
    }

    @Override
    public long getItemId(int position) {
        return subcategorias.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Subcategoria subcategoria = subcategorias.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = convertView;

        if(view == null){
            view = inflater.inflate(R.layout.item_subcategoria,parent,false);
        }

        TextView descricao = (TextView) view.findViewById(R.id.nomeCategoria);
        descricao.setText(subcategoria.getDescricao());

        return view;
    }
}
