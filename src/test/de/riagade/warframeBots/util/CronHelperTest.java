package de.riagade.warframeBots.util;

import lombok.Getter;
import lombok.Setter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@RunWith(JUnit4.class)
class CronHelperTest extends Assert {
    private Calendar referenceCalendar;

    @BeforeEach
    void init(){
        setReferenceCalendar(Calendar.getInstance());
        getReferenceCalendar().set(Calendar.MILLISECOND, 0);
        getReferenceCalendar().set(Calendar.SECOND, 0);
        getReferenceCalendar().set(Calendar.MINUTE, 0);
        getReferenceCalendar().set(Calendar.HOUR, 10);
        getReferenceCalendar().set(Calendar.DATE, 20);
        getReferenceCalendar().set(Calendar.MONTH, 9);
        getReferenceCalendar().set(Calendar.YEAR, 2020);
    }

    @Test
    void getNextDateAfter() {
        Calendar expectedCalendar = Calendar.getInstance();
        expectedCalendar.set(Calendar.MILLISECOND, 0);
        expectedCalendar.set(Calendar.SECOND, 0);
        expectedCalendar.set(Calendar.MINUTE, 0);
        expectedCalendar.set(Calendar.HOUR_OF_DAY, 10);
        expectedCalendar.set(Calendar.DATE, 26);
        expectedCalendar.set(Calendar.MONTH, 9);
        expectedCalendar.set(Calendar.YEAR, 2020);

        Date resultDate = CronHelper.getNextDateAfter(getReferenceCalendar().getTime(), "0 0 10 ? * MON *");

        assertEquals(expectedCalendar.getTime(), resultDate);
    }
}