package com.example.b4u;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.b4u.adapter.AllCategoryAdapter;
import com.example.b4u.adapter.CareProductAdapter;
import com.example.b4u.model.AllCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SecondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    CareProductAdapter careProductAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
    RecyclerView allCategoryRecycler;
    AllCategoryAdapter allCategoryAdapter;
    List<AllCategory>  allcategoryList;
    ImageView back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_second, container, false);
        allCategoryRecycler = v.findViewById(R.id.allCategoryView);
//        back = v.findViewById(R.id.back);

        //adding category
        allcategoryList = new ArrayList<>();
        allcategoryList.add(new AllCategory(1,R.drawable.category_1,"Makeup"));
        allcategoryList.add(new AllCategory(2,R.drawable.category_2,"Môi"));
        allcategoryList.add(new AllCategory(3,R.drawable.category_3,"Nước hoa"));
        allcategoryList.add(new AllCategory(4,R.drawable.category_4,"Chăm sóc da"));
        allcategoryList.add(new AllCategory(5,R.drawable.category_5,"Chăm sóc tóc"));
        allcategoryList.add(new AllCategory(6,R.drawable.category_6,"Chống nắng"));
        allcategoryList.add(new AllCategory(6,R.drawable.category_7,"Mắt"));
        allcategoryList.add(new AllCategory(6,R.drawable.category_8,"Dụng cụ"));
        allcategoryList.add(new AllCategory(6,R.drawable.category_9,"Nails"));
        setallcategoryRecycler(allcategoryList);
        // Inflate the layout for this fragment
        //


        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle  savedInstanceState)
    {
        super.onViewCreated(view,savedInstanceState);
        NavController navController = Navigation.findNavController(view);
        ImageView back_btn = view.findViewById(R.id.back1);
        ImageView cart_btn = view.findViewById(R.id.cart1);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_secondFragment_to_firstFragment);
            }
        });
        cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_secondFragment_to_fourthFragment);
            }
        });

    }
    private void setallcategoryRecycler(List<AllCategory> allcategoryDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        allCategoryRecycler.setLayoutManager(layoutManager);
        allCategoryAdapter = new AllCategoryAdapter(this.getContext(), allcategoryDataList);
        allCategoryRecycler.setAdapter(allCategoryAdapter);
    }


}