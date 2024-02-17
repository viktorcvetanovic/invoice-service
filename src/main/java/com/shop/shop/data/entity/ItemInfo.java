package com.shop.shop.data.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "item_info")
public class ItemInfo implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_info_id")
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "price")
	private Double price;
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ItemInfo itemInfo = (ItemInfo) o;
		return id.equals(itemInfo.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


}