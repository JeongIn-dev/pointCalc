package kr.co.e8ight.ndxpro.pointcalc.domain;

public class Signal {
    private Integer id;
    private Integer EBL;
    private Integer WBT;
    private Integer SBL;
    private Integer NBT;
    private Integer WBL;
    private Integer EBT;
    private Integer NBL;
    private Integer SBT;

    public Signal(Integer id, Integer EBL, Integer WBT, Integer SBL, Integer NBT, Integer WBL, Integer EBT, Integer NBL, Integer SBT) {
        this.id = id;
        this.EBL = EBL;
        this.WBT = WBT;
        this.SBL = SBL;
        this.NBT = NBT;
        this.WBL = WBL;
        this.EBT = EBT;
        this.NBL = NBL;
        this.SBT = SBT;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEBL() {
        return EBL;
    }

    public void setEBL(Integer EBL) {
        this.EBL = EBL;
    }

    public Integer getWBT() {
        return WBT;
    }

    public void setWBT(Integer WBT) {
        this.WBT = WBT;
    }

    public Integer getSBL() {
        return SBL;
    }

    public void setSBL(Integer SBL) {
        this.SBL = SBL;
    }

    public Integer getNBT() {
        return NBT;
    }

    public void setNBT(Integer NBT) {
        this.NBT = NBT;
    }

    public Integer getWBL() {
        return WBL;
    }

    public void setWBL(Integer WBL) {
        this.WBL = WBL;
    }

    public Integer getEBT() {
        return EBT;
    }

    public void setEBT(Integer EBT) {
        this.EBT = EBT;
    }

    public Integer getNBL() {
        return NBL;
    }

    public void setNBL(Integer NBL) {
        this.NBL = NBL;
    }

    public Integer getSBT() {
        return SBT;
    }

    public void setSBT(Integer SBT) {
        this.SBT = SBT;
    }
}
