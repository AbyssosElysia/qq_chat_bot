package com.elysiaptr.dto;

import com.elysiaptr.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class GptDto {
    private String model;
    private List<Message> messages;
}
