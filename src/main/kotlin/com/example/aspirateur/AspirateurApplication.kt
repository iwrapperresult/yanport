package com.example.aspirateur

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AspirateurController {
	@PostMapping("/aspirateur")
	fun controlerAspirateur(@RequestBody request: AspirateurRequest): AspirateurResponse {
		val piece = Piece(request.tailleX, request.tailleY)
		val aspirateur = Aspirateur(request.positionX, request.positionY, request.orientation)

		request.instructions.forEach { instruction ->
			when (instruction) {
				'D' -> aspirateur.pivoterADroite()
				'G' -> aspirateur.pivoterAGauche()
				'A' -> aspirateur.avancer()
			}
		}

		return AspirateurResponse(aspirateur.x, aspirateur.y, aspirateur.orientation)
	}
}

data class AspirateurRequest(
	val tailleX: Int,
	val tailleY: Int,
	val positionX: Int,
	val positionY: Int,
	val orientation: Aspirateur.Orientation,
	val instructions: String
)

data class AspirateurResponse(
	val positionX: Int,
	val positionY: Int,
	val orientation: Aspirateur.Orientation
)
@SpringBootApplication
class AspirateurApplication

fun main(args: Array<String>) {
	runApplication<AspirateurApplication>(*args)
}


data class Aspirateur(var x: Int, var y: Int, var orientation: Orientation) {
	enum class Orientation {
		N, E, W, S
	}

	fun pivoterADroite() {
		orientation = when (orientation) {
			Orientation.N -> Orientation.E
			Orientation.E -> Orientation.S
			Orientation.S -> Orientation.W
			Orientation.W -> Orientation.N
		}
	}

	fun pivoterAGauche() {
		orientation = when (orientation) {
			Orientation.N -> Orientation.W
			Orientation.E -> Orientation.N
			Orientation.S -> Orientation.E
			Orientation.W -> Orientation.S
		}
	}

	fun avancer() {
		when (orientation) {
			Orientation.N -> y++
			Orientation.E -> x++
			Orientation.S -> y--
			Orientation.W -> x--
		}
	}
}

data class Piece(val tailleX: Int, val tailleY: Int)
