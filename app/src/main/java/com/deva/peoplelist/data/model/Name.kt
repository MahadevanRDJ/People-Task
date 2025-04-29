package com.deva.peoplelist.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Name (

  @SerialName("title" ) var title : String? = null,
  @SerialName("first" ) var first : String? = null,
  @SerialName("last"  ) var last  : String? = null

)