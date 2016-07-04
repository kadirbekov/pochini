package kz.manasa.pochini.models;

import kz.manasa.pochini.constants.ApplicationConstants;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by dkadirbekov on 30.06.2016.
 */
@Entity
@Table(
        name = "currency",
        schema = ApplicationConstants.DB_SCHEMA_COMMON,
        uniqueConstraints = {@UniqueConstraint(name = "currency_code_uk", columnNames = "code")}
)
public class Currency implements GenericEntity {

    @Id
    @Type(type = "pg-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private UUID id;

    private String code;

    private String name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
