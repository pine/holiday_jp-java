package com.github.holidayjp.joda

import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Test for HolidayJp
 * Created by pine on 2018/02/24.
 */

class HolidayJpTest {
    @Test
    fun testIsHolidayLocalDate() {
        // should be holiday returns date is holiday or not
        val date1 = LocalDate(2011, 9, 19)
        val date2 = LocalDate(2011, 9, 18)
        assertTrue(HolidayJp.isHoliday(date1))
        assertFalse(HolidayJp.isHoliday(date2))

        // should be Mountain Day from 2016
        val date3 = LocalDate(2015, 8, 11)
        assertFalse(HolidayJp.isHoliday(date3))

        (2016..2050).forEach { i ->
            if (i == 2020) { // Tokyo Olympic
                val date4 = LocalDate(i, 8, 10)
                assertTrue(HolidayJp.isHoliday(date4))
            } else if (i == 2021) { // Tokyo Olympic 2021
                val date4 = LocalDate(i, 7, 22)
                assertTrue(HolidayJp.isHoliday(date4))
                val date5 = LocalDate(i, 7, 23)
                assertTrue(HolidayJp.isHoliday(date5))
                val date6 = LocalDate(i, 8, 9)
                assertTrue(HolidayJp.isHoliday(date6))
            } else {
                val date4 = LocalDate(i, 8, 11)
                assertTrue(HolidayJp.isHoliday(date4))
            }
        }
    }

    @Test
    fun testIsHolidayLocalDateTime() {
        // should be holiday returns date is holiday or not
        val date1 = LocalDateTime(2011, 9, 18, 23, 59, 59, 999)
        val date2 = LocalDateTime(2011, 9, 19, 0, 0, 0, 0)
        val date3 = LocalDateTime(2011, 9, 19, 0, 0, 0, 1)
        val date4 = LocalDateTime(2011, 9, 19, 23, 59, 59, 999)
        val date5 = LocalDateTime(2011, 9, 20, 0, 0, 0, 0)

        assertFalse(HolidayJp.isHoliday(date1))
        assertTrue(HolidayJp.isHoliday(date2))
        assertTrue(HolidayJp.isHoliday(date3))
        assertTrue(HolidayJp.isHoliday(date4))
        assertFalse(HolidayJp.isHoliday(date5))
    }

    @Test
    fun testIsHolidayDateTime() {
        // UTC
        run {
            val zoneId = DateTimeZone.forID("UTC")

            val date1 = DateTime(2011, 9, 18, 23, 59, 59, 999, zoneId)
            val date2 = DateTime(2011, 9, 19, 0, 0, 0, 0, zoneId)
            val date3 = DateTime(2011, 9, 19, 0, 0, 0, 1, zoneId)
            val date4 = DateTime(2011, 9, 19, 23, 59, 59, 999, zoneId)
            val date5 = DateTime(2011, 9, 20, 0, 0, 0, 0, zoneId)

            assertFalse(HolidayJp.isHoliday(date1))
            assertTrue(HolidayJp.isHoliday(date2))
            assertTrue(HolidayJp.isHoliday(date3))
            assertTrue(HolidayJp.isHoliday(date4))
            assertFalse(HolidayJp.isHoliday(date5))
        }

        // Asia/Tokyo
        run {
            val zoneId = DateTimeZone.forID("Asia/Tokyo")

            val date1 = DateTime(2011, 9, 18, 23, 59, 59, 999, zoneId)
            val date2 = DateTime(2011, 9, 19, 0, 0, 0, 0, zoneId)
            val date3 = DateTime(2011, 9, 19, 0, 0, 0, 1, zoneId)
            val date4 = DateTime(2011, 9, 19, 23, 59, 59, 999, zoneId)
            val date5 = DateTime(2011, 9, 20, 0, 0, 0, 0, zoneId)

            assertFalse(HolidayJp.isHoliday(date1))
            assertTrue(HolidayJp.isHoliday(date2))
            assertTrue(HolidayJp.isHoliday(date3))
            assertTrue(HolidayJp.isHoliday(date4))
            assertFalse(HolidayJp.isHoliday(date5))
        }
    }
}