package com.DAM.DAM1.Dominio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Director {
    private Long id;
    private String titulo;
    private int edad;
}
