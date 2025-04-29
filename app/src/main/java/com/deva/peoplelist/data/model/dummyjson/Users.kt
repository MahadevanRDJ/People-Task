package com.deva.peoplelist.data.model.dummyjson

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Users (

  @SerialName("users" ) var users : ArrayList<User> = arrayListOf(),
  @SerialName("total" ) var total : Int?             = null,
  @SerialName("skip"  ) var skip  : Int?             = null,
  @SerialName("limit" ) var limit : Int?             = null

)

@Serializable
data class User (

  @SerialName("id"         ) var id         : Int?     = null,
  @SerialName("firstName"  ) var firstName  : String?  = null,
  @SerialName("lastName"   ) var lastName   : String?  = null,
  @SerialName("maidenName" ) var maidenName : String?  = null,
  @SerialName("age"        ) var age        : Int?     = null,
  @SerialName("gender"     ) var gender     : String?  = null,
  @SerialName("email"      ) var email      : String?  = null,
  @SerialName("phone"      ) var phone      : String?  = null,
  @SerialName("username"   ) var username   : String?  = null,
  @SerialName("password"   ) var password   : String?  = null,
  @SerialName("birthDate"  ) var birthDate  : String?  = null,
  @SerialName("image"      ) var image      : String?  = null,
  @SerialName("bloodGroup" ) var bloodGroup : String?  = null,
  @SerialName("height"     ) var height     : Double?  = null,
  @SerialName("weight"     ) var weight     : Double?  = null,
  @SerialName("eyeColor"   ) var eyeColor   : String?  = null,
  @SerialName("hair"       ) var hair       : Hair?    = Hair(),
  @SerialName("ip"         ) var ip         : String?  = null,
  @SerialName("address"    ) var address    : Address? = Address(),
  @SerialName("macAddress" ) var macAddress : String?  = null,
  @SerialName("university" ) var university : String?  = null,
  @SerialName("bank"       ) var bank       : Bank?    = Bank(),
  @SerialName("company"    ) var company    : Company? = Company(),
  @SerialName("ein"        ) var ein        : String?  = null,
  @SerialName("ssn"        ) var ssn        : String?  = null,
  @SerialName("userAgent"  ) var userAgent  : String?  = null,
  @SerialName("crypto"     ) var crypto     : Crypto?  = Crypto(),
  @SerialName("role"       ) var role       : String?  = null

)