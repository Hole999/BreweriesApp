package org.unizd.rma.holovka.breweriesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.unizd.rma.holovka.breweriesapp.databinding.ActivityBreweryDetailsBinding

class BreweryDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBreweryDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBreweryDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val brewery = intent.getParcelableExtra<Brewery>("brewery")
        brewery?.let { brewery ->
            with(binding) {
                textViewName.text = formatAttribute("Name", brewery.name)
                textViewType.text = formatAttribute("Brewery Type", brewery.brewery_type)
                textViewAddress.text = formatAttribute("Address", brewery.address_1)
                textViewCity.text = formatAttribute("City", brewery.city)
                textViewStateProvince.text = formatAttribute("State/Province", brewery.state_province)
                textViewPostalCode.text = formatAttribute("Postal Code", brewery.postal_code)
                textViewCountry.text = formatAttribute("Country", brewery.country)
                textViewPhone.text = formatAttribute("Phone", brewery.phone)
                textViewWebsite.text = formatAttribute("Website", brewery.website_url)
                textViewId.text = formatAttribute("ID", brewery.id)
                textViewLongitude.text = formatAttribute("Longitude", brewery.longitude)
                textViewLatitude.text = formatAttribute("Latitude", brewery.latitude)
                textViewState.text = formatAttribute("State", brewery.state)
                textViewStreet.text = formatAttribute("Street", brewery.street)
            }
        }

        binding.buttonBack.setOnClickListener {
            finish()
        }
    }

    private fun formatAttribute(attributeName: String, value: String?): String {
        return if (value.isNullOrEmpty()) {
            ""
        } else {
            "$attributeName: $value"
        }
    }
}
