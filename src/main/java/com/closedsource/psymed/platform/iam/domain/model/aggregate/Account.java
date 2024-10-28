package com.closedsource.psymed.platform.iam.domain.model.aggregate;

import com.closedsource.psymed.platform.iam.domain.model.commands.SignUpCommand;
import com.closedsource.psymed.platform.iam.domain.model.valueobjects.Roles;
import com.closedsource.psymed.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@NoArgsConstructor
public class Account extends AuditableAbstractAggregateRoot<Account> {
    @NotBlank
    @Getter
    @Size(max=20)
    @Column(unique = true)
    private String userName;

    @NotBlank
    @Getter
    @Size(min = 8, max = 20)
    private String password;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Roles role;

    public Account(String userName, String password, Roles role) {
        if (role == Roles.ROLE_PROFESSIONAL || role == Roles.ROLE_PATIENT) {
            throw new IllegalArgumentException("Invalid role");
        } else {
            this.userName = userName;
            this.password = password;
            this.role = role;
        }

    }

    public Account(SignUpCommand command) {
        System.out.println("role from command %s".formatted(command.role()));
        System.out.println("role from enum %s".formatted(Roles.ROLE_PROFESSIONAL.toString()));

        if(Objects.equals(command.role(), Roles.ROLE_PROFESSIONAL.toString())
                || Objects.equals(command.role(), Roles.ROLE_PATIENT.toString())) {
            this.userName = command.username();
            this.password = command.password();
            this.role = Roles.valueOf(command.role());
        } else {
            throw new IllegalArgumentException("Invalid role");
        }

    }

    public String getRoleInString() {
        return this.role.toString();
    }

}
