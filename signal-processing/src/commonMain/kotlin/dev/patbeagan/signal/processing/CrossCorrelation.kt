package dev.patbeagan.signal.processing

import dev.patbeagan.signal.domain.DiscreteSignal

/**
 * Full linear cross-correlation of real sequences,
 * \(R_{xy}[k] = \sum_n x[n]\,y[n + \ell]\) with lag indexing aligned so `k = 0` corresponds to
 * \(\ell = -(m-1)\), and `k = n+m-2` to \(\ell = n-1\), for lengths `n` and `m`.
 */
object CrossCorrelation {
    fun full(x: DoubleArray, y: DoubleArray): DoubleArray {
        if (x.isEmpty() || y.isEmpty()) return doubleArrayOf()
        val n = x.size
        val m = y.size
        val outLen = n + m - 1
        val out = DoubleArray(outLen)
        for (k in 0 until outLen) {
            val lag = k - (m - 1)
            var sum = 0.0
            for (i in 0 until n) {
                val j = i + lag
                if (j in 0 until m) {
                    sum += x[i] * y[j]
                }
            }
            out[k] = sum
        }
        return out
    }

    fun of(x: DiscreteSignal, y: DiscreteSignal): DiscreteSignal {
        require(x.schedule == y.schedule) {
            "Signals must share the same sampling schedule for typed cross-correlation"
        }
        return DiscreteSignal(full(x.samples, y.samples), x.schedule)
    }
}
