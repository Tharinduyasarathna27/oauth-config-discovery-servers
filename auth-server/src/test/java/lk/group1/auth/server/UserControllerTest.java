package lk.group1.auth.server;

import lk.group1.auth.server.controller.UserController;
import lk.group1.auth.server.model.User;
import lk.group1.auth.server.service.UserDetailServiceImpl;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(UserControllerTest.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserController userController;

    @MockBean
    UserDetailServiceImpl userDetailService;

    @Test
    public void testUrl() throws Exception {

        //test
        User user = new User();
        user.setId(1);
        user.setUserName("Waruna");
        user.setPassword("1qaz2wsx@");
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setRoles(null);
        user.setEmail("waruna@gmail.com");
        user.setEnabled(true);

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(user);


        mockMvc.perform(MockMvcRequestBuilders.put("/oauth/updateUser/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(str))
                        .andExpect(status().isOk());


    }




}
