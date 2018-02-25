package idv.haojun.finebye.data;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Cam {
    @Id public long Id;
    private String CityName;
    private String RegionName;
    private String Address;
    private String DeptNm;
    private String BranchNm;
    private String Longitude;
    private String Latitude;
    private String direct;
    private String limit;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getRegionName() {
        return RegionName;
    }

    public void setRegionName(String regionName) {
        RegionName = regionName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDeptNm() {
        return DeptNm;
    }

    public void setDeptNm(String deptNm) {
        DeptNm = deptNm;
    }

    public String getBranchNm() {
        return BranchNm;
    }

    public void setBranchNm(String branchNm) {
        BranchNm = branchNm;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "Cam{" +
                "Id=" + Id +
                ", CityName='" + CityName + '\'' +
                ", RegionName='" + RegionName + '\'' +
                ", Address='" + Address + '\'' +
                ", DeptNm='" + DeptNm + '\'' +
                ", BranchNm='" + BranchNm + '\'' +
                ", Longitude='" + Longitude + '\'' +
                ", Latitude='" + Latitude + '\'' +
                ", direct='" + direct + '\'' +
                ", limit='" + limit + '\'' +
                '}';
    }
}
