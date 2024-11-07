package initiativedeuxsevres.ttm.model;

import java.util.ArrayList;
import java.util.List;

import initiativedeuxsevres.ttm.model.enums.Fields;
import initiativedeuxsevres.ttm.model.enums.Role;
import initiativedeuxsevres.ttm.model.enums.Support;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User  {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String username;

    @NonNull
    private String email;

    @NonNull
    private String password;

    @NonNull
    private Role role;

    @ElementCollection
    private List<Fields> fields;

    @ElementCollection
    private List<Support> supports;

    @Builder.Default
    @OneToMany(mappedBy = "id")
    private List<User> porteurs = new ArrayList<>();

    @OneToOne
    private User parrain;

}