package com.agungsetiawan.androidmysql;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.agungsetiawan.androidmysql.adapter.ProductAdapter;
import com.agungsetiawan.androidmysql.domain.Product;
import com.agungsetiawan.androidmysql.http.CustomHttpClient;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class AllProducts extends Activity implements OnItemClickListener {
	
	ListView listViewProduct;
	CustomHttpClient client;
	List<Product> listProduct;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.all_products);
		
		listViewProduct=(ListView) findViewById(R.id.listViewProduct);
		client=new CustomHttpClient();
		Gson gson=new Gson();
		
		try {
			String json=client.get("http://192.168.43.35/androiddataserver/");
			listProduct=gson.fromJson(json,
						new TypeToken<List<Product>>(){}.getType());
			
			listViewProduct.setAdapter(new ProductAdapter(this, R.layout.list_item_product, listProduct));
			listViewProduct.setOnItemClickListener(this);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.all_products, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
		Product product=listProduct.get(position);
		Intent intent=new Intent(this,ProductDetail.class);
		intent.putExtra("ID", product.getId());
		startActivity(intent);
	}

}
