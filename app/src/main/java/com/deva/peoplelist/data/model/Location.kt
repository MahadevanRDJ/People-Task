package com.deva.peoplelist.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Location (

    @SerialName("street"      ) var street      : Street?      = Street(),
    @SerialName("city"        ) var city        : String?      = null,
    @SerialName("state"       ) var state       : String?      = null,
    @SerialName("country"     ) var country     : String?      = null,
    @SerialName("postcode"    ) var postcode    : String?      = null,
    @SerialName("coordinates" ) var coordinates : Coordinates? = Coordinates(),
    @SerialName("timezone"    ) var timezone    : Timezone?    = Timezone()

)