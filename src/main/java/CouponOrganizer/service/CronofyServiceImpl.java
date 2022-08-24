package CouponOrganizer.service;

import cronofy.CronofyAPI;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class CronofyServiceImpl {

    @Value("${calendarId}")
    private String calendarId;

    @Value("${accessToken}")
    private String accessToken;

    @Value("${timeZone}")
    private String timeZone;

    @Value("${productionBaseUrl}")
    private String productionBaseUrl;

    private final String CRONOFY_API = "https://api.cronofy.com/v1";

    public void addEvent(String store, String deal, String comment, Date expirationDate, long id) throws IOException {
        if (expirationDate != null) {
            final CronofyAPI ca = new CronofyAPI(accessToken);
            ca.AddEvent(
                    CRONOFY_API,
                    calendarId,
                    buildEventId(store, id),
                    buildSummary(store),
                    buildDescription(store, deal, comment, id),
                    expirationDate,
                    getEndDate(expirationDate)
            );
        }
    }

    public void deleteEvent(String store, long id) throws IOException {
        final CronofyAPI ca = new CronofyAPI(accessToken);
        ca.DeleteEvent(
             CRONOFY_API,
             calendarId,
             buildEventId(store, id)
        );
    }

    private String buildDescription(String store, String deal, String comment, long id) {
        String description = "Store: " + store + System.lineSeparator() + "Deal: " + deal;
        if (StringUtils.isNotEmpty(comment)) {
            description += System.lineSeparator() + "Comment: " + comment;
        }
        description += System.lineSeparator() + productionBaseUrl + "/get?id=" + id;
        return description;
    }

    private String buildEventId(String store, long id) {
        return store.replaceAll(" ", "") + id;
    }

    private String buildSummary(String store) {
        return store + " coupon expires";
    }

    private Date getEndDate(Date expirationDate) {
        return DateUtils.addDays(expirationDate, 1);
    }
}
