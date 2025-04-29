package com.deva.peoplelist.data.model.openweather

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Sys (

  @SerialName("pod" ) var pod : String? = null

)