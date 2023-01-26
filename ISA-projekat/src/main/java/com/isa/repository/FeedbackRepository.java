package com.isa.repository;

import com.isa.domain.model.Appointment;
import com.isa.domain.model.CenterAccount;
import com.isa.domain.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {


    List<Feedback> findByAppointment_CenterAccountId(long centerAccount);

}
