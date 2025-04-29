package com.deva.peoplelist.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Info (

  @SerialName("seed"    ) var seed    : String? = null,
  @SerialName("results" ) var results : Int?    = null,
  @SerialName("page"    ) var page    : Int?    = null,
  @SerialName("version" ) var version : String? = null

)