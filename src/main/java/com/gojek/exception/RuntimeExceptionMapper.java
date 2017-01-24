package com.gojeck.exception;

import com.gojeck.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by Pratik on 22-01-2017.
 */
@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {
    private static final Logger log = LoggerFactory.getLogger(RuntimeExceptionMapper.class);
    @Override
    public Response toResponse(RuntimeException exception) {
        log.info("Exception Thrown:-"+exception.getMessage());
        exception.printStackTrace();
        return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).entity(new ApiResponse(Response.Status.BAD_REQUEST.getStatusCode(),exception.getMessage())).type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
