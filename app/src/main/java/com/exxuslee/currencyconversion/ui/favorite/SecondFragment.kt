package com.exxuslee.currencyconversion.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.exxuslee.currencyconversion.R
import com.exxuslee.currencyconversion.databinding.FragmentSecondBinding
import com.exxuslee.currencyconversion.ui.price.FirstAdapter
import com.exxuslee.currencyconversion.utils.showIf
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private val viewModel: SecondFragmentViewModel by viewModel()
    private var _binding: FragmentSecondBinding? = null
    private lateinit var secondAdapter: SecondAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.localCurrency()
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        secondAdapter = SecondAdapter()
        binding.recyclerView.adapter = secondAdapter

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        viewModel.isLoading.observe(viewLifecycleOwner, Observer { state ->
            binding.progressBar.showIf { state }
        })

        viewModel.dataFetchState.observe(viewLifecycleOwner, Observer { state ->
            if (!state) {
                binding.errorText.visibility = View.VISIBLE
                Snackbar.make(requireView(),
                    "Oops! An error occured, check your connection and retry!",
                    Snackbar.LENGTH_LONG).show()
            }
        })

        viewModel.symbols.observe(viewLifecycleOwner, Observer { Symbol ->
            secondAdapter.updateAdapter(Symbol)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}