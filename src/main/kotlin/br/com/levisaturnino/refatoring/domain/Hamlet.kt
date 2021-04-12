package br.com.levisaturnino.refatoring.domain

class Hamlet(private val name: String, private val type: String) : IPlay {

    override fun name(): String? {
        return name
    }


    override fun type(): String? {
        return type
    }
}