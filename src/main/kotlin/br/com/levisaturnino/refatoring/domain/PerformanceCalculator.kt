package br.com.levisaturnino.refatoring.domain

open class PerformanceCalculator(private var perf:Performance, private var play: IPlay) {

    open fun amount():Int{
     throw Error("subclass responsibility");
    }

    open fun volumeCredits():Double{
        return  Math.max(perf.audience - 30, 0).toDouble()
    }
}