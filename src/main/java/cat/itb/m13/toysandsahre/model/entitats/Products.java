package cat.itb.m13.toysandsahre.model.entitats;

import lombok.Data;
//import org.graalvm.compiler.lir.LIRInstruction;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue
    int id;
    @Column(name = "productName")
    String productName;
    Double price;
    @Column(name = "productLocation")
    String productLocation;
    @Column(name = "productDescription")
    String productDescription;
    @Column(name = "dateCreated")
    Date dateCreated;
    @Column(name = "imageLink")
    String imageLink;




    public Products(int id, Double price, String productName, String productLocation, String productDescription, Date dateCreated, String imageLink) {
        super();
        this.id = id;
        this.price = price;
        this.productName = productName;
        this.productLocation = productLocation;
        this.productDescription = productDescription;
        this.dateCreated = dateCreated;
        this.imageLink = imageLink;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Products(){
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductLocation() {
        return productLocation;
    }

    public void setProductLocation(String productLocation) {
        this.productLocation = productLocation;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
