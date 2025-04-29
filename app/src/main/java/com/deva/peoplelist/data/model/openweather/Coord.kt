package com.deva.peoplelist.data.model.openweather

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Coord (

  @SerialName("lat" ) var lat : Double? = null,
  @SerialName("lon" ) var lon : Double? = null

)