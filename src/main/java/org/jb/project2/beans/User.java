package org.jb.project2.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jb.project2.login.ClientType;
@Data
@NoArgsConstructor @AllArgsConstructor
public class User {
    private String email;
    private String password;
    private ClientType clientType;
}
