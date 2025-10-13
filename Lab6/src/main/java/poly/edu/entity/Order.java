package poly.edu.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String address;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "Createdate")
    private Date createDate = new Date();
    
    @ManyToOne
    @JoinColumn(name = "Username")
    private Account account;
    
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
}