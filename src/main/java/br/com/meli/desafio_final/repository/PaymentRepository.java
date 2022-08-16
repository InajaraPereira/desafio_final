package br.com.meli.desafio_final.repository;

import br.com.meli.desafio_final.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(value = "select full_payment, payment_date, credicard_number, flag, name_in_card\n" +
            "from payment\n" +
            "join credicard\n" +
            "on payment.id = credicard.payment_id;", nativeQuery = true)
    List<Object[]> findPaymentByCredicard(Long id);

}
