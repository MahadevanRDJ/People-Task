package com.deva.peoplelist.data.model.dummyjson

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Coordinates (

  @SerialName("lat" ) var lat : Double? = null,
  @SerialName("lng" ) var lng : Double? = null

)