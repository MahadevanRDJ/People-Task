package com.deva.peoplelist.data.model.dummyjson

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Address (

    @SerialName("address"     ) var address     : String?      = null,
    @SerialName("city"        ) var city        : String?      = null,
    @SerialName("state"       ) var state       : String?      = null,
    @SerialName("stateCode"   ) var stateCode   : String?      = null,
    @SerialName("postalCode"  ) var postalCode  : String?      = null,
    @SerialName("coordinates" ) var coordinates : Coordinates? = Coordinates(),
    @SerialName("country"     ) var country     : String?      = null

)