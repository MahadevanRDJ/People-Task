package com.deva.peoplelist.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Street (

  @SerialName("number" ) var number : Int?    = null,
  @SerialName("name"   ) var name   : String? = null

)