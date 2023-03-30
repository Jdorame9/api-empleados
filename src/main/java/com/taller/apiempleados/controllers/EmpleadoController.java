package com.taller.apiempleados.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.taller.apiempleados.dtos.EmpleadoDto;
import com.taller.apiempleados.services.EmpleadoServices;

@RestController
@RequestMapping("empleados")
public class EmpleadoController {

    @Autowired EmpleadoServices empleadoServices;
    
    @GetMapping(value = "/{idempleado}")
    @ResponseBody
    public EmpleadoDto getEmpleado(
        @PathVariable("idempleado") int idempleado
    ) {
        return empleadoServices.findEmpleado(idempleado);
    }

    @GetMapping
    @ResponseBody
    public List<EmpleadoDto> getEmpleados() {
        return empleadoServices.findEmpleados();
    }

    @PostMapping
    @ResponseBody
    public String insertEmpleado(
        @RequestBody EmpleadoDto empleadoDto
    ) {
        return empleadoServices.insertEmpleado(empleadoDto);
    }

    @DeleteMapping
    @ResponseBody
    public String deleteEmpleado(
        @RequestParam(defaultValue = "0", name = "idempleado") int idempleado
    ) {
        return empleadoServices.eliminarEmpleado(idempleado);
    }

    @PutMapping
    @ResponseBody
    public String updateEmpleado(
        @RequestBody EmpleadoDto empleadoDto
    ) {
        return empleadoServices.updateEmpleado(empleadoDto);
    }
}
