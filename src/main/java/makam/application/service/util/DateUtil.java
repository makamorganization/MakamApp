package makam.application.service.util;

import java.util.Date;

public class DateUtil {
    public static Date timestampToDate(Long timestamp) {
        Date date = new Date(timestamp);
        return date;
    }
}
