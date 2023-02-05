package de.qaware.cloudcomputing.tle;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Position {
    private BigDecimal x;
    private BigDecimal y;
    private BigDecimal z;
    private BigDecimal r;
    private String unit;
}
