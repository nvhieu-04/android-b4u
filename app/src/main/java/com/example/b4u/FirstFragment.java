package com.example.b4u;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.b4u.adapter.CategoryAdapter;
import com.example.b4u.adapter.ProductAdapter;
import com.example.b4u.adapter.ProductCareAdapter;
import com.example.b4u.model.Category;
import com.example.b4u.model.Product;
import com.example.b4u.model.ProductCare;

import java.util.ArrayList;
import java.util.List;

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
    RecyclerView productCareView, categoryView,productView;
    ProductCareAdapter productCareAdapter;
    List<ProductCare>  productCareList;
    CategoryAdapter categoryAdapter;
    List<Category> categoryList;
    ProductAdapter productAdapter;
    List<Product> productList;
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
        //Adding Images to care list view
        productCareList = new ArrayList<>();
        productCareList.add(new ProductCare(1,R.drawable.product_care_1));
        productCareList.add(new ProductCare(2,R.drawable.product_care_2));
        productCareList.add(new ProductCare(3,R.drawable.product_care_3));
        productCareList.add(new ProductCare(4,R.drawable.product_care_4));
        productCareList.add(new ProductCare(5,R.drawable.product_care_5));
        productCareList.add(new ProductCare(6,R.drawable.product_care_6));

        setproductCareRecycler(productCareList);
        //adding image to category
        categoryList = new ArrayList<>();
        categoryList.add(new Category(1,R.drawable.category_1));
        categoryList.add(new Category(2,R.drawable.category_2));
        categoryList.add(new Category(3,R.drawable.category_3));
        categoryList.add(new Category(4,R.drawable.category_4));
        categoryList.add(new Category(5,R.drawable.category_5));
        categoryList.add(new Category(6,R.drawable.category_6));
        setcategoryRecycler(categoryList);

        //adding product
        productList = new ArrayList<>();
        productList.add(new Product("Love for sale shadow palette","Description","Price","Price Before","5/5",R.drawable.product_1));
        productList.add(new Product("Four-way shadow palette","Description","Price","Price Before","5/5",R.drawable.product_2));
        productList.add(new Product("La luce lip glaze","Description","Price","Price","Price Before",R.drawable.product_3));
        productList.add(new Product("Glam attack metallic crème","Description","12$","Price Before","5/5",R.drawable.product_4));
        productList.add(new Product("PhD hybrid lip oil","Description","Price","Price Before","5/5",R.drawable.product_5));
        productList.add(new Product("Le riot lip gloss","Description","Price","Price Before","5/5",R.drawable.product_6));
        setproductRecycler(productList);
        // Inflate the layout for this fragment
        return v;
    }
    private void setproductCareRecycler(List<ProductCare> dataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        productCareView.setLayoutManager(layoutManager);
        productCareAdapter = new ProductCareAdapter(this.getContext(), dataList);
        productCareView.setAdapter(productCareAdapter);
    }
    private void setcategoryRecycler(List<Category> categoryDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        categoryView.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(this.getContext(), categoryDataList);
        categoryView.setAdapter(categoryAdapter);
    }
    private void setproductRecycler(List<Product> productsDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        productView.setLayoutManager(layoutManager);
        productAdapter = new ProductAdapter(this.getContext(), productsDataList);
        productView.setAdapter(productAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        ActionBar supportActionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (supportActionBar != null)
            supportActionBar.hide();
    }

    @Override
    public void onStop() {
        super.onStop();
        ActionBar supportActionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (supportActionBar != null)
            supportActionBar.show();
    }


}
