package com.example.b4u;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThirdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThirdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThirdFragment newInstance(String param1, String param2) {
        ThirdFragment fragment = new ThirdFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    Button btn_SignOut,btn_ChangeProfile,btn_Purchased, btn_Delivery, btn_About;
    TextView fUser,fMail,fPhone,fBirth;
    String userID;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
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
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_third, container, false);
       btn_SignOut = v.findViewById(R.id.btnSignOut);
       btn_ChangeProfile = v.findViewById(R.id.btnChangeProfile);
       btn_Purchased = v.findViewById(R.id.btnPurchased);
       btn_Delivery = v.findViewById(R.id.btnDelivery);
       btn_About = v.findViewById(R.id.btnAbout);
       fUser = v.findViewById(R.id.textNameUser);
       fMail = v.findViewById(R.id.textMail);
       fPhone = v.findViewById(R.id.textPhone);
       fBirth = v.findViewById(R.id.textBirthday);
       firebaseAuth = FirebaseAuth.getInstance();
       firebaseFirestore = FirebaseFirestore.getInstance();
       userID = firebaseAuth.getCurrentUser().getUid();
       //Get information User
        DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
        documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                fUser.setText(documentSnapshot.getString("fName"));
                fMail.setText(documentSnapshot.getString("fEmail"));
                fPhone.setText(documentSnapshot.getString("fPhone"));
                fBirth.setText(documentSnapshot.getString("fBirthDay"));
            }
        });
       btn_SignOut.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               FirebaseAuth.getInstance().signOut();
               getActivity().finish();
           }
       });
       btn_ChangeProfile.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(getActivity().getApplication(),UpdateProfile.class);
               startActivity(intent);
           }
       });
       return v;
    }
    ImageView home_btn,cart_btn;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle  savedInstanceState)
    {
        super.onViewCreated(view,savedInstanceState);
        NavController navController = Navigation.findNavController(view);
        ImageView home_btn = view.findViewById(R.id.imageHome);
        ImageView cart_btn = view.findViewById(R.id.imageCart);
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_thirdFragment_to_firstFragment);
            }
        });
        cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_thirdFragment_to_fourthFragment);
            }
        });

    }

}