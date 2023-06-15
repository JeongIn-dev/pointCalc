package kr.co.e8ight.ndxpro.pointcalc.domain;

public class PedestrianSignalsRemainSeconds {
    private Integer id;
    private Integer E;
    private Integer W;
    private Integer S;
    private Integer N;

    public PedestrianSignalsRemainSeconds(Integer id, Integer e, Integer w, Integer s, Integer n) {
        this.id = id;
        E = e;
        W = w;
        S = s;
        N = n;
    }

    public Integer getId() {
        return id;
    }

    public Integer getE() {
        return E;
    }

    public Integer getW() {
        return W;
    }

    public Integer getS() {
        return S;
    }

    public Integer getN() {
        return N;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setE(Integer e) {
        E = e;
    }

    public void setW(Integer w) {
        W = w;
    }

    public void setS(Integer s) {
        S = s;
    }

    public void setN(Integer n) {
        N = n;
    }
}
