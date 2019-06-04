package lk.tharindu.ems.service;

import java.util.List;
import java.util.Optional;

import lk.tharindu.ems.model.Allocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;

import lk.tharindu.ems.model.Employee;
import lk.tharindu.ems.model.Telephone;
import lk.tharindu.ems.repository.EmployeeRepository;
import org.springframework.web.client.RestTemplate;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	RestTemplate restTemplate;

	
	public Employee save(Employee employee) {
		
		for (Telephone telephone : employee.getTelephones()) {
			telephone.setEmployee(employee);
		}
		
		return employeeRepository.save(employee);
	}
	
	public List<Employee> fetchAllEmployee(){
		return employeeRepository.findAll();
	}
	
	public Employee fetchEmployee(Employee employee) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getId());
		if (optionalEmployee.isPresent()) {
			// fetch project alllocation
	//		RestTemplate restTemplate=new RestTemplate();
			HttpHeaders httpHeaders=new HttpHeaders();

			//extract token from context
			OAuth2AuthenticationDetails oAuth2AuthenticationDetails =(OAuth2AuthenticationDetails)
					SecurityContextHolder.getContext().getAuthentication().getDetails();

			System.out.println(">>>>"+oAuth2AuthenticationDetails.getTokenValue());
			httpHeaders.add("Authorization","bearer".concat(oAuth2AuthenticationDetails.getTokenValue()));

			//
			ResponseEntity<Allocation[]> responseEntity;
			HttpEntity<String> httpEntity=new HttpEntity<>("",httpHeaders);
			 responseEntity=restTemplate.exchange("http://allocation-service/emscloud/allocations/".
					 concat(employee.getId().toString()),HttpMethod.GET,httpEntity, Allocation[].class);


					 Employee employee1= optionalEmployee.get();
					 employee1.setAllocation(responseEntity.getBody());
			return employee1;
		}else {
			return null;
		}
	}

}
