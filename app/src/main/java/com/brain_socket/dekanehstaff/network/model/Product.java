package com.brain_socket.dekanehstaff.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {

    @SerializedName("nameAr")
    @Expose
    private String nameAr;
    @SerializedName("nameEn")
    @Expose
    private String nameEn;
    @SerializedName("pack")
    @Expose
    private String pack;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("consumerPrice")
    @Expose
    private Integer consumerPrice;
    @SerializedName("consumerPriceDiscount")
    @Expose
    private Integer consumerPriceDiscount;
    @SerializedName("horecaPrice")
    @Expose
    private Integer horecaPrice;
    @SerializedName("horecaPriceDiscount")
    @Expose
    private Integer horecaPriceDiscount;
    @SerializedName("wholeSalePrice")
    @Expose
    private Integer wholeSalePrice;
    @SerializedName("wholeSalePriceDiscount")
    @Expose
    private Integer wholeSalePriceDiscount;
    @SerializedName("isFeatured")
    @Expose
    private Boolean isFeatured;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("availableTo")
    @Expose
    private String availableTo;
    @SerializedName("isOffer")
    @Expose
    private Boolean isOffer;
    @SerializedName("isFavorite")
    @Expose
    private Boolean isFavorite;
    @SerializedName("offerSource")
    @Expose
    private String offerSource;
    @SerializedName("offerMaxQuantity")
    @Expose
    private Integer offerMaxQuantity;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("creationDate")
    @Expose
    private String creationDate;
    @SerializedName("parentCount")
    @Expose
    private Integer parentCount;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("categoryId")
    @Expose
    private String categoryId;
    @SerializedName("subCategoryId")
    @Expose
    private String subCategoryId;
    @SerializedName("offersIds")
    @Expose
    private List<Object> offersIds = null;
    @SerializedName("tagsIds")
    @Expose
    private List<String> tagsIds = null;
    @SerializedName("manufacturerId")
    @Expose
    private String manufacturerId;
    @SerializedName("media")
    @Expose
    private Media media;
    @SerializedName("offerProducts")
    @Expose
    private List<Object> offerProducts = null;
    @SerializedName("productAbstractId")
    @Expose
    private String productAbstractId;
    @SerializedName("wholeSaleMarketPrice")
    @Expose
    private Integer wholeSaleMarketPrice;
    @SerializedName("marketOfficialPrice")
    @Expose
    private Integer marketOfficialPrice;
    @SerializedName("dockanBuyingPrice")
    @Expose
    private Integer dockanBuyingPrice;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("subCategory")
    @Expose
    private SubCategory subCategory;
    @SerializedName("manufacturer")
    @Expose
    private Manufacturer manufacturer;
    @SerializedName("products")
    @Expose
    private List<Product> products = null;
    @SerializedName("productAbstract")
    @Expose
    private ProductAbstract productAbstract;
    private final static long serialVersionUID = -6990077557864329245L;

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getConsumerPrice() {
        return consumerPrice;
    }

    public void setConsumerPrice(Integer consumerPrice) {
        this.consumerPrice = consumerPrice;
    }

    public Integer getConsumerPriceDiscount() {
        return consumerPriceDiscount;
    }

    public void setConsumerPriceDiscount(Integer consumerPriceDiscount) {
        this.consumerPriceDiscount = consumerPriceDiscount;
    }

    public Integer getHorecaPrice() {
        return horecaPrice;
    }

    public void setHorecaPrice(Integer horecaPrice) {
        this.horecaPrice = horecaPrice;
    }

    public Integer getHorecaPriceDiscount() {
        return horecaPriceDiscount;
    }

    public void setHorecaPriceDiscount(Integer horecaPriceDiscount) {
        this.horecaPriceDiscount = horecaPriceDiscount;
    }

    public Integer getWholeSalePrice() {
        return wholeSalePrice;
    }

    public void setWholeSalePrice(Integer wholeSalePrice) {
        this.wholeSalePrice = wholeSalePrice;
    }

    public Integer getWholeSalePriceDiscount() {
        return wholeSalePriceDiscount;
    }

    public void setWholeSalePriceDiscount(Integer wholeSalePriceDiscount) {
        this.wholeSalePriceDiscount = wholeSalePriceDiscount;
    }

    public Boolean getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(String availableTo) {
        this.availableTo = availableTo;
    }

    public Boolean getIsOffer() {
        return isOffer;
    }

    public void setIsOffer(Boolean isOffer) {
        this.isOffer = isOffer;
    }

    public Boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public String getOfferSource() {
        return offerSource;
    }

    public void setOfferSource(String offerSource) {
        this.offerSource = offerSource;
    }

    public Integer getOfferMaxQuantity() {
        return offerMaxQuantity;
    }

    public void setOfferMaxQuantity(Integer offerMaxQuantity) {
        this.offerMaxQuantity = offerMaxQuantity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getParentCount() {
        return parentCount;
    }

    public void setParentCount(Integer parentCount) {
        this.parentCount = parentCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public List<Object> getOffersIds() {
        return offersIds;
    }

    public void setOffersIds(List<Object> offersIds) {
        this.offersIds = offersIds;
    }

    public List<String> getTagsIds() {
        return tagsIds;
    }

    public void setTagsIds(List<String> tagsIds) {
        this.tagsIds = tagsIds;
    }

    public String getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public List<Object> getOfferProducts() {
        return offerProducts;
    }

    public void setOfferProducts(List<Object> offerProducts) {
        this.offerProducts = offerProducts;
    }

    public String getProductAbstractId() {
        return productAbstractId;
    }

    public void setProductAbstractId(String productAbstractId) {
        this.productAbstractId = productAbstractId;
    }

    public Integer getWholeSaleMarketPrice() {
        return wholeSaleMarketPrice;
    }

    public void setWholeSaleMarketPrice(Integer wholeSaleMarketPrice) {
        this.wholeSaleMarketPrice = wholeSaleMarketPrice;
    }

    public Integer getMarketOfficialPrice() {
        return marketOfficialPrice;
    }

    public void setMarketOfficialPrice(Integer marketOfficialPrice) {
        this.marketOfficialPrice = marketOfficialPrice;
    }

    public Integer getDockanBuyingPrice() {
        return dockanBuyingPrice;
    }

    public void setDockanBuyingPrice(Integer dockanBuyingPrice) {
        this.dockanBuyingPrice = dockanBuyingPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public ProductAbstract getProductAbstract() {
        return productAbstract;
    }

    public void setProductAbstract(ProductAbstract productAbstract) {
        this.productAbstract = productAbstract;
    }

}
