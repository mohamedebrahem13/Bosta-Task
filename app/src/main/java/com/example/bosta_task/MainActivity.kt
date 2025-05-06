package com.example.bosta_task

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bosta_task.databinding.ActivityMainBinding
import com.example.bosta_task.ui.adapter.CitiesAdapter
import com.example.bosta_task.ui.viewmodel.CityViewModel
import com.example.bosta_task.ui.viewmodel.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val cityViewModel: CityViewModel by viewModels { viewModelFactory }

    private lateinit var citiesAdapter: CitiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as BostaApplication).appComponent.inject(this)

        setupRecyclerView()
        collectViewModelState()

        cityViewModel.getCities("60e4482c7cb7d4bc4849c4d5")

        searchListener()
    }


    private fun setupRecyclerView() {
        citiesAdapter = CitiesAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = citiesAdapter
        }
    }

    private fun searchListener() {
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                cityViewModel.updateSearchQuery(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }


    private fun collectViewModelState() {
        lifecycleScope.launch {
            cityViewModel.isLoading.collectLatest { isLoading ->
                binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
                binding.recyclerView.visibility = if (isLoading) View.GONE else View.VISIBLE
            }
        }

        lifecycleScope.launch {
            cityViewModel.filteredCities.collectLatest { cities ->
                citiesAdapter.submitList(cities)
            }
        }

        lifecycleScope.launch {
            cityViewModel.errorMessage.collectLatest { error ->
                error?.let {
                    Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}