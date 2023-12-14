package com.hcdisat.countrylist.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hcdisat.countrylist.databinding.FragmentHomeBinding
import com.hcdisat.countrylist.ui.home.adapter.CountryAdapter
import com.hcdisat.countrylist.ui.home.model.HomeState
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels { HomeViewModel.Factory }

    private val homeAdapter by lazy {
        CountryAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.countries.apply {
            adapter = homeAdapter
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.state.collect(::handleState)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleState(state: HomeState) {
        when (state) {
            is HomeState.Completed -> {
                binding.progress.visibility = View.GONE
                homeAdapter.setCountries(state.countries)
                binding.countries.visibility = View.VISIBLE
            }
            is HomeState.Error -> {
                // here we could handle errors, and show them in a pretty nice UI.
                // logging the errors for now
                Log.e("HomeFragment", "${state.throwable.message}", state.throwable)
            }
            HomeState.Loading -> {
                binding.countries.visibility = View.GONE
                binding.progress.visibility = View.VISIBLE
            }
        }
    }
}