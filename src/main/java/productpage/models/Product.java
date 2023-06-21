package productpage.models;

import lombok.Data;
import productpage.annotations.Column;

@Data
public class Product {
    @Column("id")
    private Integer id;
    @Column("price")
    private Float price;
    @Column("name")
    private String name;
    @Column("description")
    private String description;
    @Column("owner")
    private String owner;
}
