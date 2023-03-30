package com.taller.apiempleados.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpleadoDto implements Serializable{
    private int idempleado;
    private int tipoempleado;
    private String usuario;
    private String nombre;
    private String apellidopaterno;
    private String apellidomaterno;
    private String email;
}
