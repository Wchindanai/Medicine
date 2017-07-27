package dev.medicine;

/**
 * Created by dream on 4/13/2017 AD.
 */

public class FoodModel {
    private int id;
    private String name;
    private String how_to;
    private String risk;
    private String reference;

    public FoodModel(int id, String name, String how_to, String risk, String reference) {
        this.id = id;
        this.name = name;
        this.how_to = how_to;
        this.risk = risk;
        this.reference = reference;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHow_to() {
        return how_to;
    }

    public String getRisk() {
        return risk;
    }

    public String getReference() {
        return reference;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHow_to(String how_to) {
        this.how_to = how_to;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
