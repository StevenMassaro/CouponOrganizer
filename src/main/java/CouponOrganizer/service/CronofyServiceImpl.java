package CouponOrganizer.service;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.biacode.jcronofy.api.client.CronofyClient;
import org.biacode.jcronofy.api.client.impl.CronofyClientImpl;
import org.biacode.jcronofy.api.model.common.CronofyResponse;
import org.biacode.jcronofy.api.model.request.CreateOrUpdateEventRequest;
import org.biacode.jcronofy.api.model.response.CreateOrUpdateEventResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.ClientBuilder;
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

    public void addEvent(String store, String deal, String comment, Date expirationDate, long id) {
        if (expirationDate != null) {
            final CronofyClient cronofyClient = new CronofyClientImpl(ClientBuilder.newBuilder().register(JacksonJsonProvider.class).build());
            CreateOrUpdateEventRequest createOrUpdateEventRequest = new CreateOrUpdateEventRequest();
            createOrUpdateEventRequest.setCalendarId(calendarId);
            createOrUpdateEventRequest.setAccessToken(accessToken);
            createOrUpdateEventRequest.setDescription(buildDescription(store, deal, comment, id));
            createOrUpdateEventRequest.setEventId(buildEventId(store, id));
            createOrUpdateEventRequest.setStart(expirationDate);
            createOrUpdateEventRequest.setEnd(getEndDate(expirationDate));
            createOrUpdateEventRequest.setSummary(buildSummary(store));
            createOrUpdateEventRequest.setTzid(timeZone);
            CronofyResponse<CreateOrUpdateEventResponse> createOrUpdateEventResponse = cronofyClient.createOrUpdateEvent(createOrUpdateEventRequest);
        }
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
