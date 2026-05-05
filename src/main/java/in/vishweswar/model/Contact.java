package in.vishweswar.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
