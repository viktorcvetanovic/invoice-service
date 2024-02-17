package com.shop.shop.data.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "stock")
@RequiredArgsConstructor
public class Stock implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stock_id")
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "item_info_fk", referencedColumnName = "item_info_id")
	private ItemInfo itemInfo;
	@Column(name = "quantity")
	private Integer quantity;
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Stock stock = (Stock) o;
		return id.equals(stock.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


}