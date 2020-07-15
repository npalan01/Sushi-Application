package com.niyati.finalpractical;

import com.niyati.finalpractical.Model.Product;

import java.util.ArrayList;

public class ProductManager {
    private static ArrayList<Product> products;

    private static void initData() {
        products = new ArrayList<>();

        products.add(new Product(1, "Kunsei Sake", "Smoked Salmon, rice", 3.95, "i01"));
        products.add(new Product(2, "Sake", "Fresh Salmon, rice", 2.95, "i02"));
        products.add(new Product(3, "Tai", "Tilapia, rice", 2.95, "i03"));
        products.add(new Product(4, "Elbi", "Shrimp, rice", 3.95, "i04"));
        products.add(new Product(5, "Maguro", "Tuna, rice", 4.5, "i05"));
        products.add(new Product(6, "Unagi", "Grilled eel, rice, sesame", 4.5, "i06"));
        products.add(new Product(7, "Kani Kama", "Pollock, rice", 3.95, "i07"));
        products.add(new Product(8, "Tamago", "Omelet, rice", 2.95, "i08"));
        products.add(new Product(9, "Kunsei Sale Tempura", "Smoked salmon, tempura, naniyori sauce", 4.25, "i09"));
        products.add(new Product(10, "Hotategai", "Scallops, tempura, naniyori sauce", 4.25, "i10"));
        products.add(new Product(11, "Tobiko", "Flying fish caviar", 4.25, "i11"));
        products.add(new Product(12, "Masago", "Caplin fish caviar", 2.95, "i12"));
        products.add(new Product(13, "Sake Tempura", "Fresh salmon, tempura, caviar, naniyori sauce", 4.5, "i13"));
        products.add(new Product(14, "Maguro Tempura", "Tuna, tempura, caviar, naniyori sauce", 4.55, "i14"));
        products.add(new Product(15, "Crab Tempura", "Crab, tempura, caviar, naniyori sauce", 4.55, "i15"));
        products.add(new Product(16, "Shrimp Tempura", "Shrimp, tempura, caviar, naniyori sauce", 4.50, "i16"));
        products.add(new Product(17, "Sakura", "Scallop, cucumber, caviar, naniyori sauce", 4.25, "i17"));
        products.add(new Product(18, "Furai", "Fried shrimp", 4.25, "i18"));
        products.add(new Product(19, "Kappa Makis", "Cucumber, sesame", 2.10, "i19"));
        products.add(new Product(20, "Avocado Caviar", "Avocado, sesame, red caviar", 2.35, "i20"));
        products.add(new Product(21, "Oshinko", "Pickled radish, sesame, sauce", 2.25, "i21"));
        products.add(new Product(22, "Sake Makis", "Fresh salmon, shallots, sesame", 4.05, "i22"));
        products.add(new Product(23, "Sake Makis spice", "Fresh salmon, shallots, sesame, naniyori sauce", 4.05, "i23"));
        products.add(new Product(24, "Tekka Makis", "Tuna, shallots, sesame", 4.65, "i24"));
        products.add(new Product(25, "Tekka Makis spice", "Tuna, shallots, sesame, naniyori sauce", 4.60, "i25"));
        products.add(new Product(26, "Una Kuy", "Eel, cucumber, sesame", 4.35, "i26"));
        products.add(new Product(27, "Tai Makis", "Tilapia, shallots, sesame", 4.05, "i27"));
        products.add(new Product(28, "Smoked salmon", "Smoked salmon, avocado, cream cheese, sesame", 6.05, "i28"));
        products.add(new Product(29, "Shusi Naniori", "Tuna, avocado, masago, shallots, tempura, naniyori sauce", 6.35, "i29"));
        products.add(new Product(30, "Shushi Shrimp", "Shrimp, avocado, tempura, cucumber, caviar, sesame, naniyori sauce", 6.05, "i30"));
    }


    //Given the name, it will give you back a resource ID of the image.
    //-1 if wrong. Normally, I would throw an exception but for the level we are in, we are just going to badly assume -1 is not going to be used.
    public static int getImageID(String imageName) {
        try {
            return R.drawable.class.getField(imageName).getInt(null);
        } catch (NoSuchFieldException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
        return -1;
    }

    public static ArrayList<Product> getAll() {
        if (products == null)
            initData();
        return products;
    }

    public static Product getById(int id) {
        if (products == null)
            initData();
        return products.get(id - 1);
    }
}
