package com.example.b4u;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.b4u.adapter.CartAdapter;
import com.example.b4u.adapter.PurchasedAdapter;
import com.example.b4u.model.Cart;
import com.example.b4u.model.PurchasedProduct;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FourthFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FourthFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FourthFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FourthFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FourthFragment newInstance(String param1, String param2) {
        FourthFragment fragment = new FourthFragment();
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
    RecyclerView recyclerView;
    CartAdapter cartAdapter;
    List<Cart> cartList;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    DatabaseReference reference;
    String userID,nameAllProduct;
    int quantityadd,quantityminus,pricreAlLProduct,totalPriceProduct;;
    Button purchasedBtn;
    TextView deliveryPrice,totalPrice,productPrice;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fourth, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();
        recyclerView = v.findViewById(R.id.recyclerviewCart);
        productPrice = v.findViewById(R.id.totalFeeTxt);
        totalPrice = v.findViewById(R.id.totalTxt);
        deliveryPrice = v.findViewById(R.id.deliveryTxt);
        purchasedBtn = v.findViewById(R.id.btnPurchase);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayout);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        cartList = new ArrayList<>();
        cartAdapter = new CartAdapter(cartList, this.getContext(), new CartAdapter.ClickListener() {

            @Override
            public void onClickAddItems(Cart cart) {
              quantityadd =  Integer.parseInt(cart.getQuantityProduct());
              int price =  Integer.parseInt(cart.getPriceProduct());
              quantityadd++;
              int total = (quantityadd * price);
                HashMap<String,Object> data = new HashMap<>();
                data.put("QuantityProduct",""+quantityadd);
                data.put("TotalPrice",""+total);
                data.put("NameProduct",cart.getNameProduct());
                data.put("PriceProduct",cart.getPriceProduct());
                reference.child(cart.getNameProduct()).setValue(data);

            }

            @Override
            public void onClickMinusItems(Cart cart) {
                quantityminus =  Integer.parseInt(cart.getQuantityProduct());
                int price =  Integer.parseInt(cart.getPriceProduct());
                if(quantityminus > 1) {
                    quantityminus--;
                    int total = (quantityminus * price);
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("QuantityProduct", "" + quantityminus);
                    data.put("TotalPrice", "" + total);
                    data.put("NameProduct",cart.getNameProduct());
                    data.put("PriceProduct",cart.getPriceProduct());
                    reference.child(cart.getNameProduct()).setValue(data);
                }
            }

            @Override
            public void onClickDeleteItems(Cart cart) {
                delteteproduct(cart);
            }
        });
        recyclerView.setAdapter(cartAdapter);
        getlistProduct();
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    cartAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Cart cart = snapshot.getValue(Cart.class);
                cartList.clear();
                if(cart == null || cartList == null || cartList.isEmpty())
                {
                    return;
                }
                for (int i = 0 ; i < cartList.size() ; i++)
                {
                    if(cart.getNameProduct() == cartList.get(i).getNameProduct()) {
                        cartList.set(i,cart);
                        break;
                    }
                }
                cartAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Cart cart = snapshot.getValue(Cart.class);
                if(cart == null || cartList == null || cartList.isEmpty())
                {
                    return;
                }
                for (int i = 0 ; i < cartList.size() ; i++)
                {
                    if(cart.getNameProduct() == cartList.get(i).getNameProduct()) {
                        cartList.remove(cartList.get(i));
                        cartList.clear();
                        break;
                    }
                }

                cartAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        purchasedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cartList.isEmpty())
                {
                    return;
                }
                Intent intent = new Intent(v.getContext(),Purchased.class);
                intent.putExtra("name",nameAllProduct);
                intent.putExtra("price",""+pricreAlLProduct);
//                intent.putExtra("image","");
                intent.putExtra("quantity",1);
                startActivity(intent);
            }
        });

        return v;

    }
    private void delteteproduct(Cart cart)
    {
        new AlertDialog.Builder(this.getContext())
                .setTitle("X??a")
                .setMessage("B???n c?? mu???n x??a s???n ph???m n??y kh??ng?")
                .setPositiveButton("?????ng ??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        reference = database.getReference("users").child(firebaseAuth.getUid()).child("Cart");
                        reference.child(cart.getNameProduct()).removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                Toast.makeText(getActivity(),"???? x??a th??nh c??ng s???n ph???m",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .setNegativeButton("Quay L???i",null)
                .show();
    }

    private void getlistProduct(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference  = database.getReference("users").child(firebaseAuth.getUid()).child("Cart");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot :snapshot.getChildren()){
                    Cart cart = dataSnapshot.getValue(Cart.class);
                    cartList.add(cart);
                }
                pricreAlLProduct = 0;
                nameAllProduct = "";
                for (int i = 0 ; i < cartList.size() ; i++)
                {
                    pricreAlLProduct += Integer.parseInt(cartList.get(i).getTotalPrice());
                    nameAllProduct += cartList.get(i).getNameProduct()+" x "+cartList.get(i).getQuantityProduct() +" / ";
                }
                deliveryPrice.setText(" ");
                productPrice.setText(String.valueOf(pricreAlLProduct)+" VN??");
                if(pricreAlLProduct > 400000)
                {
                    deliveryPrice.setText("Mi???n Ph?? Giao H??ng");
                    totalPriceProduct = pricreAlLProduct;
                    totalPrice.setText(String.valueOf(pricreAlLProduct)+" VN??");
                }
                else {
                    if(cartList.isEmpty())
                    {
                        deliveryPrice.setText(" ");
                        totalPrice.setText("0");
                    }
                    else
                    {
                        deliveryPrice.setText("35000 VN??");
                        totalPriceProduct = pricreAlLProduct + 35000;
                        totalPrice.setText(String.valueOf(totalPriceProduct)+" VN??");
                    }
                }
                cartAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"Th???t b???i",Toast.LENGTH_SHORT).show();
            }
        });

    }
    ImageView home_btn,user_btn;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle  savedInstanceState)
    {
        super.onViewCreated(view,savedInstanceState);
        NavController navController = Navigation.findNavController(view);
        ImageView home_btn = view.findViewById(R.id.btnHomeF);
        ImageView user_btn = view.findViewById(R.id.btnUserf);
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_fourthFragment_to_firstFragment);
            }
        });
        user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_fourthFragment_to_thirdFragment);
            }
        });

    }

}