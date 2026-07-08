package com.duoc.backend.Invoice;

import com.duoc.backend.Care.Care;
import com.duoc.backend.Care.CareRepository;
import com.duoc.backend.Medication.Medication;
import com.duoc.backend.Medication.MedicationRepository;
import com.duoc.backend.Notification.SqsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private CareRepository careRepository;

    @Autowired
    private SqsService sqsService;

    public Iterable<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    public Invoice saveInvoice(Invoice invoice) {
        // Validar que los medicamentos existen
        List<Medication> validMedications = StreamSupport.stream(
                medicationRepository.findAllById(
                        invoice.getMedications().stream().map(Medication::getId).collect(Collectors.toList())
                ).spliterator(), false
        ).collect(Collectors.toList());
        if (validMedications.size() != invoice.getMedications().size()) {
            throw new IllegalArgumentException("Algunos medicamentos no existen en la base de datos.");
        }

        // Validar que los servicios existen
        List<Care> validCares = StreamSupport.stream(
                careRepository.findAllById(
                        invoice.getCares().stream().map(Care::getId).collect(Collectors.toList())
                ).spliterator(), false
        ).collect(Collectors.toList());
        if (validCares.size() != invoice.getCares().size()) {
            throw new IllegalArgumentException("Algunos servicios no existen en la base de datos.");
        }

        // Calcular el costo total basado en los servicios y medicamentos asociados
        double totalCareCost = validCares.stream()
                .mapToDouble(Care::getCost)
                .sum();

        double totalMedicationCost = validMedications.stream()
                .mapToDouble(Medication::getCost)
                .sum();

        invoice.setTotalCost(totalCareCost + totalMedicationCost);

        // Guardar la factura en el repositorio
        Invoice saved = invoiceRepository.save(invoice);

        // Enviar notificación a AWS SQS
        try {
            String message = String.format(
                "Factura #%d - Paciente: %s - Total: $%.2f",
                saved.getId(), saved.getPatientName(), saved.getTotalCost()
            );
            sqsService.sendMessage(message);
        } catch (Exception e) {
            // Si SQS falla, no bloquear la creación de la factura
            System.err.println("Error enviando mensaje a SQS: " + e.getMessage());
        }

        return saved;
    }

    public Invoice updateInvoice(Long id, Invoice invoiceDetails) {
        Invoice invoice = invoiceRepository.findById(id).orElse(null);
        if (invoice == null) return null;
        invoice.setPatientName(invoiceDetails.getPatientName());
        invoice.setDate(invoiceDetails.getDate());
        invoice.setTime(invoiceDetails.getTime());
        invoice.setCares(invoiceDetails.getCares());
        invoice.setMedications(invoiceDetails.getMedications());
        invoice.setPatient(invoiceDetails.getPatient());
        double totalCareCost = invoice.getCares().stream()
                .mapToDouble(Care::getCost).sum();
        double totalMedicationCost = invoice.getMedications().stream()
                .mapToDouble(Medication::getCost).sum();
        invoice.setTotalCost(totalCareCost + totalMedicationCost);
        return invoiceRepository.save(invoice);
    }

    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }
}