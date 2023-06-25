package com.example.insecuredeserialization;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class testDTO implements Serializable {
    String payload;
}
