package com.example.tobi.SpringbootBasicBoard.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Board {
    private Long id;
    private String title;
    private String content;
    private String userId;
    private LocalDateTime created;
}
