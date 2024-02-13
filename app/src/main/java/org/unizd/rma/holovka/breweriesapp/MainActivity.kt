package org.unizd.rma.holovka.breweriesapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import org.json.JSONArray


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BreweriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        adapter = BreweriesAdapter(emptyList()) { brewery ->
            val intent = Intent(this, BreweryDetailsActivity::class.java).apply {
                putExtra("brewery", brewery)
            }
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        fetchBreweries()
    }


    private fun fetchBreweries() {
        Thread {
            val urlString = "https://api.openbrewerydb.org/v1/breweries"
            val result = makeHttpRequest(urlString)
            val breweriesList = parseBreweriesData(result)

            Handler(Looper.getMainLooper()).post {
                adapter.updateData(breweriesList)
            }

        }.start()
    }

    fun parseBreweriesData(jsonData: String?): List<Brewery> {
        val breweriesList = mutableListOf<Brewery>()
        jsonData?.let {
            val jsonArray = JSONArray(it)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val brewery = Brewery(
                    id = jsonObject.optString("id"),
                    name = jsonObject.optString("name"),
                    brewery_type = jsonObject.optString("brewery_type"),
                    address_1 = jsonObject.optString("address_1"),
                    street = jsonObject.optString("street"),
                    city = jsonObject.optString("city"),
                    state = jsonObject.optString("state"),
                    state_province = jsonObject.optString("state_province"),
                    postal_code = jsonObject.optString("postal_code"),
                    country = jsonObject.optString("country"),
                    longitude = jsonObject.optString("longitude"),
                    latitude = jsonObject.optString("latitude"),
                    phone = jsonObject.optString("phone"),
                    website_url = jsonObject.optString("website_url"),
                    updated_at = jsonObject.optString("updated_at"),
                    created_at = jsonObject.optString("created_at"),
                    tag_list = jsonObject.optJSONArray("tag_list")?.let { array ->
                        List(array.length()) { index -> array.optString(index) }
                    } ?: emptyList()
                )
                breweriesList.add(brewery)
            }
        }
        return breweriesList
    }

    private fun makeHttpRequest(urlString: String): String {
        val url = URL(urlString)
        val urlConnection = url.openConnection() as HttpURLConnection
        urlConnection.requestMethod = "GET"

        val inputStream = urlConnection.inputStream
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val response = bufferedReader.use { it.readText() }

        urlConnection.disconnect()
        return response
    }
}



