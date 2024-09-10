package vn.hust.hedspi.ezsport.database.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "field_orders")
public class FieldOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "field_id",referencedColumnName = "id",nullable = false)
    private Field field;

    @Column(name = "start_time",nullable = false)
    private LocalTime start;

    @Column(name = "end_time",nullable = false)
    private LocalTime end;

    @Column(name = "order_date",nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private double price;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;
}
