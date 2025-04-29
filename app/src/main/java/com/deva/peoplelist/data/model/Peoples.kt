package com.deva.peoplelist.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Peoples (

  @SerialName("results" ) var results : ArrayList<Results> = arrayListOf(),
  @SerialName("info"    ) var info    : Info?              = Info()

)