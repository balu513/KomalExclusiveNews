package com.balu.komalexclusivenews.model.cricket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlayerFinder {

@SerializedName("data")
@Expose
private List<Datum> data = null;
@SerializedName("ttl")
@Expose
private Integer ttl;
@SerializedName("cache3")
@Expose
private Boolean cache3;
@SerializedName("v")
@Expose
private String v;
@SerializedName("provider")
@Expose
private Provider provider;
@SerializedName("creditsLeft")
@Expose
private Integer creditsLeft;

public List<Datum> getData() {
return data;
}

public void setData(List<Datum> data) {
this.data = data;
}

public Integer getTtl() {
return ttl;
}

public void setTtl(Integer ttl) {
this.ttl = ttl;
}

public Boolean getCache3() {
return cache3;
}

public void setCache3(Boolean cache3) {
this.cache3 = cache3;
}

public String getV() {
return v;
}

public void setV(String v) {
this.v = v;
}

public Provider getProvider() {
return provider;
}

public void setProvider(Provider provider) {
this.provider = provider;
}

public Integer getCreditsLeft() {
return creditsLeft;
}

public void setCreditsLeft(Integer creditsLeft) {
this.creditsLeft = creditsLeft;
}

}