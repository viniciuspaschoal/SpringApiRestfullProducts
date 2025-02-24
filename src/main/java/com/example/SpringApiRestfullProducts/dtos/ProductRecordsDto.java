package com.example.SpringApiRestfullProducts.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRecordsDto(@NotBlank String name, @NotNull BigDecimal value) {
}
