package com.hcdisat.countrylist.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hcdisat.countrylist.databinding.CountryItemBinding
import com.hcdisat.countrylist.domain.model.DomainCountry

class CountryAdapter: RecyclerView.Adapter<CountryViewHolder>() {
    private var countries: List<DomainCountry> = listOf()

    fun setCountries(newCountries: List<DomainCountry>) {
        countries = newCountries
        notifyItemRangeInserted(0, newCountries.size)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder =
        CountryViewHolder(
            CountryItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(country = countries[position])
    }
}

class CountryViewHolder(
    private val binding: CountryItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(country: DomainCountry) {
        with(binding) {
            countryName.text = country.name
            countryRegion.text = country.region
            countryCode.text = country.code
            countryCapital.text = country.capital
        }
    }
}