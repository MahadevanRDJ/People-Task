package com.deva.peoplelist.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Login (

  @SerialName("uuid"     ) var uuid     : String? = null,
  @SerialName("username" ) var username : String? = null,
  @SerialName("password" ) var password : String? = null,
  @SerialName("salt"     ) var salt     : String? = null,
  @SerialName("md5"      ) var md5      : String? = null,
  @SerialName("sha1"     ) var sha1     : String? = null,
  @SerialName("sha256"   ) var sha256   : String? = null

)