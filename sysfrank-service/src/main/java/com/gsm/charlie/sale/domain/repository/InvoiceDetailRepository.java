package com.gsm.charlie.sale.domain.repository;

import com.gsm.charlie.sale.domain.model.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Long> {
}
