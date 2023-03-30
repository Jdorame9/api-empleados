package com.taller.apiempleados.services;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller.apiempleados.dtos.EmpleadoDto;
import com.taller.apiempleados.entities.EmpleadoEntity;
import com.taller.apiempleados.mappers.EmpleadosMappers;
import com.taller.apiempleados.repositories.EmpleadoPersistenceContext;
import com.taller.apiempleados.repositories.EmpleadoRepository;

@Service
public class EmpleadoServices {
    
    @Autowired EmpleadosMappers empleadosMappers;

    @Autowired EmpleadoRepository empleadoRepository;

    @Autowired EmpleadoPersistenceContext empleadoPersistenceContext;

    private static final Log logger = LogFactory.getLog(EmpleadoServices.class);

    public EmpleadoDto findEmpleado(int idempleado) {
        var empleado = empleadoPersistenceContext.findEmpleado(idempleado);

        return empleadosMappers.getEmpleadoDto(empleado, EmpleadoDto.class);
    }

    public List<EmpleadoDto> findEmpleados() {
        var empleados = empleadoPersistenceContext.findEmpleados();
        return empleadosMappers.getListDto(empleados, EmpleadoDto.class);

    }

    public String insertEmpleado(EmpleadoDto empleadoDto) {
        EmpleadoEntity empleadoEntity = new EmpleadoEntity();
        try {
            empleadoEntity = empleadosMappers.getEmpleadoEntity(empleadoEntity,empleadoDto);
            empleadoPersistenceContext.insertEmpleado(empleadoEntity);
            return "Empleado Registrado";
        } catch(Exception ex) {
            logger.error(empleadoDto, ex);
        }
        return null;
    }

    public String eliminarEmpleado(int idempleado) {
        try {
            var empleado = empleadoPersistenceContext.findEmpleado(idempleado);
            empleadoPersistenceContext.deleteEmpleado(empleado);
            return "Empleado eliminado con exito";
        } catch (Exception ex) {
            logger.error(ex, ex);
        }
        return null;
    }

    public String updateEmpleado(EmpleadoDto empleadoDto) {
        try {
            var empleados = empleadoPersistenceContext.findEmpleados();
            var empleado = empleados.stream().filter(x -> x.getIdempleado() == empleadoDto.getIdempleado()).collect(Collectors.toList()).get(0);
            empleado = empleadosMappers.getEmpleadoEntity(empleado, empleadoDto);
            empleadoPersistenceContext.updateEmpleado(empleado);
            return "Datos de empleado actualizado";
        } catch (Exception ex) {
            logger.error(ex, ex);
        }
        return null;
    }
}