package lk.tharindu.service;

import lk.tharindu.model.Allocation;

import java.util.List;

public interface AllocationService {

    Allocation save(Allocation allocation);
    List<Allocation> fetchAllAllocations();
    Allocation fetchAllocation(Allocation allocation);
    List<Allocation> fetchAllocationEmp(Integer empid);
}
