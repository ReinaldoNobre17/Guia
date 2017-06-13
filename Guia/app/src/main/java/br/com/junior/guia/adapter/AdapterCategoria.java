package br.com.junior.guia.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.junior.guia.R;
import br.com.junior.guia.model.Categoria;

/**
 * Created by Junior Bezerra on 11/06/2017.
 */

public class AdapterCategoria extends BaseAdapter {


    private final Context context;
    private final List<Categoria> categorias;

    public AdapterCategoria(Context context, List<Categoria> categorias) {
        this.context = context;
        this.categorias = categorias;
    }

    @Override
    public int getCount() {
        return categorias.size();
    }

    @Override
    public Object getItem(int position) {
        return categorias.get(position);
    }

    @Override
    public long getItemId(int position) {
        return categorias.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Categoria categoria = categorias.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;

        if(view == null){
            view = inflater.inflate(R.layout.item_categoria,parent,false);
        }

        TextView nomeCategoria = (TextView) view.findViewById(R.id.nomeCategoria);
        nomeCategoria.setText(categoria.getDescricao());

        Bitmap bmp = BitmapFactory.decodeByteArray(categoria.getImagem(), 0, categoria.getImagem().length);
        ImageView imagenCategoria = (ImageView) view.findViewById(R.id.imagemCategoria);
        imagenCategoria.setImageBitmap(bmp);

        return view;
    }
}
