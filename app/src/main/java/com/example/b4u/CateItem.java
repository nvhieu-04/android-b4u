package com.example.b4u;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.b4u.adapter.CareProductAdapter;
import com.example.b4u.adapter.CategoryAdapter;
import com.example.b4u.adapter.ProductAdapter;
import com.example.b4u.model.CareProduct;
import com.example.b4u.model.Category;
import com.example.b4u.model.Product;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;
import java.util.List;


public class CateItem extends AppCompatActivity{
    RecyclerView productView;
    ProductAdapter productAdapter;
    List<Product> productList_makeup,productList_lip,productList_perfume, productList_skin, productList_hair, productList_sun, productList_eye, productList_tool, productList_nails  ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cate_item);
        Intent intent = this.getIntent();
        productView = findViewById(R.id.recyclerView3);
        int position = intent.getIntExtra("position", 0);


        //adding product for makeup
        productList_makeup = new ArrayList<>();
        //productList.add(new Product("Love for sale shadow palette","Thương Hiệu","Gíá BÁN","Mô Tả","Số lượng",R.drawable.product_1,R.drawable.product_1));
        productList_makeup.add(new Product("Four-way shadow palette","Haus Laboratories","579111","Bring your desire out of the shadows with a new kind of FOUR-WAY. Each quad was curated to unlock allure with a range of 4 blendable finishes + colors reminiscent of the escapes they're named for. Take each shadow for a spin solo, or finish strong by fornicating together. Whatever your fantasy, play freely.","4.5/5 (2103 đánh giá)",R.drawable.product_2,R.drawable.product_2));
        productList_makeup.add(new Product("La luce lip glaze","Haus Laboratories","627370","Transform your pout with a high shine, multi-dimensional glaze topper that plumps & moisturizes.","4/5 (6921 đánh giá)",R.drawable.product_3,R.drawable.product_3));
        productList_makeup.add(new Product("Glam attack metallic crème","Haus Laboratories","627370","Transform your pout with a high shine, multi-dimensional glaze topper that plumps & moisturizes.","4.8/5 (7215 đánh giá)",R.drawable.product_4,R.drawable.product_4));
        productList_makeup.add(new Product("PhD hybrid lip oil","Haus Laboratories","450000","From our labs to your lips: PhD HYBRID LIP OIL is a transformative, ultra-hydrating sheer lip oil that melts down into a true-to-you lip tint, powered by your own pH + plant-based ingredients. Named a 2021 PopSugar Pick.","4.9/5 (1028 đánh giá)",R.drawable.product_5,R.drawable.product_5));
        productList_makeup.add(new Product("LOVE FOR SALE SHADOW PALETTE","Haus Laboratories","1103160","The most anticipated palette of the season is here. HAUS LABS proudly presents LOVE FOR SALE — a new + limited edition shadow palette featuring 18 high performance, soul-stirring shades in a range of velvety mattes, lustrous shimmers, multidimensional metallics and an all-over sparkle topper – inviting you to get back to glam with our most innovative formulas yet.","4.6/5 (2201 đánh giá)",R.drawable.product_1,R.drawable.product_1));
        productList_makeup.add(new Product("LE RIOT LIP GLOSS-Plaze","Haus Laboratories","413685","This extreme high-shine gel gloss amplifies any look. Comfortable enough to not weigh you down, reflective enough to celebrate yourself with a shimmer, sparkle or true shine finish.","4.4/5 (4136 đánh giá)",R.drawable.product_6,R.drawable.product_6));
        productList_makeup.add(new Product("RIP LIP LINER-Slayer","Haus Laboratories","386000","This creamy, high-pigment lip pencil balances precision with one-stroke, demi-matte payoff to slay your lip look every time. The comfortable, longwearing formula was made to outline with ease or fill in for all over color.","4.1/5 (2003 đánh giá)",R.drawable.product_14,R.drawable.product_14));
        productList_makeup.add(new Product("Butter Gloss, Non-Sticky Lip Gloss - Tiramisu","NYX","114223","Butter Gloss: Buttery soft and silky smooth, our decadent Butter Gloss is available in a wide variety of sumptuous shades; Each glossy color delivers sheer to medium coverage that melts onto your lips","4.4/5 (5103 đánh giá)",R.drawable.product_15,R.drawable.product_15));
        productList_makeup.add(new Product("Original Volume Building Mascara, Carbon Black","L’Oreal Paris","312500","Voluminous Mascara is uniquely formulated to resist clumping, soften and build lashes up to 5X their natural thickness; The Volume Maximizing Brush thickens lashes evenly and smoothly; Suitable for sensitive eyes and contact lens wearers","4.9 (3000 đánh giá)",R.drawable.product_16,R.drawable.product_16));
        //productList_makeup.add(new Product("WALNUT FACE SCRUB","Kylie Skin","505615","Gently exfoliates, helps remove dead skin cells and refine skins texture. Walnut Powder and a Botanical Blend of Fruit Extracts: Help to gently smooth skin. Squalane and Sodium Hyaluronate: Moisturizing agents that help reinforce the skin’s natural moisture.","4/5 (2103 đánh giá)",R.drawable.product_11,R.drawable.product_11));
        productList_makeup.add(new Product("Lash Blast Volume Mascara, Brown","Cover Girl","142491","Designed to max out every lash, Lash Blast Volume Mascara creates ten times more volume instantly. Instant blast of fullness and length.","4/5 (4506 đánh giá)",R.drawable.product_19,R.drawable.product_19));
        productList_makeup.add(new Product("TruBlend Matte Made Liquid Foundation, Sun Beige","Cover Girl","144789","Comfortable matte foundation gives you that flawless-looking finish. Mattifying powders absorb excess oil and minimize the appearance of your pores for a smooth, polished look.","4.5/5 (2901 đánh giá)",R.drawable.product_20,R.drawable.product_20));
        productList_makeup.add(new Product("Rimmel Stay Matte Foundation Soft Beige","Rimmel Store","54238","Stay matte, stop shine: This liquid mousse blends flawlessly to even out skin tone and stop shine for hours; With a natural looking yet photo ready finish, your face will look practically poreless and baby soft all day long; Prepare for matte perfection.","4.7/5 (4403 đánh giá)",R.drawable.product_21,R.drawable.product_21));
        productList_makeup.add(new Product("Photo Focus Foundation, Soft Beige","wet n wild","62512","Our high-performing, skin-perfecting foundation is formulated with a matte, light-diffusing complex to help prevent white cast in photos & deliver a radiant-looking complexion.","4/5 (4826 đánh giá)",R.drawable.product_22,R.drawable.product_22));

        //adding product for lip
        productList_lip = new ArrayList<>();
        productList_lip.add(new Product("La luce lip glaze","Haus Laboratories","627370","Transform your pout with a high shine, multi-dimensional glaze topper that plumps & moisturizes.","4/5 (6921 đánh giá)",R.drawable.product_3,R.drawable.product_3));
        productList_lip.add(new Product("Glam attack metallic crème","Haus Laboratories","627370","Transform your pout with a high shine, multi-dimensional glaze topper that plumps & moisturizes.","4.8/5 (7215 đánh giá)",R.drawable.product_4,R.drawable.product_4));
        productList_lip.add(new Product("PhD hybrid lip oil","Haus Laboratories","450000","From our labs to your lips: PhD HYBRID LIP OIL is a transformative, ultra-hydrating sheer lip oil that melts down into a true-to-you lip tint, powered by your own pH + plant-based ingredients. Named a 2021 PopSugar Pick.","4.9/5 (1028 đánh giá)",R.drawable.product_5,R.drawable.product_5));
        productList_lip.add(new Product("LE RIOT LIP GLOSS-Plaze","Haus Laboratories","413685","This extreme high-shine gel gloss amplifies any look. Comfortable enough to not weigh you down, reflective enough to celebrate yourself with a shimmer, sparkle or true shine finish.","4.4/5 (4136 đánh giá)",R.drawable.product_6,R.drawable.product_6));
        productList_lip.add(new Product("RIP LIP LINER-Slayer","Haus Laboratories","386000","This creamy, high-pigment lip pencil balances precision with one-stroke, demi-matte payoff to slay your lip look every time. The comfortable, longwearing formula was made to outline with ease or fill in for all over color.","4.1/5 (2003 đánh giá)",R.drawable.product_14,R.drawable.product_14));
        productList_lip.add(new Product("Butter Gloss, Non-Sticky Lip Gloss - Tiramisu","NYX","114223","Butter Gloss: Buttery soft and silky smooth, our decadent Butter Gloss is available in a wide variety of sumptuous shades; Each glossy color delivers sheer to medium coverage that melts onto your lips","4.4/5 (5103 đánh giá)",R.drawable.product_15,R.drawable.product_15));

        //adding product for perfume
        productList_perfume = new ArrayList<>();
        //adding product for skin
        productList_skin = new ArrayList<>();
        productList_skin.add(new Product("WALNUT FACE SCRUB","Kylie Skin","505615","Gently exfoliates, helps remove dead skin cells and refine skins texture. Walnut Powder and a Botanical Blend of Fruit Extracts: Help to gently smooth skin. Squalane and Sodium Hyaluronate: Moisturizing agents that help reinforce the skin’s natural moisture.","4/5 (2103 đánh giá)",R.drawable.product_11,R.drawable.product_11));

        //adding product for hair
        productList_hair= new ArrayList<>();

        //adding product for sun
        productList_sun= new ArrayList<>();

        //adding product for eye
        productList_eye= new ArrayList<>();
        productList_eye.add(new Product("Original Volume Building Mascara, Carbon Black","L’Oreal Paris","312500","Voluminous Mascara is uniquely formulated to resist clumping, soften and build lashes up to 5X their natural thickness; The Volume Maximizing Brush thickens lashes evenly and smoothly; Suitable for sensitive eyes and contact lens wearers","4.9 (3000 đánh giá)",R.drawable.product_16,R.drawable.product_16));
        productList_eye.add(new Product("Lash Blast Volume Mascara, Brown","Cover Girl","142491","Designed to max out every lash, Lash Blast Volume Mascara creates ten times more volume instantly. Instant blast of fullness and length.","4/5 (4506 đánh giá)",R.drawable.product_19,R.drawable.product_19));


        //adding product for tool
        productList_tool= new ArrayList<>();

        //adding product for nail
        productList_nails= new ArrayList<>();


        switch(position){
            case 0 : setproductRecycler(productList_makeup);
                break;
            case 1 : setproductRecycler(productList_lip);
                break;
            case 2 : setproductRecycler(productList_perfume);
                break;
            case 3 : setproductRecycler(productList_skin);
                break;
            case 4 : setproductRecycler(productList_hair);
                break;
            case 5: setproductRecycler(productList_sun);
                break;
            case 6 : setproductRecycler(productList_eye);
                break;
            case 7 : setproductRecycler(productList_tool);
                break;
            case 8 : setproductRecycler(productList_nails);
                break;
        }
    }

    private void setproductRecycler(List<Product> productsDataList) {
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        productView.setLayoutManager(layoutManager);
        productAdapter = new ProductAdapter(this.getApplicationContext(),productsDataList);
        productView.setAdapter(productAdapter);
    }


}