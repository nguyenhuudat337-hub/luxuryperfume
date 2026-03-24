package com.example.luxuryperfume;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ResourceBundle;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText edtUsername, edtPassword,edtPhone,edtEmail,edtAddress;
    private TextView tvFullName;
    private Button btnEdit;
    private boolean isEditing = false;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Ánh xạ các view từ XML
        tvFullName = view.findViewById(R.id.textView10);
        edtPhone = view.findViewById(R.id.editTextPhone);
        edtEmail = view.findViewById(R.id.editTextTextEmailAddress);
        edtAddress = view.findViewById(R.id.editTextText4);
        edtUsername = view.findViewById(R.id.editTextText3);
        edtPassword = view.findViewById(R.id.editTextTextPassword3);
        btnEdit = view.findViewById(R.id.imageButton3);

        enableEditing(false);
        // Đọc dữ liệu từ SharedPreferences
        loadUserData();

        view.findViewById(R.id.imageButton4).setOnClickListener(v -> {
            startActivity(new Intent(getContext(), MainActivity.class));
            getActivity().finish();
            Toast.makeText(getContext(), "Sign out successfully!", Toast.LENGTH_SHORT).show();
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEditing) {
                    // Chuyển sang chế độ cho phép SỬA
                    enableEditing(true);
                    btnEdit.setText("Save"); // Đổi chữ nút thành Save
                    isEditing = true;
                } else {
                    // Chuyển về chế độ CHỈ ĐỌC (Lưu dữ liệu)
                    enableEditing(false);
                    btnEdit.setText("Edit");
                    btnEdit.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edit, 0);
                    isEditing = false;

                    // Thực hiện logic lưu dữ liệu ở đây (ví dụ lưu vào SharedPreferences hoặc Database)
                    saveProfileData();
                }
            }
        });

    }

    private void enableEditing(boolean active) {
        edtUsername.setEnabled(active);
        edtPhone.setEnabled(active);
        edtPassword.setEnabled(active);
        edtEmail.setEnabled(active);
        edtAddress.setEnabled(active);

        if (active) {
            edtUsername.requestFocus(); // Con trỏ nhảy vào ô đầu tiên khi bấm Edit
        }
    }

    private void saveProfileData() {
        // Code lưu dữ liệu của bạn ở đây
        Toast.makeText(getContext(), "Profile Updated!", Toast.LENGTH_SHORT).show();
    }
    private void loadUserData() {
        // 1. Mở file SharedPreferences
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("USER_FILE", MODE_PRIVATE);

        // 2. Lấy dữ liệu (Nếu không có thì trả về chuỗi mặc định)
        String username = sharedPreferences.getString("username", "Unknown User");
        String password = sharedPreferences.getString("password", "");

        // 3. Hiển thị lên các ô nhập liệu
        edtUsername.setText(username);
        edtPassword.setText(password);
        tvFullName.setText(username); // Hiển thị tên lớn ở trên đầu
    }





}