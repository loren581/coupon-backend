package org.jb.project2.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Setter
@Getter

public class CompanyServiceImpl extends ClientService implements CompanyService{
    private int companyId;
    @Override
    public boolean login(String email, String password) {
        return false;
    }
}
