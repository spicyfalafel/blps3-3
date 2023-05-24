package com.neevin.klerk.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.neevin.klerk.mapper.CustomLocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatMessageDto {
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    LocalDateTime messageDate;
    String httpMethod;
    String url;
    Integer responseCode;
}
