package com.example.dummyapp.presentation.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.example.dummyapp.R
import com.example.dummyapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.shimmerLoadingContainer.visibility=View.VISIBLE
        binding.shimmerLoadingContainer.startShimmerAnimation()

        val rotateAnimationInner= AnimationUtils.loadAnimation(requireActivity(), R.anim.customize_circular_progress_bar_inner)
        val rotateAnimationOuter = AnimationUtils.loadAnimation(requireActivity(), R.anim.customize_circular_progress_bar_outer)
        binding.loadingLayout.loadingProgressOuter.startAnimation(rotateAnimationOuter)
        binding.loadingLayout.loadingProgressInner.startAnimation(rotateAnimationInner)
    }

}