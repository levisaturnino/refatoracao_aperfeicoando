package br.com.levisaturnino.refatoring.domain

class Performance {
    var playID: IPlay? = null
    var audience = 0

    constructor(playID: IPlay?, audience: Int) {
        this.playID = playID
        this.audience = audience
    }
}