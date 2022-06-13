package com.exxuslee.currencyconversion.ui.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.exxuslee.currencyconversion.databinding.FragmentSecondBinding
import com.exxuslee.currencyconversion.ui.price.FirstFragment
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

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        viewModel.localCurrency()
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        secondAdapter = SecondAdapter()
        binding.recyclerView.adapter = secondAdapter

        viewModel.isLoading.observe(viewLifecycleOwner) { state ->
            binding.progressBar.showIf { state }
        }

        viewModel.dataFetchState.observe(viewLifecycleOwner) { state ->
            if (!state) {
                binding.errorText.visibility = View.VISIBLE
                Snackbar.make(requireView(),
                    "Oops! An error occured, check your connection and retry!",
                    Snackbar.LENGTH_LONG).show()
            }
        }

        viewModel.symbols.observe(viewLifecycleOwner) {
            if (it != null) {
                secondAdapter.list = it.symbols
            }
        }

        secondAdapter.onPriceClickListener = {
            Log.d(FirstFragment.TAG, "position $it")
        }

        secondAdapter.onRadioClickListener = {
//            secondAdapter.notifyDataSetChanged()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}