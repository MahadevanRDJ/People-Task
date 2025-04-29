package com.deva.peoplelist.data.model.dummyjson

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Hair (

  @SerialName("color" ) var color : String? = null,
  @SerialName("type"  ) var type  : String? = null

)