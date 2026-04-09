package dev.patbeagan.math.geometry.geospatial.model

/**
 * Mean of every vertex in the outer ring and all holes (Turf `@turf/centroid` style for polygons).
 */
fun GeoPolygon.centroidVertexMean(): GeoPosition {
    val coords = ArrayList<GeoPosition>()
    coords.addAll(outerRing.dropClosingDuplicate())
    for (hole in holes) {
        coords.addAll(hole.dropClosingDuplicate())
    }
    require(coords.isNotEmpty()) { "polygon must contain at least one position" }
    val lon = coords.sumOf { it.longitude } / coords.size
    val lat = coords.sumOf { it.latitude } / coords.size
    return GeoPosition(lon, lat)
}
