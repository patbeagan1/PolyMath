package dev.patbeagan.signal

import com.measures.frequency.Hertz
import com.measures.time.Second
import dev.patbeagan.signal.domain.DiscreteSignal
import dev.patbeagan.signal.domain.FrequencyBinIndex
import dev.patbeagan.signal.domain.SampleCount
import dev.patbeagan.signal.domain.SampleIndex
import dev.patbeagan.signal.domain.SamplingSchedule
import dev.patbeagan.signal.processing.CrossCorrelation
import dev.patbeagan.signal.processing.DiscreteConvolution
import dev.patbeagan.signal.processing.EnergyAndRms
import dev.patbeagan.signal.processing.FourierTransform
import dev.patbeagan.signal.processing.MovingAverage
import dev.patbeagan.signal.processing.Windows
import kotlin.math.abs
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class SignalProcessingTest {

    @Test
    fun convolutionFull_matchesManualExample() {
        val a = doubleArrayOf(1.0, 2.0, 3.0)
        val b = doubleArrayOf(0.0, 1.0, 0.5)
        val y = DiscreteConvolution.full(a, b)
        assertEquals(5, y.size)
        assertClose(0.0, y[0])
        assertClose(1.0, y[1])
        assertClose(2.5, y[2])
        assertClose(4.0, y[3])
        assertClose(1.5, y[4])
    }

    @Test
    fun typedConvolution_requiresSameSchedule() {
        val s1 = DiscreteSignal.of(doubleArrayOf(1.0, 1.0), 1000.0)
        val s2 = DiscreteSignal.of(doubleArrayOf(1.0, 1.0), 2000.0)
        assertFailsWith<IllegalArgumentException> {
            DiscreteConvolution.of(s1, s2)
        }
    }

    @Test
    fun crossCorrelation_peakAlignsWithImpulse() {
        val x = doubleArrayOf(0.0, 0.0, 1.0, 0.0, 0.0)
        val y = doubleArrayOf(0.0, 1.0, 0.0)
        val r = CrossCorrelation.full(x, y)
        val maxIdx = r.indices.maxBy { r[it] }
        assertEquals(3, maxIdx)
    }

    @Test
    fun movingAverage_smoothsStep() {
        val s = DiscreteSignal.of(doubleArrayOf(0.0, 0.0, 0.0, 10.0, 10.0, 10.0), 1.0)
        val out = MovingAverage.of(s, SampleCount(3))
        assertEquals(s.samples.size, out.samples.size)
        assertClose(10.0 / 3.0, out.samples[3])
        assertClose(10.0, out.samples[5])
    }

    @Test
    fun dft_idft_roundTrip() {
        val raw = doubleArrayOf(1.0, -1.0, 1.0, -1.0)
        val sig = DiscreteSignal.of(raw, 4.0)
        val spec = FourierTransform.discreteFourierTransform(sig)
        val back = FourierTransform.inverseDiscreteFourierTransform(spec)
        for (i in raw.indices) {
            assertClose(raw[i], back[i])
        }
    }

    @Test
    fun fft_matchesDft_onPowerOfTwo() {
        val rnd = Random(42)
        val n = 64
        val raw = DoubleArray(n) { rnd.nextDouble(-1.0, 1.0) }
        val sig = DiscreteSignal.of(raw, 10_000.0)
        val dft = FourierTransform.discreteFourierTransform(sig)
        val fft = FourierTransform.fastFourierTransform(sig)
        for (k in 0 until n) {
            assertClose(dft.coefficients[k].real, fft.coefficients[k].real, 1e-9)
            assertClose(dft.coefficients[k].imaginary, fft.coefficients[k].imaginary, 1e-9)
        }
    }

    @Test
    fun zeroPad_thenFft_sameAsDirectFft_whenAlreadyPowerOfTwo() {
        val raw = DoubleArray(8) { it.toDouble() }
        val sig = DiscreteSignal.of(raw, 1.0)
        val padded = FourierTransform.zeroPadToNextPowerOfTwo(sig)
        assertTrue(padded.samples.contentEquals(raw))
    }

    @Test
    fun zeroPad_expandsToNextPowerOfTwo() {
        val sig = DiscreteSignal.of(doubleArrayOf(1.0, 2.0, 3.0), 1.0)
        val padded = FourierTransform.zeroPadToNextPowerOfTwo(sig)
        assertEquals(4, padded.samples.size)
        assertClose(1.0, padded.samples[0])
        assertClose(0.0, padded.samples[3])
    }

    @Test
    fun samplingSchedule_binCenterFrequency() {
        val sched = SamplingSchedule(Hertz(8000.0))
        val f = sched.binCenterFrequency(FrequencyBinIndex(1), transformSize = 8)
        assertClose(1000.0, f.value)
    }

    @Test
    fun samplingSchedule_samplePeriod_inverseOfRate() {
        val sched = SamplingSchedule(Hertz(100.0))
        assertClose(0.01, sched.samplePeriod.value)
        val t = sched.timeAt(SampleIndex(2))
        assertClose(0.02, t.value)
    }

    @Test
    fun hannWindow_endpoints() {
        val w = Windows.hann(SampleCount(5))
        assertClose(0.0, w.first())
        assertClose(0.0, w.last())
    }

    @Test
    fun rms_ofConstant() {
        val s = DiscreteSignal.of(doubleArrayOf(3.0, 3.0, 3.0), 1.0)
        assertClose(3.0, EnergyAndRms.rootMeanSquare(s))
    }

    @Test
    fun energy_ofUnitPulse() {
        val e = EnergyAndRms.totalEnergy(doubleArrayOf(1.0, 0.0, 0.0))
        assertClose(1.0, e)
    }

    @Test
    fun schedule_fromPeriod() {
        val s = SamplingSchedule.fromPeriod(Second(0.002))
        assertClose(500.0, s.sampleRate.value)
    }

    private fun assertClose(expected: Double, actual: Double, eps: Double = 1e-10) {
        assertTrue(abs(expected - actual) < eps, "expected $expected, got $actual")
    }
}
