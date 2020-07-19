package micronaut.graal

import com.netflix.spectator.gc.GcLogger
import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        GcLogger().start(null)
        Micronaut.build()
                .packages("micronaut.graal")
                .mainClass(Application.javaClass)
                .start()
    }
}