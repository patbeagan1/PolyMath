package com.measures.storage

import com.measures.storage.*
import kotlin.test.Test

/**
 * Demonstration of comprehensive storage units in the unit-common module.
 * This shows how to use all the storage types including binary, decimal, and bit-based units.
 */
class StorageDemo {
    @Test
    fun main() {
        println("=== Storage Units Demonstration ===\n")

        // Basic byte operations
        println("1. Basic Byte Operations:")
        val byte = Byte(1024.0)
        val kilobyte = byte.toKilobyte()
        val kibibyte = byte.toKibibyte()
        println("1024 bytes = ${kilobyte.value} KB (decimal)")
        println("1024 bytes = ${kibibyte.value} KiB (binary)")
        println()

        // Decimal storage units (1000-based)
        println("2. Decimal Storage Units (1000-based):")
        val megabyte = Megabyte(1.0)
        val gigabyte = Gigabyte(1.0)
        val terabyte = Terabyte(1.0)

        println("1 MB = ${megabyte.toByte().value} bytes")
        println("1 GB = ${gigabyte.toByte().value} bytes")
        println("1 TB = ${terabyte.toByte().value} bytes")
        println()

        // Binary storage units (1024-based)
        println("3. Binary Storage Units (1024-based):")
        val mebibyte = Mebibyte(1.0)
        val gibibyte = Gibibyte(1.0)
        val tebibyte = Tebibyte(1.0)

        println("1 MiB = ${mebibyte.toByte().value} bytes")
        println("1 GiB = ${gibibyte.toByte().value} bytes")
        println("1 TiB = ${tebibyte.toByte().value} bytes")
        println()

        // Bit-based storage units
        println("4. Bit-based Storage Units:")
        val bit = Bit(8.0)
        val kilobit = Kilobit(1.0)
        val megabit = Megabit(1.0)

        println("8 bits = ${bit.toByte().value} bytes")
        println("1 Kb = ${kilobit.toByte().value} bytes")
        println("1 Mb = ${megabit.toByte().value} bytes")
        println()

        // Binary bit units
        println("5. Binary Bit Units:")
        val kibibit = Kibibit(1.0)
        val mebibit = Mebibit(1.0)

        println("1 Kib = ${kibibit.toByte().value} bytes")
        println("1 Mib = ${mebibit.toByte().value} bytes")
        println()

        // Storage unit arithmetic
        println("6. Storage Unit Arithmetic:")
        val byte1 = Byte(100.0)
        val byte2 = Byte(200.0)
        val sum = byte1 + byte2
        val diff = byte2 - byte1

        println("100 bytes + 200 bytes = ${sum.value} bytes")
        println("200 bytes - 100 bytes = ${diff.value} bytes")
        println()

        // Conversion between different unit systems
        println("7. Cross-System Conversions:")
        val decimalGB = Gigabyte(1.0)
        val binaryGB = Gibibyte(1.0)

        println("1 GB (decimal) = ${decimalGB.toByte().value} bytes")
        println("1 GiB (binary) = ${binaryGB.toByte().value} bytes")
        println("Difference: ${binaryGB.toByte().value - decimalGB.toByte().value} bytes")
        println()

        // Large storage units
        println("8. Large Storage Units:")
        val petabyte = Petabyte(1.0)
        val exabyte = Exabyte(1.0)
        val zettabyte = Zettabyte(1.0)
        val yottabyte = Yottabyte(1.0)

        println("1 PB = ${petabyte.toByte().value} bytes")
        println("1 EB = ${exabyte.toByte().value} bytes")
        println("1 ZB = ${zettabyte.toByte().value} bytes")
        println("1 YB = ${yottabyte.toByte().value} bytes")
        println()

        // Small storage units
        println("9. Small Storage Units:")
        val millibyte = Millibyte(1000.0)
        val microbyte = Microbyte(1000000.0)
        val nanobyte = Nanobyte(1000000000.0)

        println("1000 mB = ${millibyte.toByte().value} bytes")
        println("1,000,000 μB = ${microbyte.toByte().value} bytes")
        println("1,000,000,000 nB = ${nanobyte.toByte().value} bytes")
        println()

        println("=== Storage Units Demonstration Complete ===")
    }
}