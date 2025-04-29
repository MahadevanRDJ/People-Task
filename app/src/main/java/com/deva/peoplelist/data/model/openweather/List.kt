package com.deva.peoplelist.data.model.openweather

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class List (

    @SerialName("dt"         ) var dt         : Int?               = null,
    @SerialName("main"       ) var main       : Main?              = Main(),
    @SerialName("weather"    ) var weather    : ArrayList<Weather> = arrayListOf(),
    @SerialName("clouds"     ) var clouds     : Clouds?            = Clouds(),
    @SerialName("wind"       ) var wind       : Wind?              = Wind(),
    @SerialName("visibility" ) var visibility : Int?               = null,
    @SerialName("pop"        ) var pop        : Int?               = null,
    @SerialName("sys"        ) var sys        : Sys?               = Sys(),
    @SerialName("dt_txt"     ) var dtTxt      : String?            = null

)