package br.com.levisaturnino.refatoring.domain

class ComedyCalculator(private var perf:Performance, private var play: IPlay): PerformanceCalculator(perf,play){

    override fun amount():Int{
        var result = 30000
                if (perf.audience > 20) {
                    result += 10000 + 500 * (perf.audience - 20)
                }
                result += 300 * perf.audience

        return result
    }

    override fun volumeCredits():Double{
        return super.volumeCredits() + Math.floor((perf.audience / 5).toDouble())
    }
}