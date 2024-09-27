package com.nisum.user.app.infrastructure.input.adapter.rest.handler.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiExceptionTo implements Serializable {

    private static final long serialVersionUID = -7934601970667276848L;

    String title;
    String message;
    Integer httpStatus;
    List<ErrorCodeTo> errorCodeList;
}
