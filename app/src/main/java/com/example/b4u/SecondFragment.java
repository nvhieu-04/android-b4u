package com.example.b4u;

import android.content.Intent;
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
import android.widget.ImageView;

import com.example.b4u.adapter.AllCategoryAdapter;
import com.example.b4u.adapter.CategoryAdapter;
import com.example.b4u.model.AllCategory;
import com.example.b4u.model.Category;

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
        allcategoryList.add(new AllCategory(1,R.drawable.category_1));
        allcategoryList.add(new AllCategory(2,R.drawable.category_2));
        allcategoryList.add(new AllCategory(3,R.drawable.category_3));
        allcategoryList.add(new AllCategory(4,R.drawable.category_4));
        allcategoryList.add(new AllCategory(5,R.drawable.category_5));
        allcategoryList.add(new AllCategory(6,R.drawable.category_6));
        setallcategoryRecycler(allcategoryList);
        // Inflate the layout for this fragment
        return v;



    }
    private void setallcategoryRecycler(List<AllCategory> allcategoryDataList) {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this.getContext(),3);
        allCategoryRecycler.setLayoutManager(layoutManager);
        allCategoryAdapter = new AllCategoryAdapter(this.getContext(), allcategoryDataList);
        allCategoryRecycler.setAdapter(allCategoryAdapter);
    }

}