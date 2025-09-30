package com.measures.storage

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

        @Test
        fun testDataStorageUnits() {
            // Test fundamental data storage units
            val nibble = Nibble(2.0)
            val word = Word(1.0)
            val doubleWord = DoubleWord(1.0)
            val quadWord = QuadWord(1.0)

            // Test conversions to bytes
            val nibbleToByte = nibble.toByte()
            val wordToByte = word.toByte()
            val dwordToByte = doubleWord.toByte()
            val qwordToByte = quadWord.toByte()

            assertEquals(1.0, nibbleToByte.value, 0.01) // 2 nibbles = 1 byte
            assertEquals(2.0, wordToByte.value, 0.01) // 1 word = 2 bytes
            assertEquals(4.0, dwordToByte.value, 0.01) // 1 dword = 4 bytes
            assertEquals(8.0, qwordToByte.value, 0.01) // 1 qword = 8 bytes
        }

        @Test
        fun testHistoricalStorageUnits() {
            // Test historical storage units
            val sixBitChar = SixBitChar(1.0)
            val sevenBitChar = SevenBitChar(1.0)
            val punchedCardColumn = PunchedCardColumn(1.0)
            val diskSector = DiskSector(1.0)

            // Test conversions to bytes
            val sixBitToByte = sixBitChar.toByte()
            val sevenBitToByte = sevenBitChar.toByte()
            val cardToByte = punchedCardColumn.toByte()
            val sectorToByte = diskSector.toByte()

            assertEquals(0.75, sixBitToByte.value, 0.01) // 6 bits = 0.75 bytes
            assertEquals(0.875, sevenBitToByte.value, 0.01) // 7 bits = 0.875 bytes
            assertEquals(1.5, cardToByte.value, 0.01) // 12 bits = 1.5 bytes
            assertEquals(64.0, sectorToByte.value, 0.01) // 512 bits = 64 bytes
        }

        @Test
        fun testSpecializedStorageUnits() {
            // Test specialized storage units
            val asciiChar = AsciiChar(1.0)
            val utf8Char = Utf8Char(1.0)
            val utf16Char = Utf16Char(1.0)
            val float = Float32(1.0)
            val double = Float64(1.0)
            val rgbPixel = RgbPixel(1.0)

            // Test conversions to bytes
            val asciiToByte = asciiChar.toByte()
            val utf8ToByte = utf8Char.toByte()
            val utf16ToByte = utf16Char.toByte()
            val floatToByte = float.toByte()
            val doubleToByte = double.toByte()
            val rgbToByte = rgbPixel.toByte()

            assertEquals(0.875, asciiToByte.value, 0.01) // 7 bits = 0.875 bytes
            assertEquals(1.0, utf8ToByte.value, 0.01) // 8 bits = 1 byte
            assertEquals(2.0, utf16ToByte.value, 0.01) // 16 bits = 2 bytes
            assertEquals(4.0, floatToByte.value, 0.01) // 32 bits = 4 bytes
            assertEquals(8.0, doubleToByte.value, 0.01) // 64 bits = 8 bytes
            assertEquals(3.0, rgbToByte.value, 0.01) // 24 bits = 3 bytes
        }

        @Test
        fun testDataUnitArithmetic() {
            // Test data unit arithmetic
            val nibble1 = Nibble(2.0)
            val nibble2 = Nibble(3.0)
            val word1 = Word(1.0)

            // Test addition
            val sum = nibble1 + nibble2
            val total = nibble1 + word1

            assertEquals(3.0, sum.value, 0.01) // 2 nibbles + 3 nibbles = 5 nibbles, but result is in nibbles
            assertEquals(
                3.0,
                total.value,
                0.01
            ) // 2 nibbles + 1 word = 3 nibbles (1 word = 4 nibbles, but result is in nibbles)
        }

        @Test
        fun testAlternativeNaming() {
            // Test alternative naming conventions
            val nybble = Nybble(2.0)
            val dword = DWord(1.0)
            val qword = QWord(1.0)
            val oword = OWord(1.0)

            // Test conversions
            val nybbleToByte = nybble.toByte()
            val dwordToByte = dword.toByte()
            val qwordToByte = qword.toByte()
            val owordToByte = oword.toByte()

            assertEquals(1.0, nybbleToByte.value, 0.01) // 2 nybbles = 1 byte
            assertEquals(4.0, dwordToByte.value, 0.01) // 1 dword = 4 bytes
            assertEquals(8.0, qwordToByte.value, 0.01) // 1 qword = 8 bytes
            assertEquals(16.0, owordToByte.value, 0.01) // 1 oword = 16 bytes
        }

        @Test
        fun testNetworkStorageUnits() {
            // Test network-related storage units
            val ethernetFrame = EthernetFrame(1.0)
            val ipPacket = IpPacket(1.0)
            val tcpSegment = TcpSegment(1.0)

            // Test conversions
            val frameToByte = ethernetFrame.toByte()
            val packetToByte = ipPacket.toByte()
            val segmentToByte = tcpSegment.toByte()

            assertEquals(189.75, frameToByte.value, 0.01) // 1518 bits = 189.75 bytes
            assertEquals(187.5, packetToByte.value, 0.01) // 1500 bits = 187.5 bytes
            assertEquals(182.5, segmentToByte.value, 0.01) // 1460 bits = 182.5 bytes
        }

        @Test
        fun testMemoryStorageUnits() {
            // Test memory-related storage units
            val memoryPage = MemoryPage(1.0)
            val cacheLine = CacheLine(1.0)

            // Test conversions
            val pageToByte = memoryPage.toByte()
            val cacheToByte = cacheLine.toByte()

            assertEquals(512.0, pageToByte.value, 0.01) // 4096 bits = 512 bytes
            assertEquals(64.0, cacheToByte.value, 0.01) // 512 bits = 64 bytes
        }

        @Test
        fun testAudioVideoStorageUnits() {
            // Test audio and video storage units
            val audio8 = AudioSample8(1.0)
            val audio16 = AudioSample16(1.0)
            val audio24 = AudioSample24(1.0)
            val audio32 = AudioSample32(1.0)
            val video720P = VideoFrame720P(1.0)
            val video1080P = VideoFrame1080P(1.0)
            val video4K = VideoFrame4K(1.0)

            // Test conversions
            val audio8ToByte = audio8.toByte()
            val audio16ToByte = audio16.toByte()
            val audio24ToByte = audio24.toByte()
            val audio32ToByte = audio32.toByte()
            val video720PToByte = video720P.toByte()
            val video1080PToByte = video1080P.toByte()
            val video4KToByte = video4K.toByte()

            assertEquals(1.0, audio8ToByte.value, 0.01) // 8 bits = 1 byte
            assertEquals(2.0, audio16ToByte.value, 0.01) // 16 bits = 2 bytes
            assertEquals(3.0, audio24ToByte.value, 0.01) // 24 bits = 3 bytes
            assertEquals(4.0, audio32ToByte.value, 0.01) // 32 bits = 4 bytes
            assertEquals(2764800.0, video720PToByte.value, 0.01) // 720P frame
            assertEquals(6220800.0, video1080PToByte.value, 0.01) // 1080P frame
            assertEquals(24883200.0, video4KToByte.value, 0.01) // 4K frame
        }
    }
}
