package com.isa.repository;

import com.isa.domain.model.Appointment;
import com.isa.domain.model.CenterAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByPatientId(Long id);

    List<Appointment> findAllByCenterAccountId(long centerAccountId);
}
