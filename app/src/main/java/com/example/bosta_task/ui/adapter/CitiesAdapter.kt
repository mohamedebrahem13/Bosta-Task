package com.example.bosta_task.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bosta_task.databinding.ItemCityBinding
import com.example.bosta_task.domain.models.City


class CitiesAdapter : ListAdapter<City, CitiesAdapter.CityViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val binding = ItemCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CityViewHolder(private val binding: ItemCityBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(city: City) {
            val areaAdapter = CustomSpinnerAdapter(
                binding.root.context,
                city.cityName,
                city.districts
            )
            binding.spinner.adapter = areaAdapter
        }
    }
    class DiffCallback : DiffUtil.ItemCallback<City>() {
        override fun areItemsTheSame(oldItem: City, newItem: City) = oldItem.cityId == newItem.cityId
        override fun areContentsTheSame(oldItem: City, newItem: City) = oldItem == newItem
    }
}