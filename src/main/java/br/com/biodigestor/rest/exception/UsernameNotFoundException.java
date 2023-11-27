package br.com.biodigestor.rest.exception;

import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

public class UsernameNotFoundException extends NestedRuntimeException {
    private final int status;
    @Nullable
    private final String reason;

    public UsernameNotFoundException(HttpStatus status, @Nullable String reason) {
        super("");
        Assert.notNull(status, "HttpStatus is required");
        this.status = status.value();
        this.reason = reason;
    }

    @Nullable
    public String getReason() {
        return this.reason;
    }

    public HttpStatus getStatus() {
        return HttpStatus.valueOf(this.status);
    }
}
