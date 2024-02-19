import dev.patbeagan.physics.classical.PhysicsAcoustics
import dev.patbeagan.physics.classical.PhysicsElectricity
import dev.patbeagan.physics.classical.PhysicsOptics
import dev.patbeagan.math.base.Dictionary

@Suppress("UnusedReceiverParameter")
inline fun <reified T> Dictionary.physicsOpticsScope(crossinline action: PhysicsOptics. () -> T) =
    with(PhysicsOptics) { action() }

@Suppress("UnusedReceiverParameter")
inline fun <reified T> Dictionary.physicsElectricityScope(crossinline action: PhysicsElectricity. () -> T) =
    with(PhysicsElectricity) { action() }

@Suppress("UnusedReceiverParameter")
inline fun <reified T> Dictionary.physicsAcousticsScope(crossinline action: PhysicsAcoustics. () -> T) =
    with(PhysicsAcoustics) { action() }