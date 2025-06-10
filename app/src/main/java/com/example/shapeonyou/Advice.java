// Advice.java
package com.example.shapeonyou;

/**
 * Modèle pour un conseil nutritif.
 */
public class Advice {
    private String title;
    private String description;

    public Advice(String title, String description) {
        this.title = title;
        this.description = description;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
}
