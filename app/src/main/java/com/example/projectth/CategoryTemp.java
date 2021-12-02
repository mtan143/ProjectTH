package com.example.projectth;

public class CategoryTemp {
    private int CategoriesID;
    private String name;
    private String image;

    public CategoryTemp(int categoriesID, String name, String image) {
        CategoriesID = categoriesID;
        this.name = name;
        this.image = image;
    }

    public int getCategoriesID() {
        return CategoriesID;
    }

    public void setCategoriesID(int categoriesID) {
        CategoriesID = categoriesID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
