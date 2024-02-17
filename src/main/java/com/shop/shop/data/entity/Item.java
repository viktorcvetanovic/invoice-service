package com.shop.shop.data.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "item")
@RequiredArgsConstructor
public class Item implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "item_info_fk", referencedColumnName = "item_info_id")
	private ItemInfo itemInfo;
	@Column(name = "quantity")
	private Integer quantity;
	@Column(name = "discount_on_bought")
	private Integer discountOnBought;
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Item item = (Item) o;
		return id.equals(item.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


}