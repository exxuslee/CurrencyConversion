package com.exxuslee.currencyconversion.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.exxuslee.currencyconversion.R
import com.exxuslee.currencyconversion.databinding.FragmentFirstBinding
import com.exxuslee.currencyconversion.utils.showIf
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val viewModel: FistFragmentViewModel by viewModel()
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {

     //   viewModel.getLocalCardInfo()
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        viewModel.isLoading.observe(viewLifecycleOwner, Observer { state ->
            binding.progressBar.showIf { state }
        })

        viewModel.dataFetchState.observe(viewLifecycleOwner, Observer { state ->
            if (!state){
                binding.errorText.visibility = View.VISIBLE
                Snackbar.make(requireView(),"Oops! An error occured, check your connection and retry!",
                    Snackbar.LENGTH_LONG).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}