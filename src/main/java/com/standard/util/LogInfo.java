package com.standard.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.standard.dto.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.*;
import org.springframework.boot.logging.LogLevel;

import java.time.LocalDateTime;

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
    private String method;
    private String url;
    private String level;
    private String logHeader;
    private String requestParam;
    private String requestBody;
    private String responseBody;
    private String responseCode;
    private String responseMessage;
    private String transactionID;
    private String exceptionType;
    private String errorMessage;
    private Long takeTime;
    private LocalDateTime dateTime;

    public LogInfo(String serviceName, String serviceVersion, LogLevel level,
                   HttpServletRequest request, ApiResponse apiResponse, Exception ex) {
        ObjectMapper mapper = new ObjectMapper();
        this.serviceName = serviceName;
        this.serviceVersion = serviceVersion;
        this.method = request.getMethod();
        this.url = request.getRequestURL().toString();
        this.level = level.name();
        this.logHeader = CustomLogUtil.getHeadersInfo(request);
        this.requestParam = CustomLogUtil.getRequestParamsInfo(request);
        this.requestBody = CustomLogUtil.getBodyOfRequest(request);
        this.responseBody = CustomLogUtil.writeValueAsString(mapper, apiResponse);
        this.responseCode = String.valueOf(apiResponse.getStatus());
        this.transactionID = String.valueOf(request.getAttribute("requestId"));
        this.exceptionType = ex.getClass().getSimpleName();
        this.errorMessage = ex.getMessage();
        if (request.getAttribute("startTime") != null) {
            this.takeTime = System.currentTimeMillis() - (Long) request.getAttribute("startTime");
        }
        this.dateTime = LocalDateTime.now();
    }

    public LogInfo(String serviceName, String serviceVersion, LogLevel level,
                   HttpServletRequest request, ApiResponse apiResponse) {
        ObjectMapper mapper = new ObjectMapper();
        this.serviceName = serviceName;
        this.serviceVersion = serviceVersion;
        this.method = request.getMethod();
        this.url = request.getRequestURL().toString();
        this.level = level.name();
        this.logHeader = CustomLogUtil.getHeadersInfo(request);
        this.requestParam = CustomLogUtil.getRequestParamsInfo(request);
        this.requestBody = CustomLogUtil.getBodyOfRequest(request);
        this.responseBody = CustomLogUtil.writeValueAsString(mapper, apiResponse);
        this.responseCode = String.valueOf(apiResponse.getStatus());
        this.transactionID = String.valueOf(request.getAttribute("requestId"));
        if (request.getAttribute("startTime") != null) {
            this.takeTime = System.currentTimeMillis() - (Long) request.getAttribute("startTime");
        }
        this.dateTime = LocalDateTime.now();
    }

}
