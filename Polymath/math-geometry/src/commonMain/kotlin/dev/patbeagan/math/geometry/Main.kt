package dev.patbeagan.math.geometry

import dev.patbeagan.math.base.Dictionary

@Suppress("UnusedReceiverParameter")
inline fun <reified T> Dictionary.geometryScope(crossinline action: Geometry. () -> T) =
    with(Geometry) { action() }