package lk.tharindu.controller;


import lk.tharindu.model.Allocation;
import lk.tharindu.service.AllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/emscloud")
public class AllocationController {

    @Autowired
    AllocationService allocationService;

    @RequestMapping(value = "/allocation",method = RequestMethod.POST)
    public Allocation Save(@RequestBody Allocation allocation) {
        return allocationService.save(allocation);
    }

    @RequestMapping(value = "/allocation",method = RequestMethod.GET)
    public List<Allocation> fetchAllEmployees() {
        return allocationService.fetchAllAllocations();
    }


    @RequestMapping(value = "/allocation/{id}",method = RequestMethod.GET)
    public ResponseEntity<Allocation> fetctAllocation(@PathVariable Integer id) {
        Allocation  allocation =new Allocation();
        allocation.setId(id);
        Allocation allocation1 =allocationService.fetchAllocation(allocation);
        if(allocation1==null) {
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(allocation1);
        }
    }

    @RequestMapping(value = "/allocations/{empid}",method = RequestMethod.GET)
    public List<Allocation> fetctAllocationByeid(@PathVariable Integer empid) {


            return allocationService.fetchAllocationEmp(empid);

    }

//    @RequestMapping(value = "/allocation/{id}/project",method = RequestMethod.GET)
//    public ResponseEntity<List<Project>> fetchAllEmployeeProjects(@PathVariable Integer id) {
//        Allocation  allocation =new Allocation();
//        allocation.setId(id);
//        Allocation allocation1 =allocationService.fetchAllocation(allocation);
//        if(allocation1==null) {
//            return ResponseEntity.notFound().build();
//        }else{
//            return ResponseEntity.ok(allocation1.g());
//        }
//    }


}
