package main

import main.probability.DiscreteBayesian
import main.probability.FiniteDistribution
import main.probability.MarkovChain
import main.probability.MonteCarlo
import kotlin.math.abs
import kotlin.math.ln
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class ProbabilityReasoningTest {

    @Test
    fun markovChain_twoState_stepAndPower() {
        val labels = listOf("A", "B")
        val p = arrayOf(
            doubleArrayOf(0.7, 0.3),
            doubleArrayOf(0.4, 0.6),
        )
        val mc = MarkovChain(labels, p)
        val d0 = doubleArrayOf(1.0, 0.0)
        val d1 = mc.distributionAfterSteps(d0, 1)
        assertEquals(0.7, d1[0], 1e-12)
        assertEquals(0.3, d1[1], 1e-12)
        val p2 = mc.nStepTransitionMatrix(2)
        val manual = arrayOf(
            doubleArrayOf(0.7 * 0.7 + 0.3 * 0.4, 0.7 * 0.3 + 0.3 * 0.6),
            doubleArrayOf(0.4 * 0.7 + 0.6 * 0.4, 0.4 * 0.3 + 0.6 * 0.6),
        )
        for (i in 0..1) {
            for (j in 0..1) {
                assertEquals(manual[i][j], p2[i][j], 1e-10)
            }
        }
    }

    @Test
    fun markovChain_oneHotByLabel() {
        val mc = MarkovChain(
            listOf("x", "y"),
            arrayOf(
                doubleArrayOf(0.5, 0.5),
                doubleArrayOf(0.2, 0.8),
            ),
        )
        val d = mc.distributionAfterSteps("x", 1)
        assertEquals(0.5, d[0], 1e-12)
        assertEquals(0.5, d[1], 1e-12)
    }

    @Test
    fun markovChain_stationarySymmetric() {
        val mc = MarkovChain(
            listOf("1", "2", "3"),
            arrayOf(
                doubleArrayOf(1.0 / 3, 1.0 / 3, 1.0 / 3),
                doubleArrayOf(1.0 / 3, 1.0 / 3, 1.0 / 3),
                doubleArrayOf(1.0 / 3, 1.0 / 3, 1.0 / 3),
            ),
        )
        val pi = mc.stationaryDistribution()
        assertEquals(1.0 / 3, pi[0], 1e-8)
        assertEquals(1.0 / 3, pi[1], 1e-8)
        assertEquals(1.0 / 3, pi[2], 1e-8)
    }

    @Test
    fun markovChain_rejectsNonStochasticRow() {
        assertFailsWith<IllegalArgumentException> {
            MarkovChain(
                listOf("a", "b"),
                arrayOf(
                    doubleArrayOf(0.5, 0.4),
                    doubleArrayOf(0.5, 0.5),
                ),
            )
        }
    }

    @Test
    fun discreteBayesian_medicalStyle() {
        val prior = doubleArrayOf(0.01, 0.99)
        val likelihood = doubleArrayOf(0.95, 0.05)
        val post = DiscreteBayesian.posterior(prior, likelihood)
        val expected0 = 0.01 * 0.95 / (0.01 * 0.95 + 0.99 * 0.05)
        assertEquals(expected0, post[0], 1e-10)
        assertEquals(1.0, post.sum(), 1e-12)
    }

    @Test
    fun discreteBayesian_sequence() {
        val p = DiscreteBayesian.posteriorSequence(
            doubleArrayOf(0.5, 0.5),
            listOf(
                doubleArrayOf(0.9, 0.1),
                doubleArrayOf(0.5, 0.5),
            ),
        )
        assertEquals(1.0, p.sum(), 1e-12)
    }

    @Test
    fun finiteDistribution_entropyAndExpectation() {
        val d = FiniteDistribution.fromMap(mapOf("H" to 0.5, "T" to 0.5))
        assertEquals(1.0, d.entropy(base2 = true), 1e-10)
        assertEquals(ln(2.0), d.entropy(base2 = false), 1e-10)
        val e = d.expectation(mapOf("H" to 1.0, "T" to -1.0))
        assertEquals(0.0, e, 1e-12)
    }

    @Test
    fun finiteDistribution_sampleDeterministic() {
        val d = FiniteDistribution.fromMap(mapOf("only" to 1.0))
        val rng = Random(0)
        repeat(20) {
            assertEquals("only", d.sample(rng))
        }
    }

    @Test
    fun monteCarlo_meanOfUniform() {
        val est = MonteCarlo.estimate(100_000, Random(42)) { 1.0 }
        assertEquals(1.0, est.mean, 1e-9)
        assertTrue(est.variance < 1e-20)
    }

    @Test
    fun monteCarlo_piArea() {
        val rng = Random(123)
        val est = MonteCarlo.estimate(200_000, rng) {
            val x = rng.nextDouble()
            val y = rng.nextDouble()
            if (x * x + y * y <= 1.0) 1.0 else 0.0
        }
        assertTrue(abs(est.mean * 4 - kotlin.math.PI) < 0.02)
    }
}
