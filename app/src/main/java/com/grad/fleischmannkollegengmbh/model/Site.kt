package com.grad.fleischmannkollegengmbh.model

data class Site(
    var siteName: String,
    var siteImage: String,
    var siteStreet:String,
    var lightOff: Boolean,
    var thereGap: Boolean,
    var dirty: Boolean,
    var siteLocation:Location

)
{
    constructor() : this("", "", "", false,false,true, Location("",""))

}