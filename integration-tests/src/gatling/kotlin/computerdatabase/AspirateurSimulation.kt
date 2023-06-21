package computerdatabase


import io.gatling.javaapi.core.*
import io.gatling.javaapi.http.*
import io.gatling.javaapi.core.CoreDsl.*
import io.gatling.javaapi.http.HttpDsl.*

import java.util.concurrent.ThreadLocalRandom


class AspirateurSimulation : Simulation() {

  // Protocol Definition
  val httpProtocol = http
    .baseUrl("http://localhost:8080")
    .acceptHeader("application/json");

  // Scenario Definition
  val defineScenario = scenario("Init test aspirateur")
        .exec(http("test yanport")
                    .post("/aspirateur")
                    .header("Content-Type", "application/json")
                    .body(StringBody( 
                        """
                        {
                            "tailleX": 10,
                            "tailleY": 5,
                            "positionX": 5,
                            "positionY": 5,
                            "orientation": "N",
                            "instructions": "DADADADAA"
                        }
                        """
                    ))
                    .check(
                        status().shouldBe(200),
                        jmesPath("positionX").ofInt().shouldBe(5),
                        jmesPath("positionY").ofInt().shouldBe(6),
                        jmesPath("orientation").shouldBe("N"),
                    )
                );
    // Simulate
    init {
        setUp(
            defineScenario.injectOpen(atOnceUsers(1)),
        ).protocols(httpProtocol)
    }
}
