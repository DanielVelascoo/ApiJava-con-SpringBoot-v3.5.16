package com.api.DanielVelasco.services;

import com.api.DanielVelasco.dto.ClienteRequestDTO;
import com.api.DanielVelasco.dto.ClienteResponseDTO;
import com.api.DanielVelasco.dto.FacturaRequestDTO;
import com.api.DanielVelasco.dto.FacturaResponseDTO;
import com.api.DanielVelasco.entities.Cliente;
import com.api.DanielVelasco.entities.Factura;
import com.api.DanielVelasco.exceptions.ResourceNotFoundException;
import com.api.DanielVelasco.repositories.ClienteRepository;
import com.api.DanielVelasco.repositories.FacturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FacturaService {

    private final FacturaRepository facturaRepository;
    private final ClienteRepository clienteRepository;

    public List<FacturaResponseDTO> obtener() {
        //Creación de la Lista en facturas
        List<Factura> facturas = facturaRepository.findAll();
        //Creación del Array todo queda en la variable de respuesta
        List<FacturaResponseDTO> respuesta = new ArrayList<>();
        //Recorremos o iteramos los objetos para llenar el Array
        for (Factura factura : facturas) {

            FacturaResponseDTO datos = new FacturaResponseDTO();
            //Pasamos los datos al dto
            datos.setId(factura.getId());
            datos.setClienteId(factura.getCliente().getId());
            datos.setNombreCliente(factura.getCliente().getNombre());
            datos.setFechaCreacion(factura.getFechaCreacion());
            datos.setTotal(factura.getTotal());
            //Adccionamos los datos a respuesta
            respuesta.add(datos);
        }
        //Retornamos la respuesta que este caso es el array con los objetos
        return respuesta;
    }

    public FacturaResponseDTO obtenerFactura(Long id) {
        //Instanciamos el prodcuto por ID y se almacena en la variable producto
        Factura factura = facturaRepository.findById(id)
                //Creamos el mensaje en caso de que no exista el producto
                .orElseThrow(() -> new ResourceNotFoundException("Factura no encontrada"));

        //Se hace el Objeto del dto
        FacturaResponseDTO dto = new FacturaResponseDTO();
        //Se le instancian datos
        dto.setId(factura.getId());
        dto.setClienteId(factura.getCliente().getId());
        dto.setNombreCliente(factura.getCliente().getNombre());
        dto.setFechaCreacion(factura.getFechaCreacion());
        dto.setTotal(factura.getTotal());
        //Se retorna el dto para mostrar los datos
        return dto;
    }

    public FacturaResponseDTO crear(FacturaRequestDTO factura) {
        //Creación de la Entidad
        Factura facturaNueava = new Factura();

        facturaNueava.setTotal(factura.getTotal());
        //Primero hago la busqueda si el cliente existe...
        Cliente cliente = clienteRepository.findById(factura.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        //Luego lo Seteamos al momento de crear la factura
        facturaNueava.setCliente(cliente);

        //Guardar
        Factura facturaGuardada = facturaRepository.save(facturaNueava);

        //Creación de la respuesta
        FacturaResponseDTO respuesta = new FacturaResponseDTO();
        respuesta.setId(facturaGuardada.getId());
        respuesta.setClienteId(facturaGuardada.getCliente().getId());
        respuesta.setNombreCliente(facturaGuardada.getCliente().getNombre());
        respuesta.setFechaCreacion(facturaGuardada.getFechaCreacion());
        respuesta.setTotal(facturaGuardada.getTotal());

        return respuesta;
    }

    public FacturaResponseDTO update(Long id, FacturaRequestDTO detalleFactura) {

        Factura factura = facturaRepository.findById(id)
                //Creamos el mensaje en caso de que no exista el producto
                .orElseThrow(() -> new ResourceNotFoundException("Factura no encontrada"));
        //Seteamos los datos enviados para actualizar
        //Primero hago la busqueda si el cliente existe...
        Cliente cliente = clienteRepository.findById(factura.getCliente().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        factura.setCliente(cliente);
        //Guardamos los datos
        facturaRepository.save(factura);
        //Llenamos el dto
        FacturaResponseDTO dto = new FacturaResponseDTO();
        dto.setId(factura.getId());
        dto.setClienteId(factura.getCliente().getId());
        dto.setNombreCliente(factura.getCliente().getNombre());
        dto.setFechaCreacion(factura.getFechaCreacion());
        dto.setTotal(factura.getTotal());
        //Retornamos la respuesta
        return dto;
    }

    public String borrarFactura(Long id) {

        Factura factura = facturaRepository.findById(id)
                //Creamos el mensaje en caso de que no exista el producto
                .orElseThrow(() -> new ResourceNotFoundException("Factura no encontrado"));
        //Eliminamos el prdocuto y pasamos el producto erncontrado
        facturaRepository.delete(factura);
        //Retornamos el mensaje de exito
        return "Factura eliminada correctamente";
    }
}
