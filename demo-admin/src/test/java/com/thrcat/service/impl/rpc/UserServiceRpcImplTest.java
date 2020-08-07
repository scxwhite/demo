package com.thrcat.service.impl.rpc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thrcat.ResultSet;
import com.thrcat.User;
import com.thrcat.UserServiceRpc;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceRpcImplTest extends TestCase {


    @Autowired
    private UserServiceRpc userServiceRpc;

    @Test
    public void testLogin() throws JsonProcessingException {
        User user = new User();
        user.setUsername("sucx");
        user.setPassword("5678");
        ResultSet<Boolean> login = userServiceRpc.login(user);

        System.out.println(new ObjectMapper().writeValueAsString(login));

    }
}
