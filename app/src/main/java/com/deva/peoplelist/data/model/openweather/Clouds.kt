package com.deva.peoplelist.data.model.openweather

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Clouds (

  @SerialName("all" ) var all : Int? = null

)