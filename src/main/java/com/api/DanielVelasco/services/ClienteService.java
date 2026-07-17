package com.api.DanielVelasco.services;

import com.api.DanielVelasco.dto.ClienteRequestDTO;
import com.api.DanielVelasco.dto.ClienteResponseDTO;
import com.api.DanielVelasco.entities.Cliente;
import com.api.DanielVelasco.exceptions.ResourceNotFoundException;
import com.api.DanielVelasco.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<ClienteResponseDTO> obtener() {
        //Creación de la Lista en clientes
        List<Cliente> clientes = clienteRepository.findAll();
        //Creación del Array todo queda en la variable de respuesta
        List<ClienteResponseDTO> respuesta = new ArrayList<>();
        //Recorremos o iteramos los objetos para llenar el Array
        for (Cliente cliente : clientes) {

            ClienteResponseDTO datos = new ClienteResponseDTO();
            //Pasamos los datos al dto
            datos.setId(cliente.getId());
            datos.setNombre(cliente.getNombre());
            datos.setEmail(cliente.getEmail());
            datos.setCelular(cliente.getCelular());
            //Adccionamos los datos a respuesta
            respuesta.add(datos);
        }
        //Retornamos la respuesta que este caso es el array con los objetos
        return respuesta;
    }

    public ClienteResponseDTO obtenerCliente(Long id) {
        //Instanciamos el prodcuto por ID y se almacena en la variable producto
        Cliente cliente = clienteRepository.findById(id)
                //Creamos el mensaje en caso de que no exista el producto
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        //Se hace el Objeto del dto
        ClienteResponseDTO dto = new ClienteResponseDTO();
        //Se le instancian datos
        dto.setId(cliente.getId());
        dto.setNombre(cliente.getNombre());
        dto.setEmail(cliente.getEmail());
        dto.setCelular(cliente.getCelular());
        //Se retorna el dto para mostrar los datos
        return dto;

    }

    public ClienteResponseDTO crear(ClienteRequestDTO cliente) {
        //Creación de la Entidad
        Cliente clienteNuevo = new Cliente();

        //Pasar los datos del DTO a la entidad
        clienteNuevo.setNombre(cliente.getNombre());
        clienteNuevo.setEmail(cliente.getEmail());
        clienteNuevo.setCelular(cliente.getCelular());

        //Guardar
        Cliente clienteGuardado = clienteRepository.save(clienteNuevo);

        //Creación de la respuesta
        ClienteResponseDTO respuesta = new ClienteResponseDTO();
        respuesta.setId(clienteGuardado.getId());
        respuesta.setNombre(clienteGuardado.getNombre());
        respuesta.setEmail(clienteGuardado.getEmail());
        respuesta.setCelular(clienteGuardado.getCelular());

        return respuesta;
    }

    public ClienteResponseDTO update(Long id, ClienteRequestDTO detalleCliente) {

        Cliente cliente = clienteRepository.findById(id)
                //Creamos el mensaje en caso de que no exista el producto
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        //Seteamos los datos enviados para actualizar
        cliente.setNombre(detalleCliente.getNombre());
        cliente.setCelular(detalleCliente.getCelular());
        //Guardamos los datos
        clienteRepository.save(cliente);
        //Llenamos el dto
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setId(cliente.getId());
        dto.setNombre(cliente.getNombre());
        dto.setCelular(cliente.getCelular());
        //Retornamos la respuesta
        return dto;
    }

    public String borrarCliente(Long id) {

        Cliente cliente = clienteRepository.findById(id)
                //Creamos el mensaje en caso de que no exista el producto
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        //Eliminamos el prdocuto y pasamos el producto erncontrado
        clienteRepository.delete(cliente);
        //Retornamos el mensaje de exito
        return "Cliente eliminado correctamente";
    }
}
