package com.deva.peoplelist.data.model.openweather

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Main (

  @SerialName("temp"       ) var temp      : Double? = null,
  @SerialName("feels_like" ) var feelsLike : Double? = null,
  @SerialName("temp_min"   ) var tempMin   : Double? = null,
  @SerialName("temp_max"   ) var tempMax   : Double? = null,
  @SerialName("pressure"   ) var pressure  : Int?    = null,
  @SerialName("sea_level"  ) var seaLevel  : Int?    = null,
  @SerialName("grnd_level" ) var grndLevel : Int?    = null,
  @SerialName("humidity"   ) var humidity  : Int?    = null,
  @SerialName("temp_kf"    ) var tempKf    : Double? = null

)