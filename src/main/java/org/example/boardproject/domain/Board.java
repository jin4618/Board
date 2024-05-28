package org.example.boardproject.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class Board {
    @Id
    private Long id;
    private String name;
    private String title;
    private String password;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;

}
