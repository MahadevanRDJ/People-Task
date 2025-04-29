package com.deva.peoplelist.data.model.openweather

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Weathers (

  @SerialName("cod"     ) var cod     : String?         = null,
  @SerialName("message" ) var message : Int?            = null,
  @SerialName("cnt"     ) var cnt     : Int?            = null,
  @SerialName("list"    ) var list    : ArrayList<List> = arrayListOf(),
  @SerialName("city"    ) var city    : City?           = City()

)