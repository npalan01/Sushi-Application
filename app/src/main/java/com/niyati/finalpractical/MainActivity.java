package com.niyati.finalpractical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.niyati.finalpractical.Model.Product;
import com.niyati.finalpractical.adapter.ProductsAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedpreferences = getSharedPreferences(ProductsAdapter.cartPref,
                Context.MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        List<Product> allProducts = ProductManager.getAll();
        boolean isCurrentlyPurchasing = false;

        for (int i = 0; i < allProducts.size(); i++) {
            if (sharedpreferences.getInt(allProducts.get(i).getId() + "", 0) > 0) {
                isCurrentlyPurchasing = true;
                break;
            }
        }
        if (isCurrentlyPurchasing) {
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            MainActivity.this.startActivity(intent);
        }

        findViewById(R.id.main_logo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}
