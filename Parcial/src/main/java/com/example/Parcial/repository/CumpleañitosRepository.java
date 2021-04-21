package com.example.Parcial.repository;

import com.example.Parcial.model.Cumpleañitos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CumpleañitosRepository extends JpaRepository<Cumpleañitos,Integer> {
}
