package com.deva.peoplelist.data.model.dummyjson

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Crypto (

  @SerialName("coin"    ) var coin    : String? = null,
  @SerialName("wallet"  ) var wallet  : String? = null,
  @SerialName("network" ) var network : String? = null

)