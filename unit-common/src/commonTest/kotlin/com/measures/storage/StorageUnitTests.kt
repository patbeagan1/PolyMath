package com.measures.storage

import com.measures.storage.*
import kotlin.test.Test
import kotlin.test.assertEquals

class StorageUnitTests {

    @Test
    fun testBasicByteConversions() {
        // Test basic byte conversions
        val byte = Byte(1.0)
        val kilobyte = Kilobyte(1.0)
        val megabyte = Megabyte(1.0)
        
        // Test that conversions work
        val byteToKb = byte.toKilobyte()
        val kbToByte = kilobyte.toByte()
        val mbToByte = megabyte.toByte()
        
        assertEquals(0.001, byteToKb.value, 0.0001)
        assertEquals(1000.0, kbToByte.value, 0.1)
        assertEquals(1000000.0, mbToByte.value, 0.1)
    }

    @Test
    fun testBinaryStorageConversions() {
        // Test binary storage conversions (1024-based)
        val kibibyte = Kibibyte(1.0)
        val mebibyte = Mebibyte(1.0)
        val gibibyte = Gibibyte(1.0)
        
        // Test that conversions work
        val kibToByte = kibibyte.toByte()
        val mibToByte = mebibyte.toByte()
        val gibToByte = gibibyte.toByte()
        
        assertEquals(1024.0, kibToByte.value, 0.1)
        assertEquals(1024.0 * 1024.0, mibToByte.value, 0.1)
        assertEquals(1024.0 * 1024.0 * 1024.0, gibToByte.value, 0.1)
    }

    @Test
    fun testDecimalStorageConversions() {
        // Test decimal storage conversions (1000-based)
        val kilobyte = Kilobyte(1.0)
        val megabyte = Megabyte(1.0)
        val gigabyte = Gigabyte(1.0)
        
        // Test that conversions work
        val kbToByte = kilobyte.toByte()
        val mbToByte = megabyte.toByte()
        val gbToByte = gigabyte.toByte()
        
        assertEquals(1000.0, kbToByte.value, 0.1)
        assertEquals(1000000.0, mbToByte.value, 0.1)
        assertEquals(1000000000.0, gbToByte.value, 0.1)
    }

    @Test
    fun testBitStorageConversions() {
        // Test bit storage conversions
        val bit = Bit(8.0)
        val kilobit = Kilobit(1.0)
        val megabit = Megabit(1.0)
        
        // Test that conversions work
        val bitToByte = bit.toByte()
        val kbToByte = kilobit.toByte()
        val mbToByte = megabit.toByte()
        
        assertEquals(1.0, bitToByte.value, 0.01)
        assertEquals(125.0, kbToByte.value, 0.1) // 1000 bits / 8 = 125 bytes
        assertEquals(125000.0, mbToByte.value, 0.1) // 1,000,000 bits / 8 = 125,000 bytes
    }

    @Test
    fun testBinaryBitStorageConversions() {
        // Test binary bit storage conversions
        val kibibit = Kibibit(1.0)
        val mebibit = Mebibit(1.0)
        val gibibit = Gibibit(1.0)
        
        // Test that conversions work
        val kibToByte = kibibit.toByte()
        val mibToByte = mebibit.toByte()
        val gibToByte = gibibit.toByte()
        
        assertEquals(128.0, kibToByte.value, 0.1) // 1024 bits / 8 = 128 bytes
        assertEquals(131072.0, mibToByte.value, 0.1) // 1024 * 1024 bits / 8 = 131,072 bytes
        assertEquals(134217728.0, gibToByte.value, 0.1) // 1024^3 bits / 8 = 134,217,728 bytes
    }

    @Test
    fun testStorageUnitArithmetic() {
        // Test storage unit arithmetic
        val byte1 = Byte(100.0)
        val byte2 = Byte(200.0)
        val kilobyte = Kilobyte(1.0)
        
        // Test addition
        val sum = byte1 + byte2
        assertEquals(300.0, sum.value, 0.1)
        
        // Test subtraction
        val diff = byte2 - byte1
        assertEquals(100.0, diff.value, 0.1)
        
        // Test conversion and arithmetic
        val kbToByte = kilobyte.toByte()
        val total = byte1 + kbToByte
        assertEquals(1100.0, total.value, 0.1)
    }

    @Test
    fun testLargeStorageUnits() {
        // Test large storage units
        val terabyte = Terabyte(1.0)
        val petabyte = Petabyte(1.0)
        val exabyte = Exabyte(1.0)
        
        // Test that conversions work
        val tbToByte = terabyte.toByte()
        val pbToByte = petabyte.toByte()
        val ebToByte = exabyte.toByte()
        
        assertEquals(1E12, tbToByte.value, 1E9)
        assertEquals(1E15, pbToByte.value, 1E12)
        assertEquals(1E18, ebToByte.value, 1E15)
    }

    @Test
    fun testSmallStorageUnits() {
        // Test small storage units
        val millibyte = Millibyte(1000.0)
        val microbyte = Microbyte(1000000.0)
        val nanobyte = Nanobyte(1000000000.0)
        
        // Test that conversions work
        val mbToByte = millibyte.toByte()
        val ubToByte = microbyte.toByte()
        val nbToByte = nanobyte.toByte()
        
        assertEquals(1.0, mbToByte.value, 0.01)
        assertEquals(1.0, ubToByte.value, 0.01)
        assertEquals(1.0, nbToByte.value, 0.01)
    }

    @Test
    fun testBinaryVsDecimalDifference() {
        // Test the difference between binary and decimal units
        val kibibyte = Kibibyte(1.0)
        val kilobyte = Kilobyte(1.0)
        
        // Convert both to bytes
        val kibToByte = kibibyte.toByte()
        val kbToByte = kilobyte.toByte()
        
        // Binary should be larger (1024 vs 1000)
        assertEquals(1024.0, kibToByte.value, 0.1)
        assertEquals(1000.0, kbToByte.value, 0.1)
        assertEquals(24.0, kibToByte.value - kbToByte.value, 0.1)
    }

    @Test
    fun testBitToByteConversion() {
        // Test bit to byte conversion
        val bit = Bit(8.0)
        val byte = Byte(1.0)
        
        // 8 bits should equal 1 byte
        val bitToByte = bit.toByte()
        val byteToBit = byte.toBit()
        
        assertEquals(1.0, bitToByte.value, 0.01)
        assertEquals(8.0, byteToBit.value, 0.01)
    }

    @Test
    fun testComprehensiveStorageConversions() {
        // Test comprehensive storage conversions
        val gigabyte = Gigabyte(1.0)
        val gibibyte = Gibibyte(1.0)
        
        // Convert to bytes
        val gbToByte = gigabyte.toByte()
        val gibToByte = gibibyte.toByte()
        
        // Convert to smaller units
        val gbToMb = gigabyte.toMegabyte()
        val gibToMib = gibibyte.toMebibyte()
        
        assertEquals(1E9, gbToByte.value, 1E6)
        assertEquals(1024.0 * 1024.0 * 1024.0, gibToByte.value, 1E6)
        assertEquals(1000.0, gbToMb.value, 0.1)
        assertEquals(1024.0, gibToMib.value, 0.1)
    }
}
