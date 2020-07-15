package com.niyati.finalpractical;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.niyati.finalpractical.Model.Product;
import com.niyati.finalpractical.adapter.CheckoutAdapter;
import com.niyati.finalpractical.adapter.ProductsAdapter;

import java.util.ArrayList;
import java.util.List;

public class CheckOutActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    Double totalBill = 0d;
    private List<Product> productList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CheckoutAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        sharedpreferences = getSharedPreferences(ProductsAdapter.cartPref,
                Context.MODE_PRIVATE);

        List<Product> allProducts = ProductManager.getAll();

        for (int i = 0; i < allProducts.size(); i++) {
            int quantity = sharedpreferences.getInt(allProducts.get(i).getId() + "", 0);
            totalBill = totalBill + quantity * allProducts.get(i).getPrice();
            if (sharedpreferences.getInt(allProducts.get(i).getId() + "", 0) > 0) {
                productList.add(allProducts.get(i));
            }
        }

        recyclerView = findViewById(R.id.checkout_recycler_view);

        mAdapter = new CheckoutAdapter(productList, CheckOutActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        // adding inbuilt divider line
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        TextView final_checkout_price_value = findViewById(R.id.final_checkout_price_value);
        final_checkout_price_value.setText(String.format("$: %.2f", totalBill));
        Button btnPurchase = findViewById(R.id.btnPurchase);
        btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert(CheckOutActivity.this);
            }
        });
    }

    private void showAlert(Context context) {
        final SharedPreferences.Editor editor = sharedpreferences.edit();
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Confirm Purchase");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Accept",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(CheckOutActivity.this, MainActivity.class);
                        editor.clear();
                        editor.commit();
                        CheckOutActivity.this.startActivity(intent);
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Modify",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(CheckOutActivity.this, MenuActivity.class);
                        CheckOutActivity.this.startActivity(intent);
                    }
                });

        alertDialog.show();
        Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setTextColor(Color.parseColor("#03DAC5"));

        Button negativeButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        negativeButton.setTextColor(Color.parseColor("#03DAC5"));

        Button neutralButton = alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL);
        neutralButton.setTextColor(Color.parseColor("#03DAC5"));
    }
}
