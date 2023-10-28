package ReadWriteData;

public class Currency {
    private String name;
    private Double from;
    private Double to;
    private Double actual;
    private Integer compare;

    public Currency () {
    }

    public Currency (String name, Double from, Double to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getFrom() {
        return from;
    }

    public void setFrom(Double from) {
        this.from = from;
    }

    public Double getTo() {
        return to;
    }

    public void setTo(Double to) {
        this.to = to;
    }

    public Double getActual() {
        return actual;
    }

    public void setActual(Double actual) {
        this.actual = actual;
        setCompare(compare(actual));
    }

    public Integer getCompare() {
        return compare;
    }

    private void setCompare(Integer compare) {
        this.compare = compare;
    }

    private Integer compare(Double value) {
        if (value == null) {
            return null;
        } else if (to != null) {
            if (to < value) {
                return 1;
            } else if (from != null) {
                if (from > value) {
                    return -1;
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } else if (from != null) {
            if (from > value) {
                return -1;
            } else {
                return 0;
            }
        }
        return null;
    }
}
