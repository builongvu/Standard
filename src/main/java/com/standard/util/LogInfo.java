package com.standard.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.standard.dto.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.*;
import org.springframework.boot.logging.LogLevel;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogInfo {

    private String serviceName;
    private String serviceVersion;
    private String ipSource; //hostname
    private String ipDest;
    private String url;
    private String level;
    private String method;
    private String logHeader;
    private String requestParam;
    private String requestBody;
    private String responseBody;
    private String responseCode;
    private String responseMessage;
    private String transactionID;
    private Long takeTime;
    private Date dateTime;

    public LogInfo(String serviceName, String serviceVersion, LogLevel level,
                   HttpServletRequest request, ApiResponse apiResponse) {
        ObjectMapper mapper = new ObjectMapper();
        this.serviceName = serviceName;
        this.serviceVersion = serviceVersion;
        this.url = request.getRequestURL().toString();
        this.level = level.name();
        this.method = request.getMethod();
        this.logHeader = CustomLogUtil.getHeadersInfo(request);
        this.requestParam = CustomLogUtil.getRequestParamsInfo(request);
        this.requestBody = CustomLogUtil.getBodyOfRequest(request);
        this.responseBody = CustomLogUtil.writeValueAsString(mapper, apiResponse);
        this.responseCode = String.valueOf(apiResponse.getStatus());
        this.transactionID = String.valueOf(request.getAttribute("requestId"));
        if (request.getAttribute("startTime") != null) {
            this.takeTime = System.currentTimeMillis() - (Long) request.getAttribute("startTime");
        }
        this.dateTime = new Date();
    }

}
