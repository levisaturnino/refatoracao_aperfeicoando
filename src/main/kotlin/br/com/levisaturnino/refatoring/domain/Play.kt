package br.com.levisaturnino.refatoring.domain

import br.com.levisaturnino.refatoring.domain.Hamlet
import br.com.levisaturnino.refatoring.domain.AsLike
import br.com.levisaturnino.refatoring.domain.Othello
import br.com.levisaturnino.refatoring.domain.IPlay
import java.util.ArrayList

class Play {
    var hamlet: Hamlet? = null
    // @JsonProperty("as­like")
    var asLike: AsLike? = null
    var othello: Othello? = null

    companion object {
        fun populate(): List<IPlay> {
            val list: MutableList<IPlay> = ArrayList()
            list.add(Hamlet("Hamlet", "tragedy"))
            list.add(AsLike("As You Like It", "comedy"))
            list.add(Othello("Othello", "tragedy"))
            return list
        }
    }
}