package br.com.junior.guia.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import br.com.junior.guia.R;
import br.com.junior.guia.model.Categoria;
import br.com.junior.guia.model.Empresa;

/**
 * Created by Junior Bezerra on 12/06/2017.
 */

public class AdapterEmpresa extends BaseAdapter {


    private final Context context;
    private final List<Empresa> empresas;

    public AdapterEmpresa(Context context, List<Empresa> empresas) {
        this.context = context;
        this.empresas = empresas;
    }

    @Override
    public int getCount() {
        return empresas.size();
    }

    @Override
    public Object getItem(int i) {
        return empresas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return empresas.get(i).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Empresa empresa = empresas.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;

        if(view == null){
            view = inflater.inflate(R.layout.item_empresa,parent,false);
        }

        TextView nomeEmpresa = (TextView) view.findViewById(R.id.nomeEmpresa);
        nomeEmpresa.setText(empresa.getDescricao());

        TextView enderecoEmpresa = (TextView) view.findViewById(R.id.enderecoEmpresa);
        enderecoEmpresa.setText(empresa.getRua()+", "+empresa.getNumero()+", "+empresa.getCidade());

        TextView telefoneEmpresa = (TextView) view.findViewById(R.id.telefoneEmpresa);
        telefoneEmpresa.setText(empresa.getTelefone());

        Bitmap bmp = BitmapFactory.decodeByteArray(empresa.getImagem(), 0, empresa.getImagem().length);
        ImageView imagenEmpresa = (ImageView) view.findViewById(R.id.imagemEmpresa);
        imagenEmpresa.setImageBitmap(bmp);

        return view;
    }
}
