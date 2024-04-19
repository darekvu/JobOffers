package com.junioroffers.domain.loginandregister.model;

import lombok.*;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;

}
