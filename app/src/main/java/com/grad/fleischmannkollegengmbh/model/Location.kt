package com.grad.fleischmannkollegengmbh.model

data class Location(
    var lat: String,
    var long: String
)
{
    constructor(): this("","")
}
