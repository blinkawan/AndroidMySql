package com.agungsetiawan.androidmysql.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.agungsetiawan.androidmysql.R;
import com.agungsetiawan.androidmysql.domain.Product;

public class ProductAdapter extends ArrayAdapter<Product> {
	
	Context context;
	int layout;
	List<Product> listProduct;

	public ProductAdapter(Context context, int textViewResourceId,
			List<Product> objects) {
		super(context, textViewResourceId, objects);
		this.context=context;
		this.layout=textViewResourceId;
		this.listProduct=objects;
	}
	
	public View getView(int position,View convertView,ViewGroup parent){
		View v=convertView;
		ProductHolder holder;
		
		if(v==null){
			LayoutInflater vi=((Activity)context).getLayoutInflater();
			v=vi.inflate(layout, parent, false);
			
			holder=new ProductHolder();
			holder.textViewName=(TextView) v.findViewById(R.id.txtName);
			v.setTag(holder);
		}else{
			holder=(ProductHolder) v.getTag();
		}
		
		Product product=listProduct.get(position);
		holder.textViewName.setText(product.getName());
		
		return v;
	}
	
	static class ProductHolder{
		TextView textViewName;
	}
	
}
