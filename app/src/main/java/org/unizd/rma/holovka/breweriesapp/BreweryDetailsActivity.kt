package org.unizd.rma.holovka.breweriesapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.unizd.rma.holovka.breweriesapp.databinding.ActivityBreweryDetailsBinding

class BreweryDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBreweryDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBreweryDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val brewery = intent.getParcelableExtra<Brewery>("brewery")
        brewery?.let {
            with(binding) {
                setAttributeOrHide(textViewName, "Name", brewery.name)
                setAttributeOrHide(textViewType, "Brewery Type", brewery.brewery_type)
                setAttributeOrHide(textViewAddress, "Address", brewery.address_1)
                setAttributeOrHide(textViewCity, "City", brewery.city)
                setAttributeOrHide(textViewStateProvince, "State/Province", brewery.state_province)
                setAttributeOrHide(textViewPostalCode, "Postal Code", brewery.postal_code)
                setAttributeOrHide(textViewCountry, "Country", brewery.country)
                setAttributeOrHide(textViewPhone, "Phone", brewery.phone)
                setAttributeOrHide(textViewWebsite, "Website", brewery.website_url)
                setAttributeOrHide(textViewId, "ID", brewery.id)
                setAttributeOrHide(textViewLongitude, "Longitude", brewery.longitude)
                setAttributeOrHide(textViewLatitude, "Latitude", brewery.latitude)
                setAttributeOrHide(textViewState, "State", brewery.state)
                setAttributeOrHide(textViewStreet, "Street", brewery.street)
            }
        }

        binding.buttonBack.setOnClickListener {
            finish()
        }
    }

    private fun setAttributeOrHide(textView: TextView, attributeName: String, value: String?) {
        if (value.isNullOrEmpty() || value == "null") {
            textView.visibility = View.GONE
        } else {
            textView.visibility = View.VISIBLE
            textView.text = "$attributeName: $value"
        }
    }
}
