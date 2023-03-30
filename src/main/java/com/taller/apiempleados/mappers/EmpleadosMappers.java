package com.taller.apiempleados.mappers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taller.apiempleados.dtos.EmpleadoDto;
import com.taller.apiempleados.entities.EmpleadoEntity;

@Component
public class EmpleadosMappers {

    public <T> List<T> getListDto(Iterable<?> listEmpleadoEntity, Class<T> instanceClass) {
        List<T> list = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (Object item : listEmpleadoEntity) {
            T entry = item instanceof List<?> ? 
            instanceClass.cast(item) : mapper.convertValue(item, instanceClass);

            list.add(entry);
        }
        return list;
    }

    public EmpleadoDto getEmpleadoDto(EmpleadoEntity empleado, Class<EmpleadoDto> instanceClass) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(empleado, instanceClass);
    }

    public EmpleadoEntity getEmpleadoEntity(EmpleadoEntity empleadoEntity, EmpleadoDto empleadoDto) {
        ObjectMapper mapper = new ObjectMapper();
        empleadoEntity = mapper.convertValue(empleadoDto, EmpleadoEntity.class);
        return empleadoEntity;
    }
}
