package finances.exception.dto;

public record ErrorResponse(
        int code,
        String error
) {}
