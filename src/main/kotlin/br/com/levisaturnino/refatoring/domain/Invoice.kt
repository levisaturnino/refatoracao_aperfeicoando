package br.com.levisaturnino.refatoring.domain

class Invoice {
    var customer: String? = null
    var performances: List<Performance>? = ArrayList<Performance>()

    init {
        performances = populate()
    }

    fun populate():List<Performance> {

        val performances: MutableList<Performance> = ArrayList()

        performances.add(Performance(Hamlet("Hamlet", "tragedy"),55))
        performances.add(Performance(AsLike("As You Like It", "comedy"),35))
        performances.add(Performance(Othello("Othello", "tragedy"),40))

        return performances
    }
}