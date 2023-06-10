package org.jb.project2.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jb.project2.login.ClientType;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Information {
    private long id;
    private LocalDateTime time;
    private ClientType clientType;
}
