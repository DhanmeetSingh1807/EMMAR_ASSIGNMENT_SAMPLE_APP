package com.example.emmar_assignment.ui.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.emmar_assignment.R
import com.example.emmar_assignment.databinding.FragmentUserDetailsBinding

/**
 * Created by Dhanmeet on 22/08/23.
 */
class UserDetailsFragment : Fragment() {

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding : FragmentUserDetailsBinding?
        get() = _binding!!

    private val args: UserDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        listnerView()
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            user = args.user
        }
    }

    private fun listnerView(){
       binding?.ivBack?.setOnClickListener {
           findNavController().popBackStack(R.id.userListFragment, false)
       }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}