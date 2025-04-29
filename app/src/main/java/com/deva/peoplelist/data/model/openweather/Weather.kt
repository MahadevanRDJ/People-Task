package com.deva.peoplelist.data.model.openweather

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Weather (

  @SerialName("id"          ) var id          : Int?    = null,
  @SerialName("main"        ) var main        : String? = null,
  @SerialName("description" ) var description : String? = null,
  @SerialName("icon"        ) var icon        : String? = null

)