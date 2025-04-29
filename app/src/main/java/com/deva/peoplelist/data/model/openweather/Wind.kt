package com.deva.peoplelist.data.model.openweather

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Wind (

  @SerialName("speed" ) var speed : Double? = null,
  @SerialName("deg"   ) var deg   : Int?    = null,
  @SerialName("gust"  ) var gust  : Double? = null

)