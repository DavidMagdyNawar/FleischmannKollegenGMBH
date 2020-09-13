package com.grad.fleischmannkollegengmbh.model

data class LastChecked(
    var agent: String,
    var time: String,
    var date: String,
    var solved: Boolean
) {
    constructor() : this("", "", "", false)
}