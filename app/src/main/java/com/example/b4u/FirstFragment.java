package com.example.b4u;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    TextView userName;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    String userID;
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
        careProductList.add(new CareProduct("Love for sale shadow palette","Thương Hiệu","Gíá BÁN","Gía bán trước đây","Bao nhiêu người mua",R.drawable.product_1,R.drawable.product_1));

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
        productList.add(new Product("Love for sale shadow palette","Thương Hiệu","Gíá BÁN","Mô Tả","Số lượng",R.drawable.product_1,R.drawable.product_1));
        productList.add(new Product("Four-way shadow palette","Description","Price","Price Before","5/5",R.drawable.product_2,R.drawable.product_2));
        productList.add(new Product("La luce lip glaze","Description","Price","Price Before","Price Before",R.drawable.product_3,R.drawable.product_3));
        productList.add(new Product("Glam attack metallic crème","Description","Price","Price Before","5/5",R.drawable.product_4,R.drawable.product_4));
        productList.add(new Product("PhD hybrid lip oil","Description","Price","Price Before","5/5",R.drawable.product_5,R.drawable.product_5));
        productList.add(new Product("Le riot lip gloss","Description","Price","Price Before","5/5",R.drawable.product_6,R.drawable.product_6));
        setproductRecycler(productList);

        
        ImageView user = v.findViewById(R.id.imageUser);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThirdFragment nextFrag= new ThirdFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(((ViewGroup)getView().getParent()).getId(), nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });



        // Inflate the layout for this fragment
        return v;
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
