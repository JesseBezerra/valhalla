package br.com.jdsb.valhalla.sql.core.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class LocalDateTimeToDate {

  public static Date localTimeToDate(LocalTime localTime) {
      Calendar calendar = Calendar.getInstance();
      calendar.clear();
      //assuming year/month/date information is not important
      calendar.set(0, 0, 0, localTime.getHour(), localTime.getMinute(), localTime.getSecond());
      return calendar.getTime();
  }

  public static Date localDateToDate(LocalDate localDate) {
      Calendar calendar = Calendar.getInstance();
      calendar.clear();
      //assuming start of day
      calendar.set(localDate.getYear(), localDate.getMonthValue()-1, localDate.getDayOfMonth());
      return calendar.getTime();
  }

  public static Date localDateTimeToDate(LocalDateTime localDateTime) {
      Calendar calendar = Calendar.getInstance();
      calendar.clear();
      calendar.set(localDateTime.getYear(), localDateTime.getMonthValue()-1, localDateTime.getDayOfMonth(),
              localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond());
      return calendar.getTime();
  }


}