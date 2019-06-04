package lk.tharindu.repository;

import lk.tharindu.model.Allocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AllocationRepository extends JpaRepository<Allocation,Integer> {

    List<Allocation> findByempid(Integer empid);
}
