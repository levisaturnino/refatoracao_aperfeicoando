package br.com.levisaturnino.refatoring.controllers
import br.com.levisaturnino.refatoring.domain.*
import java.text.NumberFormat
import java.util.*

class PlayController {

    fun statement(invoice: Invoice, plays:List<IPlay>):String {

        return renderPlainText(invoice,plays)
    }

    fun renderPlainText (invoice: Invoice, plays:List<IPlay>):String {
        var result = "Statement for ${invoice.customer}\n"
        for ((index, perf)  in invoice.performances?.withIndex()!!) {
            result += "${playFor(index,plays).name()}: ${usd((createPerformanceCalculator(perf,playFor(index,plays)).amount()/100).toDouble())}" +
                      " (${perf.audience} seats)\n"
        }
        result += "Amount owed is ${usd(totalAmount(invoice,plays)/100)}\n"
        result += "You earned ${totalVolumeCredits(invoice,plays)} credits\n"
        return result
    }

    fun createPerformanceCalculator( perf:Performance, play: IPlay):PerformanceCalculator{
        when (play.type()) {
            "tragedy" -> {
                return TragedyCalculator(perf,play)
            }
            "comedy" -> {
                return ComedyCalculator(perf,play)
            }
            else ->
                throw  Error ("unknown type: ${play.type()}")
        }
    }

    fun totalAmount(invoice: Invoice,plays: List<IPlay>):Double{
        var result = 0.0
        for ((index, perf)  in invoice.performances?.withIndex()!!) {
            result += createPerformanceCalculator(perf,playFor(index,plays)).amount() // amount(perf,playFor(index,plays))
        }

       return result
    }

    fun totalVolumeCredits(invoice: Invoice,plays: List<IPlay>):Double{
        var result = 0.0
        for ((index, perf)  in invoice.performances?.withIndex()!!) {
            result  = createPerformanceCalculator(perf,playFor(index,plays)).volumeCredits()
        }
       return result
    }

    /*fun volumeCreditsFor( play: IPlay, perf: Performance):Double{
        var result = 0.0
        result += Math.max(perf.audience - 30, 0)
        if ("comedy" === play.type())
            result += Math.floor((perf.audience / 5).toDouble())
       return result
    }*/

    fun usd(amount: Double):String
    {
        val locale =  Locale("en", "US")
        val currencyFormatter = NumberFormat.getCurrencyInstance (locale)
        return currencyFormatter.format(amount)
    }

    fun playFor(index: Int,plays:List<IPlay>):IPlay{
        return plays[index]
    }

/*    fun amount(perf:Performance, play: IPlay):Int{
        var result  = 0
        when (play.type()) {
            "tragedy" -> {
                return TragedyCalculator(perf,play).amount()
            }
            "comedy" -> {
                return ComedyCalculator(perf,play).amount()
            }
            else ->
                throw  Error ("unknown type: ${play.type()}")
        }
        return result
    }*/
}