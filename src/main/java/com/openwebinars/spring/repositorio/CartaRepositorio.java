package com.openwebinars.spring.repositorio;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.openwebinars.spring.entidades.Carta;

public interface CartaRepositorio extends JpaRepository<Carta, Long> {

    public Carta findByNombreCarta(String nombreCarta);

    public Carta findByCodigoCarta(String codigoCarta);

    public Carta findByTipoCarta(String tipoCarta);

    public Carta findByColor(String color);

    public Carta findByCosteCarta(String costeCarta);

    public Carta findByPrecioCarta(BigDecimal precioCarta);

    @Query("SELECT c FROM Carta c WHERE LOWER(c.nombreCarta) LIKE LOWER(CONCAT('%', ?1, '%')) OR LOWER(c.tipoCarta) LIKE LOWER(CONCAT('%', ?1, '%')) OR LOWER(c.color) LIKE LOWER(CONCAT('%', ?1, '%')) OR LOWER(c.codigoCarta) LIKE LOWER(CONCAT('%', ?1, '%')) OR LOWER(c.costeCarta) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Carta> findByAll(String query);

    @Query("SELECT c FROM Carta c WHERE (LOWER(c.nombreCarta) LIKE LOWER(CONCAT('%', ?1, '%')) OR LOWER(c.tipoCarta) LIKE LOWER(CONCAT('%', ?1, '%')) OR LOWER(c.color) LIKE LOWER(CONCAT('%', ?1, '%')) OR LOWER(c.codigoCarta) LIKE LOWER(CONCAT('%', ?1, '%')) OR LOWER(c.costeCarta) LIKE LOWER(CONCAT('%', ?1, '%'))) AND LOWER(c.categoria) = LOWER(?2)")
    List<Carta> findByAllAndCategoria(String query, String categoria);
}
