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

        // Data storage units
        println("10. Data Storage Units:")
        val nibble = Nibble(2.0)
        val word = Word(1.0)
        val doubleWord = DoubleWord(1.0)
        val quadWord = QuadWord(1.0)
        
        println("2 nibbles = ${nibble.toByte().value} bytes")
        println("1 word = ${word.toByte().value} bytes")
        println("1 double word = ${doubleWord.toByte().value} bytes")
        println("1 quad word = ${quadWord.toByte().value} bytes")
        println()
        
        // Historical storage units
        println("11. Historical Storage Units:")
        val sixBitChar = SixBitChar(1.0)
        val sevenBitChar = SevenBitChar(1.0)
        val punchedCardColumn = PunchedCardColumn(1.0)
        val diskSector = DiskSector(1.0)
        
        println("1 six-bit char = ${sixBitChar.toByte().value} bytes")
        println("1 seven-bit char = ${sevenBitChar.toByte().value} bytes")
        println("1 punched card column = ${punchedCardColumn.toByte().value} bytes")
        println("1 disk sector = ${diskSector.toByte().value} bytes")
        println()
        
        // Specialized storage units
        println("12. Specialized Storage Units:")
        val asciiChar = AsciiChar(1.0)
        val utf8Char = Utf8Char(1.0)
        val utf16Char = Utf16Char(1.0)
        val float = Float32(1.0)
        val double = Float64(1.0)
        val rgbPixel = RgbPixel(1.0)
        
        println("1 ASCII char = ${asciiChar.toByte().value} bytes")
        println("1 UTF-8 char = ${utf8Char.toByte().value} bytes")
        println("1 UTF-16 char = ${utf16Char.toByte().value} bytes")
        println("1 float = ${float.toByte().value} bytes")
        println("1 double = ${double.toByte().value} bytes")
        println("1 RGB pixel = ${rgbPixel.toByte().value} bytes")
        println()
        
        // Network storage units
        println("13. Network Storage Units:")
        val ethernetFrame = EthernetFrame(1.0)
        val ipPacket = IpPacket(1.0)
        val tcpSegment = TcpSegment(1.0)
        
        println("1 Ethernet frame = ${ethernetFrame.toByte().value} bytes")
        println("1 IP packet = ${ipPacket.toByte().value} bytes")
        println("1 TCP segment = ${tcpSegment.toByte().value} bytes")
        println()
        
        // Memory storage units
        println("14. Memory Storage Units:")
        val memoryPage = MemoryPage(1.0)
        val cacheLine = CacheLine(1.0)
        
        println("1 memory page = ${memoryPage.toByte().value} bytes")
        println("1 cache line = ${cacheLine.toByte().value} bytes")
        println()
        
        // Audio/Video storage units
        println("15. Audio/Video Storage Units:")
        val audio8 = AudioSample8(1.0)
        val audio16 = AudioSample16(1.0)
        val video720P = VideoFrame720P(1.0)
        val video1080P = VideoFrame1080P(1.0)
        val video4K = VideoFrame4K(1.0)
        
        println("1 8-bit audio sample = ${audio8.toByte().value} bytes")
        println("1 16-bit audio sample = ${audio16.toByte().value} bytes")
        println("1 720P video frame = ${video720P.toByte().value} bytes")
        println("1 1080P video frame = ${video1080P.toByte().value} bytes")
        println("1 4K video frame = ${video4K.toByte().value} bytes")
        println()
        
        println("=== Comprehensive Storage Units Demonstration Complete ===")
    }
}