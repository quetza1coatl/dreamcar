package com.dreamcar.auctionplatform.exceptions;

import lombok.Getter;

@Getter
public class ErrorInfo {
    private final String url;
    private final String cause;
    private final String [] details;

    public ErrorInfo(CharSequence url,  Throwable ex) {
        this.url = url.toString();
        this.cause = ex.getClass().getSimpleName();
        details = new String[]{ex.getLocalizedMessage()};
    }


    public ErrorInfo(CharSequence url,  Throwable ex, String ... details) {
        this.url = url.toString();
        this.cause = ex.getClass().getSimpleName();
        this.details = details;
    }


}
