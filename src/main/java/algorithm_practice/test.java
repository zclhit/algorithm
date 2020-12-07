package algorithm_practice;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class test {

  private static final ZoneId DEFAULT_ZONE_ID = ZoneId.of("Asia/Shanghai");
  private static final ZoneId UTC_ZONE_ID = ZoneId.of("UTC");


  public static void main(String[] args) {

    LocalDateTime date = LocalDateTime.now();
    ZonedDateTime zonedDateTime = date.atZone(UTC_ZONE_ID);
    ZonedDateTime defaultZonedDateTime = zonedDateTime.withZoneSameInstant(DEFAULT_ZONE_ID);
    LocalDateTime transDate = defaultZonedDateTime.toLocalDateTime();
    System.out.println(transDate);
  }
}
