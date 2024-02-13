package org.unizd.rma.holovka.breweriesapp

import android.os.Parcel
import android.os.Parcelable

class Brewery(
    val id: String?,
    val name: String?,
    val brewery_type: String?,
    val address_1: String?,
    val street: String?,
    val city: String?,
    val state: String?,
    val state_province: String?,
    val postal_code: String?,
    val country: String?,
    val longitude: String?,
    val latitude: String?,
    val phone: String?,
    val website_url: String?,
    val updated_at: String?,
    val created_at: String?,
    val tag_list: List<String>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(brewery_type)
        parcel.writeString(address_1)
        parcel.writeString(street)
        parcel.writeString(city)
        parcel.writeString(state)
        parcel.writeString(state_province)
        parcel.writeString(postal_code)
        parcel.writeString(country)
        parcel.writeString(longitude)
        parcel.writeString(latitude)
        parcel.writeString(phone)
        parcel.writeString(website_url)
        parcel.writeString(updated_at)
        parcel.writeString(created_at)
        parcel.writeStringList(tag_list)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Brewery> {
        override fun createFromParcel(parcel: Parcel): Brewery {
            return Brewery(parcel)
        }

        override fun newArray(size: Int): Array<Brewery?> {
            return arrayOfNulls(size)
        }
    }
}
