package io.pivotal.pal.data.rentaltrucks.reservation.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "reservation", schema = "reservation")
public class Reservation {

    @Id
    @Column(name = "confirmation_number")
    private String confirmationNumber;

    @Column(name = "status")
    private String status;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "customer_name")
    private String customerName;

    public Reservation(String confirmationNumber, String status, LocalDate startDate, LocalDate endDate, String customerName) {
        this.confirmationNumber = confirmationNumber;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customerName = customerName;
    }

    Reservation() {
        // default constructor
    }

    ////////////////////////

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    ////////////////////////

    public void finalizeConfirmation() {
        // poor man's state machine
        if (!status.equals("REQUESTED")) {
            throw new IllegalStateException("Reservation must be REQUESTED in order to FINALIZE");
        }

        status = "FINALIZED";
    }

    public void complete() {
        status = "COMPLETED";
    }

    // initiated by system
    public void failed() {
        // depending on the reservation state, may need to adjust the trucks on hand
        // update the status failed
    }

    // initiated by user
    public void cancel() {
        // depending on the reservation state, may need to adjust the trucks on hand
        // update the status to canceled
    }

    ////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(confirmationNumber, that.confirmationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(confirmationNumber);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "confirmationNumber='" + confirmationNumber + '\'' +
                ", status='" + status + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
