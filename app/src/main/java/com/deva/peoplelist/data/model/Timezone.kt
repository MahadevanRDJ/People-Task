package com.deva.peoplelist.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Timezone (

  @SerialName("offset"      ) var offset      : String? = null,
  @SerialName("description" ) var description : String? = null

)