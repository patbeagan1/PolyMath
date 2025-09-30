package com.measures.storage

import kotlin.jvm.JvmInline

// Historical and Specialized Storage Units
// These represent historical and specialized data storage units used in computing

// Historical unit conversion constants
object HistoricalConsts {
    // Historical byte sizes (before standardization)
    const val BITS_PER_SIX_BIT_CHAR = 6.0
    const val BITS_PER_SEVEN_BIT_CHAR = 7.0
    const val BITS_PER_NINE_BIT_CHAR = 9.0
    
    // Punched card units
    const val BITS_PER_PUNCHED_CARD_COLUMN = 12.0
    const val BITS_PER_PUNCHED_CARD_ROW = 80.0
    
    // Magnetic tape units
    const val BITS_PER_TAPE_FRAME = 9.0
    const val BITS_PER_TAPE_BLOCK = 512.0
    
    // Disk sector units
    const val BITS_PER_DISK_SECTOR = 512.0
    const val BITS_PER_DISK_CLUSTER = 4096.0
    
    // Network packet units
    const val BITS_PER_ETHERNET_FRAME = 1518.0
    const val BITS_PER_IP_PACKET = 1500.0
    const val BITS_PER_TCP_SEGMENT = 1460.0
    
    // Memory page units
    const val BITS_PER_MEMORY_PAGE = 4096.0
    const val BITS_PER_MEMORY_CACHE_LINE = 512.0
}

// Historical storage unit conversion functions
fun UnitStorage<*>.toSixBitChar() = toUnit(SixBitChar(1.0))
fun UnitStorage<*>.toSevenBitChar() = toUnit(SevenBitChar(1.0))
fun UnitStorage<*>.toNineBitChar() = toUnit(NineBitChar(1.0))
fun UnitStorage<*>.toPunchedCardColumn() = toUnit(PunchedCardColumn(1.0))
fun UnitStorage<*>.toPunchedCardRow() = toUnit(PunchedCardRow(1.0))
fun UnitStorage<*>.toTapeFrame() = toUnit(TapeFrame(1.0))
fun UnitStorage<*>.toTapeBlock() = toUnit(TapeBlock(1.0))
fun UnitStorage<*>.toDiskSector() = toUnit(DiskSector(1.0))
fun UnitStorage<*>.toDiskCluster() = toUnit(DiskCluster(1.0))
fun UnitStorage<*>.toEthernetFrame() = toUnit(EthernetFrame(1.0))
fun UnitStorage<*>.toIpPacket() = toUnit(IpPacket(1.0))
fun UnitStorage<*>.toTcpSegment() = toUnit(TcpSegment(1.0))
fun UnitStorage<*>.toMemoryPage() = toUnit(MemoryPage(1.0))
fun UnitStorage<*>.toCacheLine() = toUnit(CacheLine(1.0))

@JvmInline
value class SixBitChar(override val value: Double) : UnitStorage<SixBitChar> {
    override fun asType(d: Double) = SixBitChar(d)
    override fun asBaseUnit() = Byte(value * HistoricalConsts.BITS_PER_SIX_BIT_CHAR / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class SevenBitChar(override val value: Double) : UnitStorage<SevenBitChar> {
    override fun asType(d: Double) = SevenBitChar(d)
    override fun asBaseUnit() = Byte(value * HistoricalConsts.BITS_PER_SEVEN_BIT_CHAR / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class NineBitChar(override val value: Double) : UnitStorage<NineBitChar> {
    override fun asType(d: Double) = NineBitChar(d)
    override fun asBaseUnit() = Byte(value * HistoricalConsts.BITS_PER_NINE_BIT_CHAR / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class PunchedCardColumn(override val value: Double) : UnitStorage<PunchedCardColumn> {
    override fun asType(d: Double) = PunchedCardColumn(d)
    override fun asBaseUnit() = Byte(value * HistoricalConsts.BITS_PER_PUNCHED_CARD_COLUMN / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class PunchedCardRow(override val value: Double) : UnitStorage<PunchedCardRow> {
    override fun asType(d: Double) = PunchedCardRow(d)
    override fun asBaseUnit() = Byte(value * HistoricalConsts.BITS_PER_PUNCHED_CARD_ROW / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class TapeFrame(override val value: Double) : UnitStorage<TapeFrame> {
    override fun asType(d: Double) = TapeFrame(d)
    override fun asBaseUnit() = Byte(value * HistoricalConsts.BITS_PER_TAPE_FRAME / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class TapeBlock(override val value: Double) : UnitStorage<TapeBlock> {
    override fun asType(d: Double) = TapeBlock(d)
    override fun asBaseUnit() = Byte(value * HistoricalConsts.BITS_PER_TAPE_BLOCK / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class DiskSector(override val value: Double) : UnitStorage<DiskSector> {
    override fun asType(d: Double) = DiskSector(d)
    override fun asBaseUnit() = Byte(value * HistoricalConsts.BITS_PER_DISK_SECTOR / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class DiskCluster(override val value: Double) : UnitStorage<DiskCluster> {
    override fun asType(d: Double) = DiskCluster(d)
    override fun asBaseUnit() = Byte(value * HistoricalConsts.BITS_PER_DISK_CLUSTER / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class EthernetFrame(override val value: Double) : UnitStorage<EthernetFrame> {
    override fun asType(d: Double) = EthernetFrame(d)
    override fun asBaseUnit() = Byte(value * HistoricalConsts.BITS_PER_ETHERNET_FRAME / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class IpPacket(override val value: Double) : UnitStorage<IpPacket> {
    override fun asType(d: Double) = IpPacket(d)
    override fun asBaseUnit() = Byte(value * HistoricalConsts.BITS_PER_IP_PACKET / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class TcpSegment(override val value: Double) : UnitStorage<TcpSegment> {
    override fun asType(d: Double) = TcpSegment(d)
    override fun asBaseUnit() = Byte(value * HistoricalConsts.BITS_PER_TCP_SEGMENT / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class MemoryPage(override val value: Double) : UnitStorage<MemoryPage> {
    override fun asType(d: Double) = MemoryPage(d)
    override fun asBaseUnit() = Byte(value * HistoricalConsts.BITS_PER_MEMORY_PAGE / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class CacheLine(override val value: Double) : UnitStorage<CacheLine> {
    override fun asType(d: Double) = CacheLine(d)
    override fun asBaseUnit() = Byte(value * HistoricalConsts.BITS_PER_MEMORY_CACHE_LINE / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}
