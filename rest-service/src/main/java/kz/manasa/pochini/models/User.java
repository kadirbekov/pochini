package kz.manasa.pochini.models;

import kz.manasa.pochini.constants.ApplicationConstants;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by dkadirbekov on 30.06.2016.
 */
@Entity
@Table(
        name = "user",
        schema = ApplicationConstants.DB_SCHEMA_COMMON,
        uniqueConstraints = {@UniqueConstraint(name = "user_email_uk", columnNames = "email")}
)
public class User implements GenericEntity, Serializable {

    @Id
    @Type(type = "pg-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private UUID id;

    private String email;

    private Date createDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
