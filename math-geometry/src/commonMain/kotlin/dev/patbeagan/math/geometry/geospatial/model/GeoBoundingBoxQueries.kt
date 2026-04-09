package dev.patbeagan.math.geometry.geospatial.model

/** Smallest axis-aligned bbox containing all coordinates of this line string. */
fun GeoLineString.boundingBox(): GeoBoundingBox = GeoBoundingBox.fromPositions(coordinates)

/** Smallest axis-aligned bbox containing every ring of this polygon. */
fun GeoPolygon.boundingBox(): GeoBoundingBox {
    val seq =
        sequence {
            yieldAll(outerRing)
            holes.forEach { yieldAll(it) }
        }
    return GeoBoundingBox.fromPositions(seq.asIterable())
}

/** Bbox covering all parts. */
fun MultiGeoLineString.boundingBox(): GeoBoundingBox =
    GeoBoundingBox.fromPositions(lineStrings.flatMap { it.coordinates })

/** Bbox covering all polygon parts. */
fun MultiGeoPolygon.boundingBox(): GeoBoundingBox =
    GeoBoundingBox.fromPositions(
        polygons.flatMap { p -> p.outerRing + p.holes.flatten() },
    )
