package com.shop.shop.data.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "invoice")
@RequiredArgsConstructor
public class Invoice implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_id")
	private Integer id;
	@Column(name = "date")
	private Date date;
	@Column(name = "name")
	private String name;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "invoice_fk")
	private List<Item> itemList;

	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Invoice invoice = (Invoice) o;
		return id.equals(invoice.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


}