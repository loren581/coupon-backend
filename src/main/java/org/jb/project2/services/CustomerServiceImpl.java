package org.jb.project2.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter

public class CustomerServiceImpl extends ClientService implements CustomerService{
    private int customerId;
    @Override
    public boolean login(String email, String password) {
        return false;
    }
}
