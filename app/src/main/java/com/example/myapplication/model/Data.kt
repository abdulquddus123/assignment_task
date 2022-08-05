package com.example.myapplication.model
import com.google.gson.annotations.SerializedName

data class Data (
    @SerializedName("id"        ) var id        : String? = null,
    @SerializedName("firstName" ) var firstName : String? = null,
    @SerializedName("lastName"  ) var lastName  : String? = null,
    @SerializedName("email"     ) var email     : String? = null,
    @SerializedName("phone"     ) var phone     : String? = null
)
