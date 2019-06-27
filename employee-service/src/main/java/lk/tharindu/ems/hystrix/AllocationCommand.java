package lk.tharindu.ems.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import lk.tharindu.ems.model.Allocation;
import lk.tharindu.ems.model.Employee;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class AllocationCommand extends HystrixCommand<Allocation[]> {

    Employee employee;
    HttpHeaders httpHeaders;
    RestTemplate restTemplate;

 public AllocationCommand(Employee employee,HttpHeaders httpHeaders,RestTemplate restTemplate){
     super(HystrixCommandGroupKey.Factory.asKey("default"));
     this.employee=employee;
     this.httpHeaders=httpHeaders;
     this.restTemplate=restTemplate;
 }

    @Override
    protected Allocation[] run() throws Exception {
        ResponseEntity<Allocation[]> responseEntity;
        HttpEntity<String> httpEntity=new HttpEntity<>("",httpHeaders);
        responseEntity=restTemplate.exchange("http://allocation-service/emscloud/allocations/".
                concat(employee.getId().toString()), HttpMethod.GET,httpEntity, Allocation[].class);
        return responseEntity.getBody();
    }


    @Override
    protected Allocation[] getFallback() {
        return new Allocation[1];
    }
}
