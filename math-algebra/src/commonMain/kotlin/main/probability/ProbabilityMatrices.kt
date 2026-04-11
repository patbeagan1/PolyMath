package main.probability

import kotlin.math.abs

internal fun requireSquareStochastic(
    transition: Array<DoubleArray>,
    tolerance: Double = 1e-9,
) {
    val n = transition.size
    require(n > 0) { "Transition matrix must be non-empty" }
    transition.forEachIndexed { i, row ->
        require(row.size == n) { "Row $i must have length $n" }
        row.forEachIndexed { j, p ->
            require(p >= -tolerance && p <= 1.0 + tolerance) {
                "Entry [$i][$j]=$p is not a valid probability"
            }
        }
        val sum = row.sum()
        require(abs(sum - 1.0) <= tolerance) {
            "Row $i of transition matrix must sum to 1 (was $sum)"
        }
    }
}

internal fun requireProbabilityVector(v: DoubleArray, tolerance: Double = 1e-9) {
    require(v.isNotEmpty()) { "Distribution must be non-empty" }
    v.forEachIndexed { i, p ->
        require(p >= -tolerance && p <= 1.0 + tolerance) {
            "Entry $i=$p is not a valid probability"
        }
    }
    val sum = v.sum()
    require(abs(sum - 1.0) <= tolerance) {
        "Probabilities must sum to 1 (was $sum)"
    }
}

/** Row-stochastic matrix multiply: result = a * b */
internal fun matrixMultiply(
    a: Array<DoubleArray>,
    b: Array<DoubleArray>,
): Array<DoubleArray> {
    val n = a.size
    return Array(n) { i ->
        DoubleArray(n) { j ->
            var s = 0.0
            for (k in 0 until n) {
                s += a[i][k] * b[k][j]
            }
            s
        }
    }
}

/** Row vector (length n) times row-stochastic n×n matrix: returns new row vector */
internal fun rowTimesMatrix(row: DoubleArray, m: Array<DoubleArray>): DoubleArray {
    val n = row.size
    return DoubleArray(n) { j ->
        var s = 0.0
        for (i in 0 until n) {
            s += row[i] * m[i][j]
        }
        s
    }
}

internal fun matrixPower(p: Array<DoubleArray>, n: Int): Array<DoubleArray> {
    require(n >= 0) { "n must be non-negative" }
    val size = p.size
    if (n == 0) {
        return Array(size) { i -> DoubleArray(size) { j -> if (i == j) 1.0 else 0.0 } }
    }
    var result = Array(size) { i -> p[i].clone() }
    repeat(n - 1) {
        result = matrixMultiply(result, p)
    }
    return result
}

internal fun l1Distance(a: DoubleArray, b: DoubleArray): Double {
    require(a.size == b.size)
    var d = 0.0
    for (i in a.indices) {
        d += abs(a[i] - b[i])
    }
    return d
}
