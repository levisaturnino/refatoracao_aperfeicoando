package br.com.levisaturnino.refatoring

import br.com.levisaturnino.refatoring.controllers.PlayController
import br.com.levisaturnino.refatoring.domain.Invoice
import br.com.levisaturnino.refatoring.domain.Play
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RefatoringApplication : CommandLineRunner {
	override fun run(vararg args: String?) {

		var playController = PlayController()
		var invoice = Invoice()
		invoice.customer =  "BigCo"

		print(playController.statement(invoice,Play.populate()))

	}
}

fun main(args: Array<String>) {
	runApplication<RefatoringApplication>(*args)
}
