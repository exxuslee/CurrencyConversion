package com.exxuslee.currencyconversion.ui.price

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.exxuslee.currencyconversion.databinding.FragmentFirstBinding
import com.exxuslee.currencyconversion.utils.showIf
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val viewModelFirst: FistFragmentViewModel by viewModel()

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private lateinit var firstAdapter: FirstAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {

        viewModelFirst.localPrice(
            arguments?.getString("base")?:"EUR",
            arguments?.getString("check")?:"BTC"
        )
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstAdapter = FirstAdapter()
        binding.recyclerView.adapter = firstAdapter

        viewModelFirst.isLoading.observe(viewLifecycleOwner) { state ->
            binding.progressBar.showIf { state }
        }

        viewModelFirst.dataFetchState.observe(viewLifecycleOwner) { state ->
            if (!state) {
                binding.errorText.visibility = View.VISIBLE
                Snackbar.make(requireView(),
                    "Oops! An error occured, check your connection and retry!",
                    Snackbar.LENGTH_LONG).show()
            }
        }

        viewModelFirst.price.observe(viewLifecycleOwner) { Price ->
            //           binding.textBase.text = Price?.date
            firstAdapter.updateAdapter(Price)
        }

        binding.textBase.text = arguments?.getString("name")

        firstAdapter.onPriceClickListener = {
            Log.d(TAG, "position $it")
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "price"
    }
}