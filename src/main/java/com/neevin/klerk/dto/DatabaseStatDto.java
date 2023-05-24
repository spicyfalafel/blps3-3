package com.neevin.klerk.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.neevin.klerk.mapper.CustomLocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatabaseStatDto {
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    LocalDateTime messageDate;
    Long idleInTransactionTime;
}
