package models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Arrays;

public class Reimbursement {

    private Integer id;
    private Double amount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm a z")
    private Timestamp submitted_time;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm a z")
    private Timestamp resolved_time;
    private String description;
    private byte[] bytearray;
    private Integer author;
    private Integer resolver;
    private Integer status_id;
    private Integer type_id;

    public Reimbursement() {}

    public Reimbursement(Double amount, String description, Integer author, Integer type_id) {
        this.amount = amount;
        this.description = description;
        //this.bytearry = null;
        this.author = author;
        this.type_id = type_id;
    }

    public Reimbursement(Integer id, Double amount, Timestamp submitted_time, Timestamp resolved_time, String description, byte[] bytearray, Integer author, Integer resolver, Integer status_id, Integer type_id) {
        this.id = id;
        this.amount = amount;
        this.submitted_time = submitted_time;
        this.resolved_time = resolved_time;
        this.description = description;
        this.bytearray = bytearray;
        this.author = author;
        this.resolver = resolver;
        this.status_id = status_id;
        this.type_id = type_id;
    }

    public Integer getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Timestamp getSubmitted_time() {
        return submitted_time;
    }

    public void setSubmitted_time(Timestamp submitted_time) {
        this.submitted_time = submitted_time;
    }

    public Timestamp getResolved_time() {
        return resolved_time;
    }

    public void setResolved_time(Timestamp resolved_time) {
        this.resolved_time = resolved_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getBytearray() {return bytearray;}

    public void setBytearray(byte[] bytearray) {
        this.bytearray = bytearray;
    }

    public Integer getAuthor() {
        return author;
    }

    public Integer getResolver() {
        return resolver;
    }

    public void setResolver(Integer resolver) {
        this.resolver = resolver;
    }

    public Integer getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Integer status_id) {
        this.status_id = status_id;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "id=" + id +
                ", amount=" + amount +
                ", submitted_time=" + submitted_time +
                ", resolved_time=" + resolved_time +
                ", description='" + description + '\'' +
                ", bytearray=" + Arrays.toString(bytearray) +
                ", author=" + author +
                ", resolver=" + resolver +
                ", status_id=" + status_id +
                ", type_id=" + type_id +
                '}';
    }
}
