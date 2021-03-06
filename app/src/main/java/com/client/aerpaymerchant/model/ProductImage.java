package com.client.aerpaymerchant.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductImage {

@SerializedName("id")
@Expose
private String id;
@SerializedName("product_id")
@Expose
private String productId;
@SerializedName("image")
@Expose
private String image;
@SerializedName("created")
@Expose
private String created;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getProductId() {
return productId;
}

public void setProductId(String productId) {
this.productId = productId;
}

public String getImage() {
return image;
}

public void setImage(String image) {
this.image = image;
}

public String getCreated() {
return created;
}

public void setCreated(String created) {
this.created = created;
}

}