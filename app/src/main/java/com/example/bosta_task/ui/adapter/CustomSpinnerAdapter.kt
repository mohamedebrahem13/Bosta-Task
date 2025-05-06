package com.example.bosta_task.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.bosta_task.databinding.ItemCustomSpinnerBinding
import com.example.bosta_task.domain.models.District

class CustomSpinnerAdapter(
    context: Context,
    private val cityName: String,
    private val districts: List<District>
) : ArrayAdapter<Any>(context, 0, listOf(cityName) + districts) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemCustomSpinnerBinding.inflate(LayoutInflater.from(context), parent, false)
        binding.spinnerText.text = cityName
        binding.spinnerUncoveredText.visibility = View.GONE
        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemCustomSpinnerBinding.inflate(LayoutInflater.from(context), parent, false)
        if (position == 0) {
            binding.spinnerText.text = cityName
            binding.spinnerUncoveredText.visibility = View.GONE
        } else {
            val district = districts[position - 1]
            binding.spinnerText.text = district.districtName
            binding.spinnerUncoveredText.visibility =
                if (district.coverage.equals("Bosta", ignoreCase = true)) View.GONE else View.VISIBLE
        }

        return binding.root
    }
}