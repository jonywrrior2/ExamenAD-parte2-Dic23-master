package cesur.examen.domain.client;

import cesur.examen.domain.car.Car;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * EXAMEN DE ACCESO A DATOS
 * Diciembre 2023
 *
 * Nombre del alumno:Cristian Bersabe Atienza
 * Fecha:11/12/2023
 */


@Data
@Entity
@Table(name = "cliente")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "email")
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client", cascade = CascadeType.ALL)
    private List<Car> cars = new ArrayList<>();

    /**
     * Utility to add a car to a client.
     * This allows to maintain bidirectional consistency over the relationship,
     * providing client information to the car added.
     *
     * @param c Car to be added to the client.
     */
    public void addCar(Car c) {
        c.setClient(this);
        cars.add(c);
    }

}
