package com.example.mytask;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pojo {

    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("Result")
    @Expose
    private List<Result> result;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("RecordCount")
    @Expose
    private String recordCount;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }
    public class Result {

        @SerializedName("DealID")
        @Expose
        private String dealID;
        @SerializedName("PromoCode")
        @Expose
        private String promoCode;
        @SerializedName("Title")
        @Expose
        private String title;
        @SerializedName("Image")
        @Expose
        private String image;
        @SerializedName("Description")
        @Expose
        private String description;
        @SerializedName("TOC")
        @Expose
        private String toc;
        @SerializedName("DealCategoryID")
        @Expose
        private String dealCategoryID;
        @SerializedName("Discount")
        @Expose
        private String discount;
        @SerializedName("CurrencySymbol")
        @Expose
        private String currencySymbol;
        @SerializedName("ValidityStart")
        @Expose
        private String validityStart;
        @SerializedName("ValidityEnd")
        @Expose
        private String validityEnd;
        @SerializedName("DealType")
        @Expose
        private String dealType;
        @SerializedName("isFav")
        @Expose
        private String isFav;
        @SerializedName("MemberTier")
        @Expose
        private String memberTier;
        @SerializedName("StoreID")
        @Expose
        private String storeID;
        @SerializedName("StoreName")
        @Expose
        private String storeName;
        @SerializedName("TowerNumber")
        @Expose
        private String towerNumber;
        @SerializedName("CategName")
        @Expose
        private String categName;

        public String getDealID() {
            return dealID;
        }

        public void setDealID(String dealID) {
            this.dealID = dealID;
        }

        public String getPromoCode() {
            return promoCode;
        }

        public void setPromoCode(String promoCode) {
            this.promoCode = promoCode;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getToc() {
            return toc;
        }

        public void setToc(String toc) {
            this.toc = toc;
        }

        public String getDealCategoryID() {
            return dealCategoryID;
        }

        public void setDealCategoryID(String dealCategoryID) {
            this.dealCategoryID = dealCategoryID;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getCurrencySymbol() {
            return currencySymbol;
        }

        public void setCurrencySymbol(String currencySymbol) {
            this.currencySymbol = currencySymbol;
        }

        public String getValidityStart() {
            return validityStart;
        }

        public void setValidityStart(String validityStart) {
            this.validityStart = validityStart;
        }

        public String getValidityEnd() {
            return validityEnd;
        }

        public void setValidityEnd(String validityEnd) {
            this.validityEnd = validityEnd;
        }

        public String getDealType() {
            return dealType;
        }

        public void setDealType(String dealType) {
            this.dealType = dealType;
        }

        public String getIsFav() {
            return isFav;
        }

        public void setIsFav(String isFav) {
            this.isFav = isFav;
        }

        public String getMemberTier() {
            return memberTier;
        }

        public void setMemberTier(String memberTier) {
            this.memberTier = memberTier;
        }

        public String getStoreID() {
            return storeID;
        }

        public void setStoreID(String storeID) {
            this.storeID = storeID;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getTowerNumber() {
            return towerNumber;
        }

        public void setTowerNumber(String towerNumber) {
            this.towerNumber = towerNumber;
        }

        public String getCategName() {
            return categName;
        }

        public void setCategName(String categName) {
            this.categName = categName;
        }

    }
}





