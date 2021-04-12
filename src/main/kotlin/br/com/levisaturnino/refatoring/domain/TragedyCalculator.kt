package br.com.levisaturnino.refatoring.domain

class TragedyCalculator(private var perf:Performance, private var play: IPlay): PerformanceCalculator(perf,play){

    override fun amount():Int{
        var result = 40000
            if (perf.audience > 30) {
                result += 1000 * (perf.audience - 30)
            }
        return result
    }
}