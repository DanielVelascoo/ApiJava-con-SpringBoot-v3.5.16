package com.api.DanielVelasco.services;

import com.api.DanielVelasco.dto.detallefactura.DetalleFacturaRequestDTO;
import com.api.DanielVelasco.dto.factura.FacturaRequestDTO;
import com.api.DanielVelasco.dto.factura.FacturaResponseDTO;
import com.api.DanielVelasco.entities.Cliente;
import com.api.DanielVelasco.entities.DetalleFactura;
import com.api.DanielVelasco.entities.Factura;
import com.api.DanielVelasco.exceptions.ResourceNotFoundException;
import com.api.DanielVelasco.mapper.DetalleFacturaMapper;
import com.api.DanielVelasco.mapper.FacturaMapper;
import com.api.DanielVelasco.repositories.ClienteRepository;
import com.api.DanielVelasco.repositories.FacturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FacturaService {

    private final FacturaRepository facturaRepository;
    private final ClienteRepository clienteRepository;
    private final DetalleFacturaService detalleFacturaService;
    private final DetalleFacturaMapper detalleFacturaMapper;
    private final FacturaMapper facturaMapper;

    public List<FacturaResponseDTO> obtener() {
        return facturaMapper.toDTOList(facturaRepository.findAll());
    }

    public FacturaResponseDTO obtenerFactura(Long id) {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Factura no encontrada"));
        return facturaMapper.toDTO(factura);
    }

    @Transactional
    public FacturaResponseDTO crear(FacturaRequestDTO factura) {
        // Convertir DTO a entidad
        Factura facturaNueva = facturaMapper.toEntity(factura);
        // Buscar cliente
        Cliente cliente = clienteRepository.findById(factura.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        facturaNueva.setCliente(cliente);
        BigDecimal total = BigDecimal.ZERO;
        List<DetalleFactura> detalles = new ArrayList<>();
        // Crear detalles
        for (DetalleFacturaRequestDTO detalleRequest : factura.getDetalles()) {
            DetalleFactura detalle = detalleFacturaService.crearDetalle(detalleRequest);
            detalle.setFactura(facturaNueva);
            detalles.add(detalle);
            total = total.add(detalle.getSubtotal());
        }
        facturaNueva.setDetalles(detalles);
        facturaNueva.setTotal(total);
        // Guardar factura
        Factura facturaGuardada = facturaRepository.save(facturaNueva);
        // Convertir a DTO de respuesta
        return facturaMapper.toDTO(facturaGuardada);
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
