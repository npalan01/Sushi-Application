package com.niyati.finalpractical.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.niyati.finalpractical.Model.Product;
import com.niyati.finalpractical.ProductManager;
import com.niyati.finalpractical.R;

import java.util.List;


public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProdudctViewHolder> {

    private List<Product> productList;
    SharedPreferences sharedpreferences;
    public Context context;
    public static final String cartPref = "cart_pref";

    public class ProdudctViewHolder extends RecyclerView.ViewHolder {
        public TextView name, desc, price, quantity;
        public Button add, remove;
        public ImageView productImage;


        public ProdudctViewHolder(View view) {
            super(view);
            sharedpreferences = context.getSharedPreferences(cartPref,
                    Context.MODE_PRIVATE);
            name = view.findViewById(R.id.name);
            desc = view.findViewById(R.id.desc);
            price = view.findViewById(R.id.price);
            productImage = view.findViewById(R.id.product_image);
            quantity = view.findViewById(R.id.text_Quantity);
            add = view.findViewById(R.id.btnAdd);
            remove = view.findViewById(R.id.btnRemove);

        }
    }


    public ProductsAdapter(List<Product> productList, Context context) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public ProdudctViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_row, parent, false);

        return new ProdudctViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ProdudctViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.name.setText(product.getName());
        holder.desc.setText(product.getDesc() + "");
        holder.price.setText(product.getPrice() + "$");
        holder.quantity.setText(sharedpreferences.getInt(product.getId()+"",0)+"");
        final SharedPreferences.Editor editor = sharedpreferences.edit();
        holder.productImage.setImageResource(ProductManager.getImageID(product.getImageName()));
        final int productId = product.getId();
        final int[] currentQuantity = {Integer.parseInt(holder.quantity.getText().toString())};
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentQuantity[0]++;
                holder.quantity.setText((currentQuantity[0]) + "");
                editor.putInt(productId+"",currentQuantity[0]);
                editor.commit();
            }
        });

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuantity[0] == 0){
                    editor.putString("product_id_quantity", productId + "-" + 0);
                    return;
                }
                currentQuantity[0]--;
                holder.quantity.setText((currentQuantity[0]) + "");
                editor.putInt(productId+"",currentQuantity[0]);
                editor.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}