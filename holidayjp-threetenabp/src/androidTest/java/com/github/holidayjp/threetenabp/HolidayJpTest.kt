package com.github.holidayjp.threetenabp

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.jakewharton.threetenabp.AndroidThreeTen
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.threeten.bp.*
import kotlin.test.assertFalse
import kotlin.test.assertTrue


/**
 * HolidayJpTest for ThreeTenABP
 * Created by pine on 2018/02/22.
 */

@RunWith(AndroidJUnit4::class)
class HolidayJpTest {
    private val context = InstrumentationRegistry.getTargetContext().applicationContext

    @Before
    fun setup() {
        AndroidThreeTen.init(context)
    }

    @Test
    fun testIsHolidayLocalDate() {
        // should be holiday returns date is holiday or not
        val date1 = LocalDate.of(2011, 9, 19)
        val date2 = LocalDate.of(2011, 9, 18)
        assertTrue(HolidayJp.isHoliday(date1))
        assertFalse(HolidayJp.isHoliday(date2))

        // should be Mountain Day from 2016
        val date3 = LocalDate.of(2015, 8, 11)
        assertFalse(HolidayJp.isHoliday(date3))

        (2016..2050).forEach { i ->
            if (i == 2020) { // Tokyo Olympic
                val date4 = LocalDate.of(i, 8, 10)
                assertTrue(HolidayJp.isHoliday(date4))
            } else if (i == 2021) { // Tokyo Olympic 2021
                val date4 = LocalDate.of(i, 7, 22)
                assertTrue(HolidayJp.isHoliday(date4))
                val date5 = LocalDate.of(i, 7, 23)
                assertTrue(HolidayJp.isHoliday(date5))
                val date6 = LocalDate.of(i, 8, 9)
                assertTrue(HolidayJp.isHoliday(date6))
            } else {
                val date4 = LocalDate.of(i, 8, 11)
                assertTrue(HolidayJp.isHoliday(date4))
            }
        }
    }

    @Test
    fun testIsHolidayLocalDateTime() {
        // should be holiday returns date is holiday or not
        val date1 = LocalDateTime.of(2011, 9, 18, 23, 59, 59, 999999999)
        val date2 = LocalDateTime.of(2011, 9, 19, 0, 0, 0, 0)
        val date3 = LocalDateTime.of(2011, 9, 19, 0, 0, 0, 1)
        val date4 = LocalDateTime.of(2011, 9, 19, 23, 59, 59, 999999999)
        val date5 = LocalDateTime.of(2011, 9, 20, 0, 0, 0, 0)

        assertFalse(HolidayJp.isHoliday(date1))
        assertTrue(HolidayJp.isHoliday(date2))
        assertTrue(HolidayJp.isHoliday(date3))
        assertTrue(HolidayJp.isHoliday(date4))
        assertFalse(HolidayJp.isHoliday(date5))
    }

    @Test
    fun testIsHolidayZonedDateTime() {
        // UTC
        run {
            val zoneId = ZoneId.of("UTC")

            val date1 = ZonedDateTime.of(2011, 9, 18, 23, 59, 59, 999999999, zoneId)
            val date2 = ZonedDateTime.of(2011, 9, 19, 0, 0, 0, 0, zoneId)
            val date3 = ZonedDateTime.of(2011, 9, 19, 0, 0, 0, 1, zoneId)
            val date4 = ZonedDateTime.of(2011, 9, 19, 23, 59, 59, 999999999, zoneId)
            val date5 = ZonedDateTime.of(2011, 9, 20, 0, 0, 0, 0, zoneId)

            assertFalse(HolidayJp.isHoliday(date1))
            assertTrue(HolidayJp.isHoliday(date2))
            assertTrue(HolidayJp.isHoliday(date3))
            assertTrue(HolidayJp.isHoliday(date4))
            assertFalse(HolidayJp.isHoliday(date5))
        }

        // Asia/Tokyo
        run {
            val zoneId = ZoneId.of("Asia/Tokyo")

            val date1 = ZonedDateTime.of(2011, 9, 18, 23, 59, 59, 999999999, zoneId)
            val date2 = ZonedDateTime.of(2011, 9, 19, 0, 0, 0, 0, zoneId)
            val date3 = ZonedDateTime.of(2011, 9, 19, 0, 0, 0, 1, zoneId)
            val date4 = ZonedDateTime.of(2011, 9, 19, 23, 59, 59, 999999999, zoneId)
            val date5 = ZonedDateTime.of(2011, 9, 20, 0, 0, 0, 0, zoneId)

            assertFalse(HolidayJp.isHoliday(date1))
            assertTrue(HolidayJp.isHoliday(date2))
            assertTrue(HolidayJp.isHoliday(date3))
            assertTrue(HolidayJp.isHoliday(date4))
            assertFalse(HolidayJp.isHoliday(date5))
        }
    }

    @Test
    fun testIsHolidayOffsetDateTime() {
        // UTC
        run {
            val zoneOffset = ZoneOffset.UTC

            val date1 = OffsetDateTime.of(2011, 9, 18, 23, 59, 59, 999999999, zoneOffset)
            val date2 = OffsetDateTime.of(2011, 9, 19, 0, 0, 0, 0, zoneOffset)
            val date3 = OffsetDateTime.of(2011, 9, 19, 0, 0, 0, 1, zoneOffset)
            val date4 = OffsetDateTime.of(2011, 9, 19, 23, 59, 59, 999999999, zoneOffset)
            val date5 = OffsetDateTime.of(2011, 9, 20, 0, 0, 0, 0, zoneOffset)

            assertFalse(HolidayJp.isHoliday(date1))
            assertTrue(HolidayJp.isHoliday(date2))
            assertTrue(HolidayJp.isHoliday(date3))
            assertTrue(HolidayJp.isHoliday(date4))
            assertFalse(HolidayJp.isHoliday(date5))
        }

        // Asia/Tokyo
        run {
            val zoneOffset = ZoneOffset.ofHours(9)

            val date1 = OffsetDateTime.of(2011, 9, 18, 23, 59, 59, 999999999, zoneOffset)
            val date2 = OffsetDateTime.of(2011, 9, 19, 0, 0, 0, 0, zoneOffset)
            val date3 = OffsetDateTime.of(2011, 9, 19, 0, 0, 0, 1, zoneOffset)
            val date4 = OffsetDateTime.of(2011, 9, 19, 23, 59, 59, 999999999, zoneOffset)
            val date5 = OffsetDateTime.of(2011, 9, 20, 0, 0, 0, 0, zoneOffset)

            assertFalse(HolidayJp.isHoliday(date1))
            assertTrue(HolidayJp.isHoliday(date2))
            assertTrue(HolidayJp.isHoliday(date3))
            assertTrue(HolidayJp.isHoliday(date4))
            assertFalse(HolidayJp.isHoliday(date5))
        }

    }
}