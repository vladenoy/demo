package com.example.demo.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_seq", allocationSize = 1)
    Long id;
    @Column(name="name", unique=true, length=500, nullable = false)
    String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    LocalDate dateOfBirth;
    @Column(name="password", length=500, nullable = false)
    @Size(min = 8, max = 500, message = "Password should be in range 8-500 characters")
    String password;
    @OneToOne(mappedBy="user", cascade = CascadeType.ALL)
    @NotNull(message = "Each user must have one account.")
    @JsonManagedReference
    Account account;
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
    @NotEmpty(message = "Phones should contain at least one phone number")
    @JsonManagedReference
    @ToString.Exclude
    List<PhoneData> phones;
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
    @NotEmpty(message = "Emails should contain at least one email address")
    @JsonManagedReference
    @ToString.Exclude
    List<EmailData> emails;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
