package com.agungsetiawan.androidmysql;

import java.io.IOException;

import com.agungsetiawan.androidmysql.domain.Product;
import com.agungsetiawan.androidmysql.http.CustomHttpClient;
import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.EditText;

public class ProductDetail extends Activity {
	
	EditText editTextId;
	EditText editTextName;
	EditText editTextPrice;
	EditText editTextDescription;
	int id;
	CustomHttpClient client;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_detail);
		
		editTextId=(EditText) findViewById(R.id.editTextId);
		editTextName=(EditText) findViewById(R.id.editTextName);
		editTextPrice=(EditText) findViewById(R.id.editTextPrice);
		editTextDescription=(EditText) findViewById(R.id.editTextDescription);
		
		Intent intent=getIntent();
		id=intent.getIntExtra("ID", 1);
		
		client=new CustomHttpClient();
		Gson gson=new Gson();
		
		try {
			String json=client.get("http://192.168.43.35/androiddataserver/detail.php?id="+id);
			Product product=gson.fromJson(json, Product.class);
			
			editTextId.setText(String.valueOf(product.getId()));
			editTextName.setText(product.getName());
			editTextPrice.setText(String.valueOf(product.getPrice()));
			editTextDescription.setText(product.getDescription());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.product_detail, menu);
		return true;
	}

}
