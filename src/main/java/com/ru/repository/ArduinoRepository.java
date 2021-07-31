package com.ru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ru.model.Arduino;

@Repository
public interface ArduinoRepository extends JpaRepository<Arduino, Integer>{

}
