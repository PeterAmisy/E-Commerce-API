package com.peter.amisy.e_commerce_api.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private String id;
    private String name;
    private String email;
}
