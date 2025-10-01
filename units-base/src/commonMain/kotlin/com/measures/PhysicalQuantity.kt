package com.measures

data class PhysicalQuantity(
    val magnitude: Double,
    val dimension: PhysicalDimension
) {
    fun isCommensurableTo(other: PhysicalQuantity) = dimension == other.dimension
    operator fun plus(other: PhysicalQuantity) {
//if(isCommensurableTo(other)) {
//    PhysicalQuantity(this.magnitude + other.magnitude, dimension)
//}
    }

    data class PhysicalDimension(
        val timeExp: Int, // (T),
        val lengthExp: Int, // (L),
        val massExp: Int, // (M),
        val electricCurrentExp: Int, // (I),
        val absoluteTemperatureExp: Int, // (Θ),
        val amountOfSubstanceExp: Int, // (N)
        val luminousIntensityExp: Int, // (J).
    )
}