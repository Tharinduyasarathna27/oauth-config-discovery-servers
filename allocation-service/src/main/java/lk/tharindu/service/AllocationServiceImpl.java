package lk.tharindu.service;

import lk.tharindu.model.Allocation;
import lk.tharindu.repository.AllocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AllocationServiceImpl implements AllocationService {

    @Autowired
    AllocationRepository allocationRepository;


    @Override
    public Allocation save(Allocation allocation) {
        return allocationRepository.save(allocation);
    }

    @Override
    public List<Allocation> fetchAllAllocations() {
        return allocationRepository.findAll();
    }

    @Override
    public Allocation fetchAllocation(Allocation allocation) {
        Optional<Allocation> optionalAllocation = allocationRepository.findById(allocation.getId());
        if (optionalAllocation.isPresent()) {
            return optionalAllocation.get();
        }else {
            return null;
        }
    }


    @Override
    public List<Allocation> fetchAllocationEmp(Integer empid) {


            return allocationRepository.findByempid(empid);

    }

}
