package com.deva.peoplelist.data.model.dummyjson

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Company (

  @SerialName("department" ) var department : String?  = null,
  @SerialName("name"       ) var name       : String?  = null,
  @SerialName("title"      ) var title      : String?  = null,
  @SerialName("address"    ) var address    : Address? = Address()

)