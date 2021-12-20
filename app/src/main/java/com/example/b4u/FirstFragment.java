package com.example.b4u;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.b4u.adapter.CareProductAdapter;
import com.example.b4u.adapter.CategoryAdapter;
import com.example.b4u.adapter.ProductAdapter;
import com.example.b4u.model.CareProduct;
import com.example.b4u.model.Category;
import com.example.b4u.model.Product;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    RecyclerView categoryView,productView,productCareView;
    CategoryAdapter categoryAdapter;
    List<Category> categoryList;
    ProductAdapter productAdapter;
    List<Product> productList;
    CareProductAdapter careProductAdapter;
    List<CareProduct> careProductList;
    TextView userName,seeAll;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    String userID;
    EditText searchText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_first, container, false);
        //ProductCare
        productCareView = v.findViewById(R.id.recyclerView1);
        categoryView = v.findViewById(R.id.recyclerView2);
        productView = v.findViewById(R.id.recyclerView3);
        userName = v.findViewById(R.id.textUser);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();
        searchText = v.findViewById(R.id.searchText);

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

        //Get name on Home Screen
        DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
        documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                userName.setText(documentSnapshot.getString("fName"));
            }
        });
        //Adding Images to care list view
        //Vài bữa add i chang line 111->116
        careProductList = new ArrayList<>();
        //careProductList.add(new CareProduct("Love for sale shadow palette","Thương Hiệu","Gíá BÁN","Gía bán trước đây","Bao nhiêu người mua",R.drawable.product_1,R.drawable.product_1));
        careProductList.add(new CareProduct("LOVE FOR SALE SHADOW PALETTE","Haus Laboratories","1103160","The most anticipated palette of the season is here. HAUS LABS proudly presents LOVE FOR SALE — a new + limited edition shadow palette featuring 18 high performance, soul-stirring shades in a range of velvety mattes, lustrous shimmers, multidimensional metallics and an all-over sparkle topper – inviting you to get back to glam with our most innovative formulas yet.","4.8/5 (2103 đánh giá)",R.drawable.product_1,R.drawable.product_1));
        careProductList.add(new CareProduct("LE RIOT LIP GLOSS-Plaze","Haus Laboratories","413685","This extreme high-shine gel gloss amplifies any look. Comfortable enough to not weigh you down, reflective enough to celebrate yourself with a shimmer, sparkle or true shine finish.","4.5/6 (4136 đánh giá)",R.drawable.product_6,R.drawable.product_6));
        careProductList.add(new CareProduct("RIP LIP LINER-Slayer","Haus Laboratories","386000","This creamy, high-pigment lip pencil balances precision with one-stroke, demi-matte payoff to slay your lip look every time. The comfortable, longwearing formula was made to outline with ease or fill in for all over color.","4.8/5 (2003 đánh giá)",R.drawable.product_14,R.drawable.product_14));
        careProductList.add(new CareProduct("Butter Gloss, Non-Sticky Lip Gloss - Tiramisu","NYX","114223","Butter Gloss: Buttery soft and silky smooth, our decadent Butter Gloss is available in a wide variety of sumptuous shades; Each glossy color delivers sheer to medium coverage that melts onto your lips","4.4/5 (5103 đánh giá)",R.drawable.product_15,R.drawable.product_15));
        careProductList.add(new CareProduct("Original Volume Building Mascara, Carbon Black","L’Oreal Paris","312500","Voluminous Mascara is uniquely formulated to resist clumping, soften and build lashes up to 5X their natural thickness; The Volume Maximizing Brush thickens lashes evenly and smoothly; Suitable for sensitive eyes and contact lens wearers","4.7/5 (3000 đánh giá)",R.drawable.product_16,R.drawable.product_16));
        careProductList.add(new CareProduct("WALNUT FACE SCRUB","Kylie Skin","505615","Gently exfoliates, helps remove dead skin cells and refine skins texture. Walnut Powder and a Botanical Blend of Fruit Extracts: Help to gently smooth skin. Squalane and Sodium Hyaluronate: Moisturizing agents that help reinforce the skin’s natural moisture.","4/5 (2113 đánh giá)",R.drawable.product_11,R.drawable.product_11));
        careProductList.add(new CareProduct("Lash Blast Volume Mascara, Brown","Cover Girl","142491","Designed to max out every lash, Lash Blast Volume Mascara creates ten times more volume instantly. Instant blast of fullness and length.","4.4/5 (4506 đánh giá)",R.drawable.product_19,R.drawable.product_19));
        careProductList.add(new CareProduct("TruBlend Matte Made Liquid Foundation, Sun Beige","Cover Girl","144789","Comfortable matte foundation gives you that flawless-looking finish. Mattifying powders absorb excess oil and minimize the appearance of your pores for a smooth, polished look.","4.8/5 (2901 đánh giá)",R.drawable.product_20,R.drawable.product_20));
        careProductList.add(new CareProduct("Rimmel Stay Matte Foundation Soft Beige","Rimmel Store","54238","Stay matte, stop shine: This liquid mousse blends flawlessly to even out skin tone and stop shine for hours; With a natural looking yet photo ready finish, your face will look practically poreless and baby soft all day long; Prepare for matte perfection.","4.2/5 (4403 đánh giá)",R.drawable.product_21,R.drawable.product_21));
        careProductList.add(new CareProduct("Photo Focus Foundation, Soft Beige","wet n wild","62512","Our high-performing, skin-perfecting foundation is formulated with a matte, light-diffusing complex to help prevent white cast in photos & deliver a radiant-looking complexion.","4.8 (4826 đánh giá)",R.drawable.product_22,R.drawable.product_22));
        //
        setcareproductRecycler(careProductList);

        //adding image to category
        categoryList = new ArrayList<>();
        categoryList.add(new Category(1,R.drawable.category_1,"Makeup"));
        categoryList.add(new Category(2,R.drawable.category_2,"Môi"));
        categoryList.add(new Category(3,R.drawable.category_3,"Nước hoa"));
        categoryList.add(new Category(4,R.drawable.category_4,"Chăm sóc da"));
        categoryList.add(new Category(5,R.drawable.category_5,"Chăm sóc tóc"));
        categoryList.add(new Category(6,R.drawable.category_6,"Chống nắng"));
        setcategoryRecycler(categoryList);

        //adding product
        productList = new ArrayList<>();
        //productList.add(new Product("Love for sale shadow palette","Thương Hiệu","Gíá BÁN","Mô Tả","Số lượng",R.drawable.product_1,R.drawable.product_1));
        productList.add(new Product("Four-way shadow palette","Haus Laboratories","579111","Bring your desire out of the shadows with a new kind of FOUR-WAY. Each quad was curated to unlock allure with a range of 4 blendable finishes + colors reminiscent of the escapes they're named for. Take each shadow for a spin solo, or finish strong by fornicating together. Whatever your fantasy, play freely.","4.5/5 (2103 đánh giá)",R.drawable.product_2,R.drawable.product_2));
        productList.add(new Product("La luce lip glaze","Haus Laboratories","627370","Transform your pout with a high shine, multi-dimensional glaze topper that plumps & moisturizes.","4/5 (6921 đánh giá)",R.drawable.product_3,R.drawable.product_3));
        productList.add(new Product("Glam attack metallic crème","Haus Laboratories","627370","Transform your pout with a high shine, multi-dimensional glaze topper that plumps & moisturizes.","4.8/5 (7215 đánh giá)",R.drawable.product_4,R.drawable.product_4));
        productList.add(new Product("PhD hybrid lip oil","Haus Laboratories","450000","From our labs to your lips: PhD HYBRID LIP OIL is a transformative, ultra-hydrating sheer lip oil that melts down into a true-to-you lip tint, powered by your own pH + plant-based ingredients. Named a 2021 PopSugar Pick.","4.9/5 (1028 đánh giá)",R.drawable.product_5,R.drawable.product_5));
        productList.add(new Product("LOVE FOR SALE SHADOW PALETTE","Haus Laboratories","1103160","The most anticipated palette of the season is here. HAUS LABS proudly presents LOVE FOR SALE — a new + limited edition shadow palette featuring 18 high performance, soul-stirring shades in a range of velvety mattes, lustrous shimmers, multidimensional metallics and an all-over sparkle topper – inviting you to get back to glam with our most innovative formulas yet.","4.6/5 (2201 đánh giá)",R.drawable.product_1,R.drawable.product_1));
        productList.add(new Product("LE RIOT LIP GLOSS-Plaze","Haus Laboratories","413685","This extreme high-shine gel gloss amplifies any look. Comfortable enough to not weigh you down, reflective enough to celebrate yourself with a shimmer, sparkle or true shine finish.","4.4/5 (4136 đánh giá)",R.drawable.product_6,R.drawable.product_6));
        productList.add(new Product("RIP LIP LINER-Slayer","Haus Laboratories","386000","This creamy, high-pigment lip pencil balances precision with one-stroke, demi-matte payoff to slay your lip look every time. The comfortable, longwearing formula was made to outline with ease or fill in for all over color.","4.1/5 (2003 đánh giá)",R.drawable.product_14,R.drawable.product_14));
        productList.add(new Product("Butter Gloss, Non-Sticky Lip Gloss - Tiramisu","NYX","114223","Butter Gloss: Buttery soft and silky smooth, our decadent Butter Gloss is available in a wide variety of sumptuous shades; Each glossy color delivers sheer to medium coverage that melts onto your lips","4.4/5 (5103 đánh giá)",R.drawable.product_15,R.drawable.product_15));
        productList.add(new Product("Original Volume Building Mascara, Carbon Black","L’Oreal Paris","312500","Voluminous Mascara is uniquely formulated to resist clumping, soften and build lashes up to 5X their natural thickness; The Volume Maximizing Brush thickens lashes evenly and smoothly; Suitable for sensitive eyes and contact lens wearers","4.9 (3000 đánh giá)",R.drawable.product_16,R.drawable.product_16));
        productList.add(new Product("WALNUT FACE SCRUB","Kylie Skin","505615","Gently exfoliates, helps remove dead skin cells and refine skins texture. Walnut Powder and a Botanical Blend of Fruit Extracts: Help to gently smooth skin. Squalane and Sodium Hyaluronate: Moisturizing agents that help reinforce the skin’s natural moisture.","4/5 (2103 đánh giá)",R.drawable.product_11,R.drawable.product_11));
        productList.add(new Product("Lash Blast Volume Mascara, Brown","Cover Girl","142491","Designed to max out every lash, Lash Blast Volume Mascara creates ten times more volume instantly. Instant blast of fullness and length.","4/5 (4506 đánh giá)",R.drawable.product_19,R.drawable.product_19));
        productList.add(new Product("TruBlend Matte Made Liquid Foundation, Sun Beige","Cover Girl","144789","Comfortable matte foundation gives you that flawless-looking finish. Mattifying powders absorb excess oil and minimize the appearance of your pores for a smooth, polished look.","4.5/5 (2901 đánh giá)",R.drawable.product_20,R.drawable.product_20));
        productList.add(new Product("Rimmel Stay Matte Foundation Soft Beige","Rimmel Store","54238","Stay matte, stop shine: This liquid mousse blends flawlessly to even out skin tone and stop shine for hours; With a natural looking yet photo ready finish, your face will look practically poreless and baby soft all day long; Prepare for matte perfection.","4.7/5 (4403 đánh giá)",R.drawable.product_21,R.drawable.product_21));
        productList.add(new Product("Photo Focus Foundation, Soft Beige","wet n wild","62512","Our high-performing, skin-perfecting foundation is formulated with a matte, light-diffusing complex to help prevent white cast in photos & deliver a radiant-looking complexion.","4/5 (4826 đánh giá)",R.drawable.product_22,R.drawable.product_22));
        setproductRecycler(productList);




        // Inflate the layout for this fragment
        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle  savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(view);
        ImageView user = view.findViewById(R.id.imageUser);
        seeAll = view.findViewById(R.id.textView33);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_firstFragment_to_thirdFragment);
            }
        });
        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_firstFragment_to_secondFragment);
            }
        });

    }
    private void filter(String text){
        ArrayList<Product> filterlist = new ArrayList<>();
        for(Product item: productList)
        {
            if(item.getName().toLowerCase().contains(text.toLowerCase())){
                filterlist.add(item);
        }
           // ProductAdapter.filterList(filterlist);
        }
    }
    private void setcareproductRecycler(List<CareProduct> productcareDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        productCareView.setLayoutManager(layoutManager);
        careProductAdapter = new CareProductAdapter(this.getContext(), productcareDataList);
        productCareView.setAdapter(careProductAdapter);
    }
    private void setcategoryRecycler(List<Category> categoryDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        categoryView.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(this.getContext(), categoryDataList);
        categoryView.setAdapter(categoryAdapter);
    }
    private void setproductRecycler(List<Product> productsDataList) {
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        productView.setLayoutManager(layoutManager);
        productAdapter = new ProductAdapter(this.getContext(), productsDataList);
        productView.setAdapter(productAdapter);
    }




}
