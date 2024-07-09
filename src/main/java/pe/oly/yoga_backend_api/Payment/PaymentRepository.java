package pe.oly.yoga_backend_api.Payment;

import org.springframework.data.jpa.repository.JpaRepository;



import pe.oly.yoga_backend_api.Paquete.Paquete;



public interface PaymentRepository extends JpaRepository<Payment, Integer>{


   Payment findByPaqueteId(Paquete paquete);
}
