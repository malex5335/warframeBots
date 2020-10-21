package de.riagade.warframeBots.util;

import lombok.experimental.UtilityClass;
import org.quartz.CronExpression;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

@UtilityClass
public class CronHelper {

    /**
     * calculates the next possible Date for a cron expression
     *
     * @param cronExpression the cron expression to check for
     * @return the next Date to run according to the cron expression or new Date(0) if something goes wrong
     */
    public static Date getNextDate(String cronExpression) {
        return getNextDateAfter(Calendar.getInstance().getTime(), cronExpression);
    }

    /**
     * calculates the next possible Date for a cron expression
     *
     * @param date the Date to check the next possible Date after
     * @param cronExpression the cron expression to check for
     * @return the next Date to run according to the cron expression or new Date(0) if something goes wrong
     */
    public static Date getNextDateAfter(Date date, String cronExpression) {
        try{
            CronExpression expression = new CronExpression(cronExpression);
            return expression.getNextValidTimeAfter(date);
        } catch (ParseException e){
            e.printStackTrace();
            return new Date(0);
        }
    }

    /**
     * calculates the next possible Date for a cron expression
     *
     * @param cronExpression the cron expression to check for
     * @param moduloNumber the number of n in 'each n week'
     * @param revertExpectation true if the result should ne every every week but the expected one
     * @return the next Date to run according to the cron expression or new Date(0) if something goes wrong
     */
    public static Date calculatePartWeekly(String cronExpression, int moduloNumber, boolean revertExpectation) {
        Calendar referenceDate = Calendar.getInstance();
        if(revertExpectation){
            while(referenceDate.get(Calendar.WEEK_OF_YEAR) % moduloNumber == 0) {
                referenceDate.add(Calendar.DATE, 1);
            }
        } else {
            while(referenceDate.get(Calendar.WEEK_OF_YEAR) % moduloNumber > 0) {
                referenceDate.add(Calendar.DATE, 1);
            }
        }
        return CronHelper.getNextDateAfter(referenceDate.getTime(), cronExpression);
    }
}
