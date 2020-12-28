package by.belhard.BH26.kirill.casinoProject.casinoApp.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@Builder
public class Account {

    private int id;
    private String username;
    private String password;
    private int money;

}