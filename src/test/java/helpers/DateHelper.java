package helpers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Хелпер для работы с датами и их преобразованиями
 */
public class DateHelper {

    /**
     * Преобразование даты из Timestamp в LocalDateTime
     *
     * @param timestampDate дата в формате int приходящая с бека
     * @return дата в вормате LocalDateTime
     */
    private static LocalDateTime parseTimeStampToLocalDateTime(int timestampDate) {
        return LocalDateTime.ofInstant(
                Instant.ofEpochSecond(timestampDate),
                ZoneId.of("GMT+00:00")
                //ZoneId.systemDefault()
        );
    }

    public static String transformDateToSearchResultMask(int timestampDate) {
        return transformDateToSearchResultMask(parseTimeStampToLocalDateTime(timestampDate));
    }

    /**
     * Преобразование даты к формату представлену в окне поиска
     *
     * @param localDateTime дата в формате LocalDateTime
     * @return дата в формате отображаемая на экране поиска
     */
    public static String transformDateToSearchResultMask(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy (HH:mm)");
        return localDateTime.format(formatter);
    }
}
