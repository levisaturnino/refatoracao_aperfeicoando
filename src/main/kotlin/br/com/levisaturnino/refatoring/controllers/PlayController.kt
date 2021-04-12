package br.com.levisaturnino.refatoring.controllers
import br.com.levisaturnino.refatoring.domain.IPlay
import br.com.levisaturnino.refatoring.domain.Invoice
import java.text.NumberFormat
import java.util.*

class PlayController {

    fun statement (invoice: Invoice, plays:List<IPlay>):String {
        var totalAmount = 0.0
        var volumeCredits = 0.0
        var result = "Statement for ${invoice.customer}\n"

        for ((index, perf)  in invoice.performances?.withIndex()!!) {
            val play = plays[index]
            var thisAmount = 0

            when (play.type()) {
                "tragedy" -> {
                    thisAmount = 40000
                    if (perf.audience > 30) {
                        thisAmount += 1000 * (perf.audience - 30)
                    }
                }
                "comedy" -> {
                    thisAmount = 30000
                    if (perf.audience > 20) {
                        thisAmount += 10000 + 500 * (perf.audience - 20)
                    }
                    thisAmount += 300 * perf.audience
                }
                else ->
                    throw  Error ("unknown type: ${play.type()}")
            }

            // add volume credits
            volumeCredits += Math.max(perf.audience - 30, 0)
            // add extra credit for every ten comedy attendees
            if ("comedy" === play.type())
                volumeCredits += Math.floor((perf.audience / 5).toDouble())

            // print line for this order
            result += "${play.name()}: ${format((thisAmount/100).toDouble())} (${perf.audience} seats)\n"
            totalAmount += thisAmount
        }

        result += "Amount owed is ${format(totalAmount/100)}\n"
        result += "You earned ${volumeCredits} credits\n"

        return result
    }

    fun format(amount: Double):String
    {
        val locale =  Locale("en", "US")
        val currencyFormatter = NumberFormat.getCurrencyInstance (locale)
        return currencyFormatter.format(amount)
    }
}