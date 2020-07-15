package com.niyati.finalpractical.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.niyati.finalpractical.Model.Product;
import com.niyati.finalpractical.ProductManager;
import com.niyati.finalpractical.R;

import java.util.List;



public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.CheckoutViewHolder> {

    private List<Product> productList;
    SharedPreferences sharedpreferences;
    public Context context;
    public static final String cartPref = "cart_pref";

    public class CheckoutViewHolder extends RecyclerView.ViewHolder {
        public TextView name, desc, price, quantity, checkoutItemPrice;
        public ImageView productImage;


        public CheckoutViewHolder(View view) {
            super(view);
            sharedpreferences = context.getSharedPreferences(cartPref,
                    Context.MODE_PRIVATE);
            name = view.findViewById(R.id.name);
            desc = view.findViewById(R.id.desc);
            price = view.findViewById(R.id.price);
            productImage = view.findViewById(R.id.product_image);
            quantity = view.findViewById(R.id.checkout_qnty_value);
            checkoutItemPrice = view.findViewById(R.id.checkout_item_price);
        }
    }


    public CheckoutAdapter(List<Product> productList, Context context) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public CheckoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.checkout_list_row, parent, false);

        return new CheckoutViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CheckoutViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.name.setText(product.getName());
        holder.desc.setText(product.getDesc() + "");
        holder.price.setText(product.getPrice() + "$");
        holder.quantity.setText(sharedpreferences.getInt(product.getId() + "", 0) + "");
        double totalPrice = (sharedpreferences.getInt(product.getId() + "", 0) * product.getPrice());
        String total = String.format("$: %.2f", totalPrice);
        holder.checkoutItemPrice.setText(total);
        holder.productImage.setImageResource(ProductManager.getImageID(product.getImageName()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}