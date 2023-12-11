package cesur.examen.domain.client;

import cesur.examen.common.HibernateUtil;
import cesur.examen.domain.car.Car;
import cesur.examen.domain.car.CarDAO;
import lombok.extern.java.Log;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * EXAMEN DE ACCESO A DATOS
 * Diciembre 2023
 *
 * Nombre del alumno:Cristian Bersabe Atienza
 * Fecha:11/12/2023
 */
@Log
public class ClientService {

    /**
     * Return a List of Client entities that have one or more cars of the given manufacturer.
     * If a client has more than one car of the manufacturer, it only appears once in
     * the list (similar to a Set). Tip: start querying to Car entities...
     *
     * @param manufacturer
     * @return the list of clients
     */
    public static List<Client> hasManufacturer(String manufacturer) {
        List<Client> clientsWithManufacturer = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Use HQL to select clients with at least one car of the specified manufacturer
            String hql = "SELECT DISTINCT c FROM Client c JOIN FETCH c.cars car WHERE car.manufacturer = :manufacturer";
            Query<Client> query = session.createQuery(hql, Client.class);
            query.setParameter("manufacturer", manufacturer);

            clientsWithManufacturer = query.list();
        } catch (Exception e) {
            log.severe("Error getting clients with manufacturer");
            throw new RuntimeException(e);
        }

        return clientsWithManufacturer;
    }
}

