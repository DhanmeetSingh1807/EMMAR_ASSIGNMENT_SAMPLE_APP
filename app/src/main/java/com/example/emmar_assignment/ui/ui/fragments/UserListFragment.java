package com.example.emmar_assignment.ui.ui.fragments;

import static androidx.navigation.ViewKt.findNavController;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emmar_assignment.R;
import com.example.emmar_assignment.databinding.FragmentUserListBinding;
import com.example.emmar_assignment.ui.database.entity.User;
import com.example.emmar_assignment.ui.ui.adapter.RecylerViewAdapter;
import com.example.emmar_assignment.ui.viewmodel.UserViewModel;

import java.util.ArrayList;

/**
 * Created by Dhanmeet on 22/08/23.
 */
public class UserListFragment extends Fragment {

    private UserViewModel mViewModel;
    private FragmentUserListBinding binding;
    private RecylerViewAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_list, container, false);
        // UI initialization
        initView();
        // setting up for user data
        listnerView();
        setUpRecylerView();
        // making api call for getting user data & storing to local database
        mViewModel.FetchAndSaveUserData();
        // fetching all users from local database
        getAllUsers();
        return binding.getRoot();
    }

    private void initView() {
        // view-model initialization
        mViewModel = new ViewModelProvider(this).get(UserViewModel.class);
    }

    private void setUpRecylerView() {
        RecyclerView recyclerView = binding.viewdeveloper;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setHasFixedSize(true);

        // getting selected user data on item click & showing user details activity
        adapter = new RecylerViewAdapter(user ->
                findNavController(binding.getRoot())
                        .navigate((NavDirections) UserListFragmentDirections.actionUserListFragmentToUserDetailsFragment(user)
                        ));

        recyclerView.setAdapter(adapter);
    }

    public void listnerView() {
        binding.ivBack.setOnClickListener(v ->
                requireActivity().finish()
        );
    }

    private void getAllUsers() {
        mViewModel.getGetAllUsers().observe(requireActivity(), userList ->
                adapter.setUserList((ArrayList<User>) userList));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}