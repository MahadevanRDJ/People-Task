package com.deva.peoplelist.data.model.dummyjson

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Bank (

  @SerialName("cardExpire" ) var cardExpire : String? = null,
  @SerialName("cardNumber" ) var cardNumber : String? = null,
  @SerialName("cardType"   ) var cardType   : String? = null,
  @SerialName("currency"   ) var currency   : String? = null,
  @SerialName("iban"       ) var iban       : String? = null

)