package br.com.levisaturnino.refatoring.controllers
import br.com.levisaturnino.refatoring.domain.IPlay
import br.com.levisaturnino.refatoring.domain.Invoice
import br.com.levisaturnino.refatoring.domain.Performance
import java.text.NumberFormat
import java.util.*

class PlayController {

    fun statement (invoice: Invoice, plays:List<IPlay>):String {
        var totalAmount = 0.0
        var result = "Statement for ${invoice.customer}\n"

        for ((index, perf)  in invoice.performances?.withIndex()!!) {
           // val play = playFor(index,plays)
            //var thisAmount = amount(perf,playFor(index,plays))
            // print line for this order
            result += "${playFor(index,plays).name()}: ${usd((amount(perf,playFor(index,plays))/100).toDouble())}" +
                      " (${perf.audience} seats)\n"
           totalAmount += amount(perf,playFor(index,plays))
        }

       // var volumeCredits = totalVolumeCredits(invoice,plays)

        result += "Amount owed is ${usd(totalAmount/100)}\n"
        result += "You earned ${totalVolumeCredits(invoice,plays)} credits\n"

        return result
    }

    fun totalVolumeCredits(invoice: Invoice,plays: List<IPlay>):Double{
        var volumeCredits = 0.0
        for ((index, perf)  in invoice.performances?.withIndex()!!) {
            volumeCredits  = volumeCreditsFor(playFor(index,plays),perf)
        }
       return volumeCredits
    }
    fun volumeCreditsFor( play: IPlay, perf: Performance):Double{

        var result = 0.0
        // add volume credits
        result += Math.max(perf.audience - 30, 0)
        // add extra credit for every ten comedy attendees
        if ("comedy" === play.type())
            result += Math.floor((perf.audience / 5).toDouble())

       return result
    }

    fun usd(amount: Double):String
    {
        val locale =  Locale("en", "US")
        val currencyFormatter = NumberFormat.getCurrencyInstance (locale)
        return currencyFormatter.format(amount)
    }

    fun playFor(index: Int,plays:List<IPlay>):IPlay{
        return plays[index]
    }

    fun amount(perf:Performance, play: IPlay):Int{

        var result  = 0

        when (play.type()) {
            "tragedy" -> {
                result = 40000
                if (perf.audience > 30) {
                    result += 1000 * (perf.audience - 30)
                }
            }
            "comedy" -> {
                result = 30000
                if (perf.audience > 20) {
                    result += 10000 + 500 * (perf.audience - 20)
                }
                result += 300 * perf.audience
            }
            else ->
                throw  Error ("unknown type: ${play.type()}")
        }
        return result
    }
}