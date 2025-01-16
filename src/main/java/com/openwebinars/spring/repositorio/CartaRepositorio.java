package com.openwebinars.spring.repositorio;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openwebinars.spring.entidades.Carta;

public interface CartaRepositorio extends JpaRepository<Carta, Long> {
    
    public Carta findByNombreCarta(String nombreCarta);

    public Carta findByCodigoCarta(String codigoCarta);

    public Carta findByTipoCarta(String tipoCarta);

    public Carta findByColor(String color);

    public Carta findByCosteCarta(String costeCarta);

    public Carta findByPrecioCarta(BigDecimal precioCarta);

    List<Carta> findByNombreCartaContainingIgnoreCase(String nombreCarta);
}
